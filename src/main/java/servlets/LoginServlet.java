package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserBean;
import dao.Database;
import dao.HibernateUtil;
import dao.UserDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	HibernateUtil hibernateUtil;

	@Inject
	UserDao userDao;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("index");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		UserBean user = userDao.getUser(login);
		if (user == null) {
			request.setAttribute("alert", "There is no such user");
			getServletContext().getRequestDispatcher("/").forward(request, response);
		} else {
			if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				session.setMaxInactiveInterval(30 * 60);
				response.sendRedirect("index");
				// getServletContext().getRequestDispatcher("/").forward(request,response);
			} else {
				request.setAttribute("alert", "Login or password are incorrect");
				getServletContext().getRequestDispatcher("/").forward(request, response);
			}
		}

	}

}
