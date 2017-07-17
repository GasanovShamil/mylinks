package servlets;

import java.io.IOException;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserBean;
import dao.UserDao;
import utils.PasswordUtil;

/**
 * Servlet implementation class AccountManagementServlet
 */
@WebServlet("/account")
public class AccountManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	UserDao userDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountManagementServlet() {
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
		if (user == null) {
			request.setAttribute("alert", "Please log in to see your profile!");
			getServletContext().getRequestDispatcher("/").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/WEB-INF/accountPage.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("alert", "Please log in to update your profile!");
			getServletContext().getRequestDispatcher("/").forward(request, response);
		} else {
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String password = request.getParameter("password");
			String repassword = request.getParameter("repassword");

			if (!password.equals(repassword)) {
				request.setAttribute("alert", "passwords don't match");
				getServletContext().getRequestDispatcher("/WEB-INF/accountPage.jsp").forward(request, response);
			} else {
				user.setName(name);
				user.setSurname(surname);
				if(!password.isEmpty())	user.setPassword(PasswordUtil.getHash(password));
				userDao.updateUser(user);
				request.setAttribute("message", "Account updated successfully");
				getServletContext().getRequestDispatcher("/WEB-INF/accountPage.jsp").forward(request, response);
			}
		}
	}

}
