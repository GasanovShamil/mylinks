package dao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.StatBean;
import utils.HibernateUtil;

@Named
@RequestScoped
public class StatDaoImpl implements StatDao {

	@Inject
	HibernateUtil hibernateUtil;

	private SessionFactory sessionFactory;
	private Session session;

	@PostConstruct
	public void init() {
		sessionFactory = hibernateUtil.getSessionFactory();
	}

	@PreDestroy
	public void destroy() {
	}
	
	@Override
	public boolean putStat(StatBean bean) {
		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(bean);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public StatBean getStat(String shortUrl) {
		// TODO Desider comment recuperer les stats
		return null;
	}

	@Override
	public void updateStat(StatBean bean) {
		// TODO Je ne sais pas si on a besoin de cette metode
	}

}
