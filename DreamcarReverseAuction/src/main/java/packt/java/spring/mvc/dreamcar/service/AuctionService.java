package packt.java.spring.mvc.dreamcar.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import packt.java.spring.mvc.dreamcar.enums.AuctionStatusEnum;
import packt.java.spring.mvc.dreamcar.interfaces.IAuctionService;
import packt.java.spring.mvc.dreamcar.pojo.Auction;
import packt.java.spring.mvc.dreamcar.pojo.Bid;
import packt.java.spring.mvc.dreamcar.pojo.Component;
import packt.java.spring.mvc.dreamcar.pojo.User;
import packt.java.spring.mvc.dreamcar.viewmodels.AuctionDetailViewModel;
import packt.java.spring.mvc.dreamcar.viewmodels.AuctionViewModel;
import packt.java.spring.mvc.dreamcar.viewmodels.CreateAuctionViewModel;

@Service
public class AuctionService implements IAuctionService {

	private static SessionFactory factory = SessionFactoryHelper
			.getSessionfactory();

	private List<AuctionViewModel> auctionViewModels = null;

	public AuctionService() {
	}

	public boolean createAuction(CreateAuctionViewModel auctionViewModel) {
		Session session = factory.openSession();
		Transaction tx = null;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = new Date();
		Date endDate = null;

		try {
			endDate = formatter.parse(auctionViewModel.getEndDate());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		int statusId = AuctionStatusEnum.Started.getId();
		Component newComponent = new Component();
		newComponent.setComponentId(auctionViewModel.getComponentId());
		User owner = new User();
		owner.setUserId(auctionViewModel.getOwnerId());

		Auction newAuction = new Auction();
		newAuction.setStatusId(statusId);
		newAuction.setStartDate(startDate);
		newAuction.setEndDate(endDate);
		newAuction.setQuantity(auctionViewModel.getQuantity());
		newAuction.setComponent(newComponent);
		newAuction.setTargetPrice(auctionViewModel.getTargetPrice());
		newAuction.setUserByOwnerId(owner);
		newAuction.setCurrencyType(auctionViewModel.getCurrencyType());

		try {

			tx = session.beginTransaction();
			session.save(newAuction);
			tx.commit();

			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	public List<AuctionViewModel> getAuctions() {
		Session session = factory.openSession();
		List<Auction> auctions = null;

		try {
			SQLQuery sqlQuery = session.createSQLQuery("select * from Auction");
			auctions = (List<Auction>) sqlQuery.addEntity(Auction.class).list();

			if (auctions != null) {
				auctionViewModels = new ArrayList<AuctionViewModel>();
				for (Auction auction : auctions) {
					AuctionStatusEnum statusId = AuctionStatusEnum
							.getType(auction.getStatusId());
					Hibernate.initialize(auction.getUserByOwnerId());
					Hibernate.initialize(auction.getComponent());

					User owner = auction.getUserByOwnerId();
					Component component = auction.getComponent();
					AuctionViewModel newAuction = new AuctionViewModel(
							auction.getAuctionId(), component, owner,
							auction.getCurrencyType(),
							auction.getTargetPrice(), auction.getStartDate(),
							auction.getEndDate(), auction.getQuantity(),
							statusId);
					auctionViewModels.add(newAuction);
				}
			}

			return auctionViewModels;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}

		return auctionViewModels;
	}

	@SuppressWarnings("unchecked")
	public AuctionDetailViewModel getAuctionDetails(int auctionId) {
		Session session = factory.openSession();
		Auction auction = null;
		AuctionDetailViewModel auctionDetail = null;
		try {
			SQLQuery sqlQuery = (SQLQuery) session.createSQLQuery(
					"select * from Auction where auctionId=:auctionId")
					.setParameter("auctionId", auctionId);
			List<Auction> auctions = (ArrayList<Auction>) sqlQuery.addEntity(
					Auction.class).list();
			auction = auctions.get(0);
			
			Hibernate.initialize(auction.getComponent());
			Hibernate.initialize(auction.getUserByWinnerId());
			Component component = auction.getComponent();
			User winner = auction.getUserByWinnerId();

			auctionDetail = new AuctionDetailViewModel();
			auctionDetail.setTargetPrice(auction.getTargetPrice());
			auctionDetail.setStartDate(auction.getStartDate());
			auctionDetail.setEndDate(auction.getEndDate());
			auctionDetail.setComponentName(component.getName());
			auctionDetail.setQuantity(auction.getQuantity());
			auctionDetail.setCurrencyType(auction.getCurrencyType());
			auctionDetail.setStatusId(AuctionStatusEnum.getType(auction.getStatusId()));
			auctionDetail.setWinner(winner);
			
			return auctionDetail;

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}

		return auctionDetail;
	}

	@SuppressWarnings("unchecked")
	public void checkEndedAuctions() {
		Session session = factory.openSession();
		Date today = new Date();
		Transaction tx = null;

		try {
			SQLQuery sqlQuery = (SQLQuery) session
					.createSQLQuery(
							"select * from Auction where statusId=:statusId")
					.setParameter("statusId", AuctionStatusEnum.Started.getId());
			List<Auction> auctions = (ArrayList<Auction>) sqlQuery.addEntity(
					Auction.class).list();

			for (Auction auction : auctions) {
				Hibernate.initialize(auction.getBids());
				List<Bid> bids =  new ArrayList<Bid>(auction.getBids());

				if (bids.size() > 0) {
					Bid lowestBid = bids.get(0);
					for (int i = 1; i < bids.size(); i++) {
						Bid newBid = bids.get(i);

						if (lowestBid.getAmount() <= newBid.getAmount()) {
							lowestBid = newBid;
						}
					}
					Hibernate.initialize(lowestBid.getUser());
					User winner = lowestBid.getUser();

					if (lowestBid.getAmount() <= auction.getTargetPrice() 
							|| auction.getEndDate().compareTo(today) <= 0) {
						
						auction.setUserByWinnerId(winner);
						auction.setStatusId(AuctionStatusEnum.Ended.getId());
						tx = session.beginTransaction();
						session.update(auction);
						tx.commit();
					}
				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}
}