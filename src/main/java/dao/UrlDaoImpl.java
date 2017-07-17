package dao;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import beans.UrlBean;
import utils.HibernateUtil;

@Named
@RequestScoped
public class UrlDaoImpl implements UrlDao {

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
	public boolean putUrl(UrlBean bean) {
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
	public UrlBean getUrl(String shortUrl) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		UrlBean bean = (UrlBean) session.get(UrlBean.class, shortUrl);
		session.getTransaction().commit();
		return bean;
	}

	@Override
	public void updateUrl(UrlBean bean) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(bean);
		session.getTransaction().commit();
	}

	@Override
	public boolean shortUrlExist(String shortUrl) {
		return getUrl(shortUrl) != null;
	}

	@Override
	public List<UrlBean> getUrlsByUserId(Integer userId) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(UrlBean.class);
		criteria.addOrder(Order.desc("createDate"));
		List<UrlBean> beans = criteria.add(Restrictions.eq("userId", userId)).list();
		session.getTransaction().commit();
		return beans;
	}
	
	@Override
	public UrlBean getLastGenericUrl() {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Criteria maxQuery = session.createCriteria(UrlBean.class);
		maxQuery.setProjection( Projections.max( "createDate" ));
		maxQuery.add(Restrictions.eq("generic", true));
		List<Timestamp> dateList = maxQuery.list();
		session.getTransaction().commit();
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(UrlBean.class);
		criteria.add(Restrictions.eq("generic", true));
		criteria.add(Restrictions.eq("createDate", dateList.get(0)));
		List<UrlBean> beans = criteria.list();
		session.getTransaction().commit();
		
		return (beans.isEmpty())?null:beans.get(0);
	}
}
