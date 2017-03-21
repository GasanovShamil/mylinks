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

import beans.UrlBean;

@Named
@RequestScoped
public class UrlDao {

	@Inject
	Database db;

	private Connection conn;

	@PostConstruct
	public void init() {
		conn = db.getConnection();
	}

	@PreDestroy
	public void destroy() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean putUrl(UrlBean bean) {
		try {
			PreparedStatement pst = conn.prepareStatement(
					"INSERT INTO url (shortUrl, longUrl, createDate, expireDate, nbClicks, userId) VALUES (?,?,?,?,?,?)");

			pst.setString(1, bean.getShortUrl());
			pst.setString(2, bean.getLongUrl());
			pst.setTimestamp(3, bean.getCreateDate());
			if (bean.getExpireDate() != null) {
				pst.setTimestamp(4, bean.getExpireDate());
			} else {
				pst.setNull(4, java.sql.Types.TIMESTAMP);
			}
			if (bean.getNbClicks() != null) {
				pst.setInt(5, bean.getNbClicks());
			} else {
				pst.setNull(5, java.sql.Types.INTEGER);
			}
			if (bean.getUserId() != null) {
				pst.setInt(6, bean.getUserId());
			} else {
				pst.setNull(6, java.sql.Types.INTEGER);
			}

			pst.executeUpdate();

			pst.close();
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public UrlBean getUrl(String shortUrl) {
		PreparedStatement pst;
		UrlBean bean = null;
		try {
			pst = conn.prepareStatement("SELECT * FROM url WHERE shortUrl = ?");
			pst.setString(1, shortUrl);
			ResultSet res = pst.executeQuery();
			if (res.next()) {
				bean = new UrlBean(shortUrl, res.getString(2),res.getTimestamp(3) , res.getTimestamp(4), res.getInt(5), res.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;

	}
}
