package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import beans.ClickBean;
import beans.UrlBean;
import beans.UserBean;
import dao.ClickDao;
import dao.UrlDao;

@Named
@RequestScoped
public class UrlUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	UrlGenerator urlGenerator;

	@Inject
	ClickDao statDao;

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
				generic = (personalUrlString == null || personalUrlString.isEmpty());
				shortUrl = (generic) ? urlGenerator.newUrl() : personalUrlString;
				createTimestamp = new Timestamp(new Date().getTime());
				startTimestamp = getTimestampFromString(startDateString, true);
				expireTimestamp = getTimestampFromString(expireDateString, false);
				urlPassword = (urlPasswordString == null || urlPasswordString.isEmpty()) ? null : urlPasswordString;
				isCaptcha = captcha.equals("true");
				nbClickInt = (nbClickString != null && !nbClickString.isEmpty()) ? Integer.parseInt(nbClickString)
						: null;
				UrlBean uBean = new UrlBean(shortUrl, longUrlString, createTimestamp, startTimestamp, expireTimestamp,
						urlPassword, isCaptcha, 0, nbClickInt, userId, generic);
				if (!generic && (urlDao.shortUrlExist(shortUrl) || ConfigClass.USED_URLS_LIST.contains(shortUrl)
						|| shortUrl.endsWith("~s"))) {
					map[0] = "alert";
					map[1] = "This URL already exist in database: " + shortUrl;
				} else if(shortUrl.contains("/")){
					map[0] = "alert";
					map[1] = "URL can not contain slash: " + shortUrl;
				}else if (startTimestamp != null && expireTimestamp != null && startTimestamp.after(expireTimestamp)) {
				
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

	public void putStats(String shortUrl, String ipAddress, String userAgent) {
		HashMap<String,String> osBrowser =  this.getOsBrowser(userAgent);
		ClickBean statBean = new ClickBean(shortUrl, ipAddress, getIpCountry(ipAddress), osBrowser.get("browser"), osBrowser.get("os"));
		statDao.putStat(statBean);

	}

	public String getIpCountry(String ip) {
		StringBuilder sb = new StringBuilder();

		InputStreamReader in = null;

		URL u;
		try {
			u = new URL("http://freegeoip.net/json/" + ip);
			HttpURLConnection.setFollowRedirects(true);
			HttpURLConnection huc = (HttpURLConnection) u.openConnection();
			huc.setRequestMethod("GET");
			huc.connect();
			in = new InputStreamReader(huc.getInputStream(), Charset.defaultCharset());
			BufferedReader bufferedReader = new BufferedReader(in);
			if (bufferedReader != null) {
				int cp;
				while ((cp = bufferedReader.read()) != -1) {
					sb.append((char) cp);
				}
				bufferedReader.close();
			}
			in.close();
			System.out.println(sb);
			JSONObject jsonObj = new JSONObject(sb.toString());
			String res = jsonObj.getString("country_name");
			return (res != null && !res.isEmpty())?res:null;

		} catch (MalformedURLException e) {
			return null;
		} catch (ProtocolException e) {
			return null;
		} catch (IOException e) {
			return null;
		}

	}

	public HashMap<String, String> getOsBrowser(String userAgent) {
		// String browserDetails = request.getHeader("User-Agent");
		String user = userAgent.toLowerCase();
		HashMap<String, String> result = new HashMap<>();

		String os = "";
		String browser = "";

		// =================OS=======================
		if (userAgent.toLowerCase().indexOf("windows") >= 0) {
			os = "Windows";
		} else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
			os = "Mac";
		} else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
			os = "Unix";
		} else if (userAgent.toLowerCase().indexOf("android") >= 0) {
			os = "Android";
		} else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
			os = "IPhone";
		} else {
			// os = "UnKnown, More-Info: "+userAgent;
			os = "UnKnown";
		}
		// ===============Browser===========================
		if (user.contains("msie")) {
			String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
			browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
		} else if (user.contains("safari") && user.contains("version")) {
			browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0];
		} else if (user.contains("opr") || user.contains("opera")) {
			if (user.contains("opera"))
				browser = (userAgent.substring(userAgent.indexOf("Opera")).split("/")[0]);
			else if (user.contains("opr"))
				browser = ((userAgent.substring(userAgent.indexOf("OPR")).split("/")[0]).replace("OPR", "Opera"));
		} else if (user.contains("chrome")) {
			browser = (userAgent.substring(userAgent.indexOf("Chrome")).split("/")[0]);
		} else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)
				|| (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1)
				|| (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
			// browser=(userAgent.substring(userAgent.indexOf("MSIE")).split("
			// ")[0]).replace("/", "-");
			browser = "Netscape-?";

		} else if (user.contains("firefox")) {
			browser = (userAgent.substring(userAgent.indexOf("Firefox")).split("/")[0]);
		} else if (user.contains("rv")) {
			browser = "IE-" + user.substring(user.indexOf("rv") + 3, user.indexOf(")"));
		} else {
			// browser = "UnKnown, More-Info: "+userAgent;
			browser = "UnKnown";
		}
		result.put("os", os);
		result.put("browser", browser);

		return result;
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
