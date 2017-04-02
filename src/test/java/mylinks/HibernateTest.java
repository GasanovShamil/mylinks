package mylinks;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.UrlBean;
import beans.UserBean;
import dao.HibernateUtil;

public class HibernateTest {

	
	
	
	public static void main(String[] args) {
		String shortUrl = "hibernate";
		String longUrlString = "http://www.journaldev.com/2882/hibernate-tutorial-for-beginners";
		Timestamp createTimestamp = createTimestamp = new Timestamp(new Date().getTime());
		Timestamp expireTimestamp = null;
		
		String urlPasswordString = null;
		String expireDateString = null;
		String nbClickString = null;
		Integer nbClickInt = null;
		Integer userId = null;
		boolean generic = false;
		
		UrlBean url = new UrlBean(shortUrl, longUrlString, createTimestamp, expireTimestamp, urlPasswordString, nbClickInt, userId, generic);
		
		//Get Session
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.getCurrentSession();
		//start transaction
		session.beginTransaction();
		//Save the Model object
		//session.save(url);
		UrlBean url2 = (UrlBean) session.get(UrlBean.class, shortUrl);
		if(url2 != null) System.out.println(url2.getShortUrl());
		//Commit transaction
		session.getTransaction().commit();
		
		//terminate session factory, otherwise program won't end
		sessionFactory.close();
	}

}