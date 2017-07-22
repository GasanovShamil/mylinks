package servlets;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import beans.UrlBean;
import beans.UserBean;
import dao.StatsDao;
import dao.UrlDao;

/**
 * Servlet implementation class StatServlet
 */
@WebServlet("/stats")
public class StatsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	UrlDao urlDao;

	@Inject
	StatsDao statsDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StatsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		if (user == null || !user.isAdmin()) {
			response.sendRedirect("index");
		} else {
			getServletContext().getRequestDispatcher("/WEB-INF/globalStats.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("charset", "utf-8");
		JSONObject json = new JSONObject();
		
		String shortUrl = (String) request.getParameter("url");
		
		if (shortUrl == null) {
			json.put("security", new Object[] {
				statsDao.getPasswordUrls(),
				statsDao.getCaptchaUrls()
			});
			
			json.put("browserByCountry", statsDao.getBrowserByCountry());
			
			json.put("popularity", statsDao.getPopularity());
		} else {
			UrlBean urlBean = urlDao.getUrl(shortUrl);
			
			if (urlBean != null) {
				json.put("usedLeftRatio", new Object[][] {
					{ "Click Used", urlBean.getNbClicks() },
					{ "Click Left", urlBean.getTotalClicks() - urlBean.getNbClicks() }
				});

				json.put("evolutionOverTime", statsDao.getEvolutionOverTime(shortUrl));
			}
		}

		String result = json.toString();
		response.getWriter().append(result);
	}

}
