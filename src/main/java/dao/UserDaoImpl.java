package dao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import beans.UserBean;

@Named
@RequestScoped
public class UserDaoImpl implements UserDao {

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
	public UserBean getUser(String login) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(UserBean.class);
		UserBean bean = (UserBean) criteria.add(Restrictions.eq("login", login)).uniqueResult();
		session.getTransaction().commit();
		return bean;
	}

	@Override
	public boolean putUser(UserBean bean) {
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
	public void updateUser(UserBean bean) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(bean);
		session.getTransaction().commit();
	}

}
