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
import dao.UserDao;
import utils.Database;
import utils.HibernateUtil;

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
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserBean user = userDao.getUser(email);
		if (user == null) {
			request.setAttribute("alert", "There is no such user");
			getServletContext().getRequestDispatcher("/").forward(request, response);
		} else {
			if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
				if (!user.isConfirmed()) {
					request.setAttribute("alert", "Please check your email to confirm your address.");
					getServletContext().getRequestDispatcher("/").forward(request, response);
				} else {
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					session.setMaxInactiveInterval(30 * 60);
					response.sendRedirect("index");
				}
			} else {
				request.setAttribute("alert", "Login or password are incorrect");
				getServletContext().getRequestDispatcher("/").forward(request, response);
			}
		}

	}

}
