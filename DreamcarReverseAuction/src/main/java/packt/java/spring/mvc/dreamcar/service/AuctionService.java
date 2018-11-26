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
import packt.java.spring.mvc.dreamcar.pojo.Component;
import packt.java.spring.mvc.dreamcar.pojo.User;
import packt.java.spring.mvc.dreamcar.viewmodels.AuctionDetailViewModel;
import packt.java.spring.mvc.dreamcar.viewmodels.AuctionViewModel;
import packt.java.spring.mvc.dreamcar.viewmodels.CreateAuctionViewModel;

@Service
public class AuctionService implements IAuctionService {

	private static SessionFactory factory = SessionFactoryHelper
			.getSessionFactory();

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
		newAuction.setOwner(owner);
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
					Hibernate.initialize(auction.getOwner());
					Hibernate.initialize(auction.getComponent());

					User owner = auction.getOwner();
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

	public AuctionDetailViewModel getAuctionDetails(int auctionId) {
		Session session = factory.openSession();
		Auction auction = null;
		AuctionDetailViewModel auctionDetail = null;
		try {
			SQLQuery sqlQuery = (SQLQuery) session.createSQLQuery(
					"select * from Auction where auctionId=:auctionId")
					.setParameter("auctionid", auctionId);
			auction = (Auction) sqlQuery.addEntity(Auction.class).list().get(0);
			Hibernate.initialize(auction.getComponent());
			Component component = auction.getComponent();
			
			auctionDetail = new AuctionDetailViewModel();
			auctionDetail.setTargetPrice(auction.getTargetPrice());
			auctionDetail.setStartDate(auction.getStartDate());
			auctionDetail.setComponentName(component.getName());
			auctionDetail.setQuantity(auction.getQuantity());
			auctionDetail.setCurrencyType(auction.getCurrencyType());

			return auctionDetail;

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}

		return auctionDetail;
	}
}