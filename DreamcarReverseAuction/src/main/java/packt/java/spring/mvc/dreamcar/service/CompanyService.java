package packt.java.spring.mvc.dreamcar.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import packt.java.spring.mvc.dreamcar.interfaces.ICompanyService;
import packt.java.spring.mvc.dreamcar.pojo.Company;

@Service
public class CompanyService implements ICompanyService {

	public CompanyService() {
	}

	private List<Company> companyList = null;
	
	public List<Company> getCompanyList(){
		if(companyList == null){
			companyList = getCompanies();
		}
		
		return companyList;
	}
	
	private static SessionFactory factory = SessionFactoryHelper
			.getSessionfactory();

	@SuppressWarnings("unchecked")
	private List<Company> getCompanies() {
		Session session = factory.openSession();
		List<Company> companies = null;

		try {
			SQLQuery sqlQuery = session.createSQLQuery("select * from Company where companyId <> 1");
			companies = (List<Company>) sqlQuery.addEntity(Company.class).list();

			return companies;
		} catch (HibernateException e) {
		  e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}

		return companies;
	}
}