package com.password.manager;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.password.manager.dao.UserDAO;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name= "LoginServlet", value="/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
       
    /**
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() throws ClassNotFoundException, SQLException {
        super();
        this.userDAO = new UserDAO();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Integer userid = this.userDAO.isValidUser(username, password);
		if (userid != null) {
			session.setAttribute("userID", userid);
			session.setAttribute("loggedInUser", username);
			response.sendRedirect("index.jsp");
		} else {
			request.setAttribute("message", "Invalid username/password!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

}
