package dao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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

	// public boolean putUrl(UrlBean bean) {
	// try {
	// PreparedStatement pst = conn.prepareStatement(
	// "INSERT INTO url (shortUrl, longUrl, createDate, expireDate, password,
	// nbClicks, userId, generic) VALUES (?,?,?,?,?,?,?,?)");
	//
	// pst.setString(1, bean.getShortUrl());
	// pst.setString(2, bean.getLongUrl());
	// pst.setTimestamp(3, bean.getCreateDate());
	// if (bean.getExpireDate() != null) {
	// pst.setTimestamp(4, bean.getExpireDate());
	// } else {
	// pst.setNull(4, java.sql.Types.TIMESTAMP);
	// }
	// if (bean.getPassword() != null) {
	// pst.setString(5, bean.getPassword());
	// } else {
	// pst.setNull(5, java.sql.Types.VARCHAR);
	// }
	// if (bean.getNbClicks() != null) {
	// pst.setInt(6, bean.getNbClicks());
	// } else {
	// pst.setNull(6, java.sql.Types.INTEGER);
	// }
	// if (bean.getUserId() != null) {
	// pst.setInt(7, bean.getUserId());
	// } else {
	// pst.setNull(7, java.sql.Types.INTEGER);
	// }
	// pst.setBoolean(8, bean.isGeneric());
	//
	// pst.executeUpdate();
	//
	// pst.close();
	// conn.close();
	// return true;
	// } catch (SQLException e) {
	// e.printStackTrace();
	// return false;
	// }
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.UrlDao#getUrl(java.lang.String)
	 */
	@Override
	public UrlBean getUrl(String shortUrl) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		UrlBean bean = (UrlBean) session.get(UrlBean.class, shortUrl);
		session.getTransaction().commit();
		return bean;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.UrlDao#updateUrl(beans.UrlBean)
	 */
	@Override
	public void updateUrl(UrlBean bean) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(bean);
		session.getTransaction().commit();
	}

	// public UrlBean getUrl(String shortUrl) {
	// PreparedStatement pst;
	// UrlBean bean = null;
	// try {
	// pst = conn.prepareStatement("SELECT * FROM url WHERE shortUrl = ?");
	// pst.setString(1, shortUrl);
	// ResultSet res = pst.executeQuery();
	// if (res.next()) {
	// bean = new UrlBean(shortUrl, res.getString("longUrl"),
	// res.getTimestamp("createDate"),
	// res.getTimestamp("expireDate"), res.getString("password"),
	// res.getInt("nbClicks"),
	// res.getInt("userId"), res.getBoolean("generic"));
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// return bean;
	//
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.UrlDao#shortUrlExist(java.lang.String)
	 */
	@Override
	public boolean shortUrlExist(String shortUrl) {
		return getUrl(shortUrl) != null;
	}

}
