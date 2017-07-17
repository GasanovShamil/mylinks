package servlets;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UrlBean;
import dao.UrlDao;
import utils.CaptchaUtil;
import utils.UrlUtil;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	UrlDao urlDao;

	@Inject
	UrlUtil urlUtil;

	public RedirectServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(InetAddress.getLoopbackAddress());
		String shortUrl = (String) request.getAttribute("url");
		// if (shortUrl == null) shortUrl = request.getParameter("shortUrl");
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		String userAgent = request.getHeader("User-Agent");
		UrlBean urlBean = null;
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		if (shortUrl == null) {
			response.sendRedirect("index");
		} else if (shortUrl.endsWith("~s")) {
			String splittedUrl = shortUrl.substring(0, shortUrl.length() - 2);
			urlBean = urlDao.getUrl(splittedUrl);
			if (urlBean == null) {
				request.setAttribute("message", splittedUrl);
				getServletContext().getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(request, response);
			} else {
				request.setAttribute("statsUrl", splittedUrl);
				getServletContext().getRequestDispatcher("/WEB-INF/urlStats.jsp").forward(request, response);
			}
		} else {
			urlBean = urlDao.getUrl(shortUrl);
			if (urlBean == null) {
				request.setAttribute("alert", shortUrl);
				getServletContext().getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(request, response);
			} else if (urlBean.dateExpired()) {
				request.setAttribute("alert", "Expired URL: " + shortUrl);
				getServletContext().getRequestDispatcher("/").forward(request, response);
			} else if (urlBean.getPassword() != null || urlBean.isCaptcha()) {
				request.setAttribute("shortUrl", shortUrl);
				request.setAttribute("password", urlBean.getPassword() != null);
				request.setAttribute("captcha", urlBean.isCaptcha());
				getServletContext().getRequestDispatcher("/WEB-INF/urlPasswordPage.jsp").forward(request, response);
			} else if (urlBean.getTotalClicks() != null) {
				if (urlBean.getNbClicks() >= urlBean.getTotalClicks()) {
					request.setAttribute("alert", "Number of visits on this URL was expired: " + shortUrl);
					getServletContext().getRequestDispatcher("/").forward(request, response);
				} else {
					urlBean.increaseNbClicks();
					urlDao.updateUrl(urlBean);
					urlUtil.putStats(urlBean.getShortUrl(), ipAddress, userAgent);
					response.sendRedirect(urlBean.getLongUrl());
				}
			} else {
				urlBean.increaseNbClicks();
				urlDao.updateUrl(urlBean);
				urlUtil.putStats(urlBean.getShortUrl(), ipAddress, userAgent);
				response.sendRedirect(urlBean.getLongUrl());
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String shortUrl = (String) request.getParameter("url");
		String urlPassword = (String) request.getParameter("urlPassword");
		String captchaResponse = request.getParameter("g-recaptcha-response");
		UrlBean bean = urlDao.getUrl(shortUrl);
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		String userAgent = request.getHeader("User-Agent");
		if (urlPassword != null && !urlPassword.equals(bean.getPassword())) {
			request.setAttribute("alert", "Wrong password! Please try again :)");
			request.setAttribute("password", true);
			request.setAttribute("captcha", captchaResponse != null);
			request.setAttribute("shortUrl", shortUrl);
			getServletContext().getRequestDispatcher("/WEB-INF/urlPasswordPage.jsp").forward(request, response);
		} else if (captchaResponse != null && !CaptchaUtil.verify(captchaResponse)) {
			request.setAttribute("alert", "Please check \"I am not a robot\"! ");
			request.setAttribute("password", urlPassword != null);
			request.setAttribute("captcha", true);
			request.setAttribute("shortUrl", shortUrl);
			getServletContext().getRequestDispatcher("/WEB-INF/urlPasswordPage.jsp").forward(request, response);
		} else if (bean.getTotalClicks() != null) {
			if (bean.getNbClicks() >= bean.getTotalClicks()) {
				request.setAttribute("alert", "Number of visits on this URL was expired: " + shortUrl);
				getServletContext().getRequestDispatcher("/").forward(request, response);
			} else {
				bean.increaseNbClicks();
				urlDao.updateUrl(bean);
				urlUtil.putStats(bean.getShortUrl(), ipAddress, userAgent);
				response.sendRedirect(bean.getLongUrl());
			}
		} else {
			bean.increaseNbClicks();
			urlDao.updateUrl(bean);
			urlUtil.putStats(bean.getShortUrl(), ipAddress, userAgent);
			response.sendRedirect(bean.getLongUrl());
		}
	}
}
