package com.password.manager;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.password.manager.dao.UserDAO;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(name = "RegisterServlet", value="/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.userDAO = new UserDAO();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fullname = request.getParameter("fullname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		
		
		
		if(fullname == "") {
			request.setAttribute("errorMessage", "Please check your fullname!");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		if(username == "") {
			request.setAttribute("errorMessage", "Please check your username!");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		if(password == "") {
			request.setAttribute("errorMessage", "Please check your password!");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		if(repassword == "") {
			request.setAttribute("errorMessage", "Please check your repassword!");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		if(username.length() > 50) {
			request.setAttribute("errorMessage", "Username lenghth should be below 50.");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		
		if(password.length() > 40) {
			request.setAttribute("errorMessage", "Password lenghth should be below 40.");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		if(!password.equals(repassword)) {
			request.setAttribute("errorMessage", "Password mismatch!");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		if(userDAO.isUserExists(username)) {
			request.setAttribute("errorMessage", "User Already Exists!");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		try {
			userDAO.saveUser(fullname, username, password);
			request.setAttribute("successMessage", "User Registration Successfull!");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			request.setAttribute("errorMessage", "Please check your fields!");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		
	}

}
