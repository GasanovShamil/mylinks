package servlets;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UrlBean;
import beans.UrlGenerator;
import beans.UserBean;
import dao.UrlDao;

/**
 * Servlet implementation class CreateUrlServlet
 */
@WebServlet("/createUrl")
public class CreateUrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	UrlGenerator urlGenerator;

	@Inject
	UrlDao urlDao;

	public CreateUrlServlet() {
		super();
	}

	// String shortUrl,String longUrl, Timestamp createDate, Timestamp
	// expireDate, float nbClicks, float userId

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		String shortUrl;
		String longUrlString = (String) request.getParameter("longUrl");
		Timestamp createTimestamp = null;
		Timestamp expireTimestamp = null;
		String personalUrlString = (String) request.getParameter("personalUrl");
		String urlPasswordString = (String) request.getParameter("urlPassword");
		urlPasswordString = (urlPasswordString.isEmpty()) ? null : urlPasswordString;
		String expireDateString = (String) request.getParameter("expireDate");
		String nbClickString = (String) request.getParameter("nbClick");
		Integer nbClickInt;
		Integer userId;
		boolean generic;

		if (longUrlString.contains("mylinks")) {
			request.setAttribute("alert", "Why do you want to create a link on this site? : " + longUrlString);
			getServletContext().getRequestDispatcher("/").forward(request, response);
		} else {
			try {
				URL testLongUrl = new URL(longUrlString);
				generic = (personalUrlString.isEmpty());
				shortUrl = (generic) ? urlGenerator.newUrl() : personalUrlString;
				createTimestamp = new Timestamp(new Date().getTime());
				if (!expireDateString.isEmpty()) {
					Date expireDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
							.parse(expireDateString + " 23:59:59.000");
					expireTimestamp = new Timestamp(expireDate.getTime());
				}
				nbClickInt = (!nbClickString.isEmpty()) ? Integer.parseInt(nbClickString) : null;
				userId = (user != null) ? user.getUserId() : null;
				UrlBean uBean = new UrlBean(shortUrl, longUrlString, createTimestamp, expireTimestamp,
						urlPasswordString, nbClickInt, userId, generic);

				if (!generic && urlDao.shortUrlExist(shortUrl)) {
					request.setAttribute("alert", "This URL already exist in database: " + shortUrl);
					getServletContext().getRequestDispatcher("/").forward(request, response);
				} else {
					urlDao.putUrl(uBean);
					request.setAttribute("shortUrl", "http://localhost:8080/mylinks/" + shortUrl);
					getServletContext().getRequestDispatcher("/").forward(request, response);
				}
			} catch (MalformedURLException e) {
				request.setAttribute("alert", "This is not an URL: " + longUrlString);
				getServletContext().getRequestDispatcher("/").forward(request, response);

			} catch (ParseException e) {
				System.out.println(expireDateString);
				request.setAttribute("alert", "There is a problem with your date format");
				getServletContext().getRequestDispatcher("/").forward(request, response);
			}
		}
	}
}
