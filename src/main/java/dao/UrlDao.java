package dao;

import beans.UrlBean;

public interface UrlDao {

	boolean putUrl(UrlBean bean);

	UrlBean getUrl(String shortUrl);

	void updateUrl(UrlBean bean);

	boolean shortUrlExist(String shortUrl);

}