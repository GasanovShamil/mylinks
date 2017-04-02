package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.UrlBean;

@Named
@RequestScoped
public class UrlDao {

	@Inject
	Database db;

	private Connection conn;
	private SessionFactory sessionFactory;
	private Session session;

	@PostConstruct
	public void init() {
		conn = db.getConnection();
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@PreDestroy
	public void destroy() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 sessionFactory.close();
	}

	 public boolean putUrl(UrlBean bean) {
	 try {
	 session = sessionFactory.getCurrentSession();
	 session.beginTransaction();
	 session.save(bean);
	 session.getTransaction().commit();
	 } catch (HibernateException e) {
	 System.out.println(e.getMessage());
//	 session.close();
	 return false;
	 }
//	 session.close();
	 return true;
	 }

//	public boolean putUrl(UrlBean bean) {
//		try {
//			PreparedStatement pst = conn.prepareStatement(
//					"INSERT INTO url (shortUrl, longUrl, createDate, expireDate, password, nbClicks, userId, generic) VALUES (?,?,?,?,?,?,?,?)");
//
//			pst.setString(1, bean.getShortUrl());
//			pst.setString(2, bean.getLongUrl());
//			pst.setTimestamp(3, bean.getCreateDate());
//			if (bean.getExpireDate() != null) {
//				pst.setTimestamp(4, bean.getExpireDate());
//			} else {
//				pst.setNull(4, java.sql.Types.TIMESTAMP);
//			}
//			if (bean.getPassword() != null) {
//				pst.setString(5, bean.getPassword());
//			} else {
//				pst.setNull(5, java.sql.Types.VARCHAR);
//			}
//			if (bean.getNbClicks() != null) {
//				pst.setInt(6, bean.getNbClicks());
//			} else {
//				pst.setNull(6, java.sql.Types.INTEGER);
//			}
//			if (bean.getUserId() != null) {
//				pst.setInt(7, bean.getUserId());
//			} else {
//				pst.setNull(7, java.sql.Types.INTEGER);
//			}
//			pst.setBoolean(8, bean.isGeneric());
//
//			pst.executeUpdate();
//
//			pst.close();
//			conn.close();
//			return true;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		}
//	}

	public UrlBean getUrl(String shortUrl) {
		PreparedStatement pst;
		UrlBean bean = null;
		try {
			pst = conn.prepareStatement("SELECT * FROM url WHERE shortUrl = ?");
			pst.setString(1, shortUrl);
			ResultSet res = pst.executeQuery();
			if (res.next()) {
				bean = new UrlBean(shortUrl, res.getString("longUrl"), res.getTimestamp("createDate"),
						res.getTimestamp("expireDate"), res.getString("password"), res.getInt("nbClicks"),
						res.getInt("userId"), res.getBoolean("generic"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;

	}

	public boolean shortUrlExist(String shortUrl) {
		return getUrl(shortUrl) != null;
	}

}
