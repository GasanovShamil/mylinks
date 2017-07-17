package servlets;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UrlBean;
import beans.UserBean;
import dao.UrlDao;
import utils.ConfigClass;
import utils.UrlGenerator;
import utils.UrlUtil;

/**
 * Servlet implementation class CreateUrlServlet
 */
@WebServlet("/createUrl")
public class CreateUrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject 
	UrlUtil urlUtil; 
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
		Integer userId = (user != null)?user.getUserId():null;
		String personalUrlString = (String) request.getParameter("personalUrl");
		String passwordCheckbox = (String) request.getParameter("passwordCheckbox");	
		String urlPasswordString =(passwordCheckbox!= null && !passwordCheckbox.isEmpty())?(String) request.getParameter("urlPassword"):null;
		String startDateString = (String) request.getParameter("startDate");
		String expireDateString = (String) request.getParameter("expireDate");
		String nbClickString = (String) request.getParameter("nbClick");
		String longUrlString = (String) request.getParameter("longUrl");
		String captchaCheckbox = (String) request.getParameter("captchaCheckbox");
		String captcha = (captchaCheckbox != null && !captchaCheckbox.isEmpty())?captchaCheckbox:"false";
		String[] res = urlUtil.createUrl(personalUrlString, longUrlString, startDateString, expireDateString, urlPasswordString, captcha, nbClickString, userId);
		request.setAttribute(res[0], res[1]);
		getServletContext().getRequestDispatcher("/").forward(request, response);
			}
	
	
}
