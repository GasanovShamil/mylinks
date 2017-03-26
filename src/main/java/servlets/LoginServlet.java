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

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;   
	
	@Inject
	Database db;
  
    public LoginServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String login = request.getParameter("login");
		String password = request.getParameter("password");	
		try {
			
			Connection conn = db.getConnection();
			String preparednSQLquery = "SELECT * FROM users WHERE login = ? "; 
			PreparedStatement pst = conn.prepareStatement(preparednSQLquery );
			pst.setString(1, login);
			
			ResultSet res = pst.executeQuery();
			
			if (!res.next()){
				request.setAttribute("alert", "There is no such user");
				getServletContext().getRequestDispatcher("/").forward(request,response);
			}else{
				if (res.getString(4).equals(login) && res.getString(5).equals(password)){
					HttpSession session = request.getSession();
					UserBean user = new UserBean();
					user.setUserId(res.getInt(1));
					user.setName(res.getString(2));
					user.setSurname(res.getString(3));
					user.setLogin(res.getString(4));
					session.setAttribute("user", user);
					session.setMaxInactiveInterval(30*60);
					res.close();
					conn.close();
					response.sendRedirect("index");
//					getServletContext().getRequestDispatcher("/").forward(request,response);
				}else{
					res.close();
					conn.close();
					request.setAttribute("alert", "Login or password are incorrect");
					getServletContext().getRequestDispatcher("/").forward(request,response);
				}
			}
			res.close();
			conn.close();
		} catch (SQLException e) { 
			System.out.println("blabla");
			e.printStackTrace();
		}	
	}

}

