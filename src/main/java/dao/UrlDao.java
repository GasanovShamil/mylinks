package dao;

import java.util.List;

import beans.UrlBean;

public interface UrlDao {

	boolean putUrl(UrlBean bean);

	UrlBean getUrl(String shortUrl);

	void updateUrl(UrlBean bean);

	boolean shortUrlExist(String shortUrl);

	List<UrlBean> getUrlsByUserId(Integer userId);
	
	UrlBean getLastGenericUrl();

}