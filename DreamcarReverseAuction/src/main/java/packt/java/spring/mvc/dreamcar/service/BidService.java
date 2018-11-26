package packt.java.spring.mvc.dreamcar.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import packt.java.spring.mvc.dreamcar.interfaces.IBidService;
import packt.java.spring.mvc.dreamcar.pojo.Auction;
import packt.java.spring.mvc.dreamcar.pojo.Bid;
import packt.java.spring.mvc.dreamcar.pojo.Company;
import packt.java.spring.mvc.dreamcar.pojo.User;
import packt.java.spring.mvc.dreamcar.viewmodels.BidViewModel;
import packt.java.spring.mvc.dreamcar.viewmodels.CreateBidViewModel;

@Service
public class BidService implements IBidService {

	private static SessionFactory factory = SessionFactoryHelper
			.getSessionFactory();
	
	public BidService(){
	}
	
	public boolean createBid(CreateBidViewModel bidViewModel) {
		Session session = factory.openSession();
		Transaction tx = null;

		Date createdDate = new Date();
		User bidder = new User();
		bidder.setUserId(bidViewModel.getUserId());
		Auction auction = new Auction();
		auction.setAuctionId(bidViewModel.getAuctionId());
		
		Bid newBid = new Bid();
		newBid.setCreatedDate(createdDate);
		newBid.setUser(bidder);
		newBid.setAmount(bidViewModel.getBidAmount());
		newBid.setAuction(auction);
		
		try {

			tx = session.beginTransaction();
			session.save(newBid);
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
	public List<BidViewModel> getAuctionBids(int auctionId){
		
		if(auctionId == 0){
			return null;
		}
		
		Session session = factory.openSession();
		List<Bid> bids = null;
		List<BidViewModel> bidViewModels = null;
		
		try {
			SQLQuery query = (SQLQuery) session.createSQLQuery("select * from Bid where auctionId=:auctionId")
					.setParameter("auctionId", auctionId);
			
			bids = (List<Bid>) query.addEntity(Bid.class).list();

			if (bids != null) {
				bidViewModels = new ArrayList<BidViewModel>();
				
				for (Bid bid : bids) {

					BidViewModel newBid = new BidViewModel();
					Hibernate.initialize(bid.getUser());
					Hibernate.initialize(bid.getUser().getCompany());
					
					User bidder = bid.getUser();
					Company bidderCompany = bidder.getCompany();
					
					newBid.setBidderUsername(bidder.getUsername());
					newBid.setCompanyName(bidderCompany.getName());
					newBid.setAmount(bid.getAmount());
					newBid.setCreatedDate(bid.getCreatedDate());
					newBid.setBidId(bid.getBidId());
					
					bidViewModels.add(newBid);
				}
			}

			return bidViewModels;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}

		return bidViewModels;
	}
	
}
