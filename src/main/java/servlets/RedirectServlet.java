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
		request.getRemoteAddr();
		String shortUrl = (String) request.getAttribute("url");
		if (shortUrl == null) {
			response.sendRedirect("index");
		} else if (shortUrl.endsWith("~s") || shortUrl.endsWith("!")) {
			response.getWriter().append("TODO : here we will show stats for each short url");
		} else {
			UrlBean bean = urlDao.getUrl(shortUrl);
			if (bean != null) {
//				String ipAddress = request.getHeader("X-FORWARDED-FOR");
//				if (ipAddress == null) {
//					   ipAddress = request.getRemoteAddr();
//				}
				response.sendRedirect(bean.getLongUrl());
			} else {
				request.setAttribute("alert", "There is no such url: " + shortUrl);
				getServletContext().getRequestDispatcher("/").forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
