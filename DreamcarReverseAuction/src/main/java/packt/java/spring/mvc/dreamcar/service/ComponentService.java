package packt.java.spring.mvc.dreamcar.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import packt.java.spring.mvc.dreamcar.interfaces.IComponentService;
import packt.java.spring.mvc.dreamcar.pojo.Component;

@Service
public class ComponentService implements IComponentService {
	
	public ComponentService(){
	}

	private static SessionFactory factory = SessionFactoryHelper
			.getSessionfactory();

	@SuppressWarnings("unchecked")
	public List<Component> getComponentList() {
		Session session = factory.openSession();
		List<Component> components = null;

		try {
			SQLQuery sqlQuery = session.createSQLQuery("select * from Component");
			components = (List<Component>) sqlQuery.addEntity(Component.class).list();

			return components;
		} catch (HibernateException e) {
		  e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}

		return components;
	}
	
	public Component createComponent(String componentName) {
		Session session = factory.openSession();
		Component newComponent = null;
		Transaction tx = null;
		
		try {
			Query query = session.createQuery(
					"from Component where name=:name")
					.setParameter("name", componentName );
			boolean componentExists = query.uniqueResult() != null;

			if (!componentExists) {
				newComponent = new Component(componentName);
				tx = session.beginTransaction();
				session.save(newComponent);
				tx.commit();
			}
			return newComponent;
		} catch (HibernateException e) {
		  e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}

		return newComponent;
	}
}
