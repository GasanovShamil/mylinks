package dao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.ClickBean;
import utils.HibernateUtil;

@Named
@RequestScoped
public class ClickDaoImpl implements ClickDao {

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
	public boolean putStat(ClickBean bean) {
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
	public ClickBean getStat(String statId) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		ClickBean bean = (ClickBean) session.get(ClickBean.class, statId);
		session.getTransaction().commit();
		return bean;
	}

	@Override
	public void updateStat(ClickBean bean) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(bean);
		session.getTransaction().commit();
	}

}
