package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UrlBean;
import beans.UserBean;
import dao.UrlDao;
import utils.UrlUtil;

@WebServlet("/manageUrl")
public class ManageUrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	UrlDao urlDao;

	@Inject
	UrlUtil urlUtil;
	
	public ManageUrlServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("index");
		} else {
			List<UrlBean> urlBeans = urlDao.getUrlsByUserId(user.getUserId());
			request.setAttribute("urlList", urlBeans);
			getServletContext().getRequestDispatcher("/WEB-INF/urlManagementPage.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("index");
		} else {
			String shortUrl = request.getParameter("shortUrl");
			String urlPassword = request.getParameter("urlPassword");
			String startDateString = request.getParameter("startDate");
			String expireDateString = request.getParameter("expireDate");
			String captchaCheckbox = (String) request.getParameter("captchaCheckbox");
			Timestamp startTimestamp = null;
			Timestamp expireTimestamp = null;
			
				try {
					startTimestamp = urlUtil.getTimestampFromString(startDateString, true);;
					expireTimestamp = urlUtil.getTimestampFromString(expireDateString, false);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			
			String nbClickString = request.getParameter("nbClick");
			Integer nbClickInt = (!nbClickString.isEmpty()) ? Integer.parseInt(nbClickString) : null;
			UrlBean url = urlDao.getUrl(shortUrl);
			url.setStartDate(startTimestamp);
			url.setExpireDate(expireTimestamp);
			url.setPassword((urlPassword.isEmpty())?null:urlPassword);
			url.setNbClicks(nbClickInt);
			url.setCaptcha((captchaCheckbox != null && !captchaCheckbox.isEmpty())?true:false);
			urlDao.updateUrl(url);
//			getServletContext().getRequestDispatcher("/WEB-INF/urlManagementPage.jsp").forward(request, response);
			doGet(request, response);
			

		}
	}

}
