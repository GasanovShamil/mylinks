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
import utils.MailUtil;
import utils.PasswordUtil;

@WebServlet("/createUser")
public class CreateConfirmUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	UserDao userDao;

	@Inject
	MailUtil mailer;

	public CreateConfirmUserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String confirmToken = request.getParameter("confirmToken");
		UserBean user = userDao.getUser(email);
		if (email == null || confirmToken == null || user == null || !user.getConfirmToken().equals(confirmToken) || user.isConfirmed()) {
			response.sendRedirect("index");
		} else {
			user.setConfirmed(true);
			userDao.updateUser(user);
			request.setAttribute("alert", "Thanks. You can use your account now.");
			getServletContext().getRequestDispatcher("/").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");

		if (!password.equals(repassword)) {
			request.setAttribute("alert", "passwords don't match");
			getServletContext().getRequestDispatcher("/").forward(request, response);
		} else if (userDao.getUser(email) != null) {
			request.setAttribute("alert", "This email already used.");
			getServletContext().getRequestDispatcher("/").forward(request, response);
		} else {
			String confirmToken = UUID.randomUUID().toString().replaceAll("-", "");
			UserBean user = new UserBean(name, surname, email, PasswordUtil.getHash(password), confirmToken, false, false);
			userDao.putUser(user);
			mailer.sendEmail(email, name, confirmToken, 0);
			request.setAttribute("alert", "Please check your mail to confirm account.");
			getServletContext().getRequestDispatcher("/").forward(request, response);
		}

	}

}
