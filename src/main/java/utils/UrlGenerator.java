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

import beans.UrlBean;
import dao.UrlDao;

@Named
@ApplicationScoped
public class UrlGenerator implements Serializable {

	

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
	

	@PostConstruct
	public void init() {

		UrlBean bean = urlDao.getLastGenericUrl();
			if (bean == null) {
				System.out.println("there is no url in database. First url is set to : 11110");
				this.setUrl("11110");
			} else {
				this.setUrl(bean.getShortUrl());
				System.out.println("Last shortUrl in database is:" + this.getUrl());
			}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
