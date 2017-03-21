package servlets;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UrlBean;
import beans.UrlGenerator;
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
		String longUrl = (String) request.getParameter("longUrl");
		if (longUrl.contains("mylinks")){
			request.setAttribute("alert", "Why do you want to create a link on this site? : " + longUrl);
			getServletContext().getRequestDispatcher("/").forward(request, response);	
		}else{
		try {
			URL testLongUrl = new URL(longUrl);
			String shortUrl = urlGenerator.newUrl();
			Date date = new Date();
			Timestamp createDate = new Timestamp(date.getTime());
			UrlBean uBean = new UrlBean(shortUrl, longUrl, createDate, null, null, null);
			urlDao.putUrl(uBean);
			request.setAttribute("succes", "Your url is : mylinks/" + shortUrl);
			getServletContext().getRequestDispatcher("/").forward(request, response);
		} catch (MalformedURLException e) {
			request.setAttribute("alert", "This is not an URL: " + longUrl);
			getServletContext().getRequestDispatcher("/").forward(request, response);

		}
		}

	}
}
