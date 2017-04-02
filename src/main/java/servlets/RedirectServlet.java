package servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UrlBean;
import dao.UrlDao;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	UrlDao urlDao;

	public RedirectServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String shortUrl = (String) request.getAttribute("url");
		if (shortUrl == null) request.getParameter("shortUrl");

		if (shortUrl == null) {
			response.sendRedirect("index");
		} else if (shortUrl.endsWith("~s") || shortUrl.endsWith("!")) {
			response.getWriter().append("TODO : here we will show stats for each short url");
		} else {
			UrlBean bean = urlDao.getUrl(shortUrl);
			if (bean == null) {
				request.setAttribute("alert", "There is no such url: " + shortUrl);
				getServletContext().getRequestDispatcher("/").forward(request, response);
			} else if (bean.dateExpired()) {
				request.setAttribute("alert", "Expired URL: " + shortUrl);
				getServletContext().getRequestDispatcher("/").forward(request, response);
			} else if (bean.getPassword() != null) {
					request.setAttribute("shortUrl", shortUrl);
					getServletContext().getRequestDispatcher("/WEB-INF/urlPasswordPage.jsp").forward(request, response);
				
			} else if (bean.getNbClicks() != null) {
				if (bean.getNbClicks() <= 0) {
					request.setAttribute("alert", "Number of visits on this URL was expired: " + shortUrl);
					getServletContext().getRequestDispatcher("/").forward(request, response);
				} else {
					bean.decreaseNbClicks();
					urlDao.updateUrl(bean);
					response.sendRedirect(bean.getLongUrl());
				}
			} else {
				response.sendRedirect(bean.getLongUrl());
			}
		}
	}

	// String ipAddress = request.getHeader("X-FORWARDED-FOR");
	// if (ipAddress == null) {
	// ipAddress = request.getRemoteAddr();
	// }
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String shortUrl = (String) request.getParameter("url");
		String urlPassword = (String) request.getParameter("urlPassword");
		UrlBean bean = urlDao.getUrl(shortUrl);
		if (urlPassword == null || !urlPassword.equals(bean.getPassword())) {
			request.setAttribute("alert", "Wrong password! Please try again :)");
			request.setAttribute("shortUrl", shortUrl);
			getServletContext().getRequestDispatcher("/WEB-INF/urlPasswordPage.jsp").forward(request, response);
		}else if (bean.getNbClicks() != null) {
			if (bean.getNbClicks() <= 0) {
				request.setAttribute("alert", "Number of visits on this URL was expired: " + shortUrl);
				getServletContext().getRequestDispatcher("/").forward(request, response);
			} else {
				bean.decreaseNbClicks();
				urlDao.updateUrl(bean);
				response.sendRedirect(bean.getLongUrl());
			}
		} else {
			response.sendRedirect(bean.getLongUrl());
		}
	}

}
