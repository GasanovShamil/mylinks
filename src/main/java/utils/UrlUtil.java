package utils;

import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import beans.UrlBean;
import beans.UserBean;
import dao.UrlDao;

@Named
@RequestScoped
public class UrlUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	UrlGenerator urlGenerator;

	@Inject
	UrlDao urlDao;

	public String[] createUrl(String personalUrlString, String longUrlString, String startDateString,
			String expireDateString, String urlPasswordString, String captcha, String nbClickString, Integer userId) {
		String hostString = "http://localhost:8080/mylinks/"; // getServletContext().getInitParameter("host");
		String[] map = new String[2];
		String shortUrl;
		String urlPassword;

		Timestamp createTimestamp;
		Timestamp startTimestamp;
		Timestamp expireTimestamp;

		Integer nbClickInt;

		boolean isCaptcha;
		boolean generic;

		if (longUrlString.contains("mylinks")) {
			map[0] = "alert";
			map[1] = "Why do you want to create a link on this site? : " + longUrlString;
		} else if (isNotUrlString(longUrlString)) {
			map[0] = "alert";
			map[1] = "This is not an URL: " + longUrlString;
		} else {
			try {
				generic = (personalUrlString == null ||personalUrlString.isEmpty());
				shortUrl = (generic) ? urlGenerator.newUrl() : personalUrlString;
				createTimestamp = new Timestamp(new Date().getTime());
				startTimestamp = getTimestampFromString(startDateString, true);
				expireTimestamp = getTimestampFromString(expireDateString, false);
				urlPassword = (urlPasswordString == null || urlPasswordString.isEmpty()) ? null : urlPasswordString;
				isCaptcha = captcha.equals("true");
				nbClickInt = (nbClickString != null && !nbClickString.isEmpty()) ? Integer.parseInt(nbClickString) : null;
				UrlBean uBean = new UrlBean(shortUrl, longUrlString, createTimestamp, startTimestamp, expireTimestamp,
						urlPassword, isCaptcha, nbClickInt, userId, generic);
				if (!generic && urlDao.shortUrlExist(shortUrl)) {
					map[0] = "alert";
					map[1] = "This URL already exist in database: " + shortUrl;
				} else if (startTimestamp != null && expireTimestamp != null && startTimestamp.after(expireTimestamp)) {
					map[0] = "alert";
					map[1] = "Start date can't be after end date!";
				} else {
					urlDao.putUrl(uBean);
					map[0] = "shortUrl";
					map[1] = hostString + shortUrl;
				}
			} catch (ParseException e) {
				System.out.println(expireDateString);
				map[0] = "alert";
				map[1] = "There is a problem with your date format";
			}
		}
		return map;
	}

	public boolean isNotUrlString(String longUrlString) {
		URL url;
		try {
			url = new URL(longUrlString);
		} catch (MalformedURLException e) {
			url = null;
		}
		return url == null;
	}

	public Timestamp getTimestampFromString(String dateString, boolean startDate) throws ParseException {
		Timestamp res = null;
		if (dateString != null && !dateString.isEmpty()) {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
					.parse(dateString + ((startDate) ? " 00:00:00.000" : " 23:59:59.000"));
			res = new Timestamp(date.getTime());
		}
		return res;
	}

	public boolean isLiveUrl(String longUrl) {
		
		int code = 404;
		URL u;
		try {
			u = new URL("http://www.example.com/");
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection huc = (HttpURLConnection) u.openConnection();
			HttpURLConnection.setFollowRedirects(false);
			huc.setRequestMethod("HEAD"); // OR huc.setRequestMethod ("GET");
			huc.connect();
			code = huc.getResponseCode();
			System.out.println(code);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return code == 200;
	}
}
