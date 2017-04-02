package dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sessionAnnotationFactory;
	    
    private static SessionFactory buildSessionAnnotationFactory() {
    	try {
        	Configuration configuration = new Configuration();
        	configuration.configure("hibernate.cfg.xml");
        	System.out.println("Hibernate Annotation Configuration loaded");
        	
        	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        	System.out.println("Hibernate Annotation serviceRegistry created");
        	
        	SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        	
            return sessionFactory;
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
	}   
    	
	public static SessionFactory getSessionAnnotationFactory() {
		if(sessionAnnotationFactory == null) sessionAnnotationFactory = buildSessionAnnotationFactory();
        return sessionAnnotationFactory;
    }	
}