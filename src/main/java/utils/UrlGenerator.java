package utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dao.UrlDao;

@Named
@ApplicationScoped
public class UrlGenerator implements Serializable {

	@Inject
	Database db;

	@Inject UrlDao urlDao;
	private String url;

	private static final long serialVersionUID = 1L;

	public UrlGenerator() {
	}

	public String newUrl() {
		boolean res = false;
		do {
			generateUrl();
			res = urlDao.shortUrlExist(url);
		} while (res);
		return url;
	}

	private void generateUrl() {
		char[] urls = url.toCharArray();
		for (int i = urls.length - 1; i >= 0; i--) {
			if (urls[i] < '9') {
				urls[i] = (char) (urls[i] + 1);
				break;
			} else if (urls[i] == '9') {
				urls[i] = 'A';
				break;
			} else if (urls[i] < 'Z') {
				urls[i] = (char) (urls[i] + 1);
				break;
			} else if (urls[i] == 'Z') {
				urls[i] = 'a';
				break;
			} else if (urls[i] < 'z') {
				urls[i] = (char) (urls[i] + 1);
				break;
			} else {
				urls[i] = '1';
			}
		}
		url = new String(urls);
	}
	
	

//	private boolean canIncrement(char[] charray) {
//		for (int i = 0; i < charray.length - 1; i++) {
//			if (charray[i] != 'z')
//				return true;
//		}
//		return false;
//	}
//
//	private String getMoreSpace(String u) {
//		char[] newUrls = new char[u.length() + 1];
//		for (int i = 0; i < newUrls.length - 1; i++) {
//			newUrls[i] = '1';
//		}
//		return new String(newUrls);
//
//	}

	@PostConstruct
	public void init() {

		Connection conn = db.getConnection();

		Statement st;
		try {
			st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * from url WHERE createDate = (SELECT MAX(createDate) FROM url WHERE generic = true)");
			if (!res.next()) {
				System.out.println("there is no url in database. First url is set to : 11110");
				this.setUrl("11110");
			} else {
				this.setUrl(res.getString("shortUrl"));
				System.out.println("Last shortUrl in database is:" + this.getUrl());
			}
			res.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
