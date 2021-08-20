package com.password.manager;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.password.manager.dao.CredentialDAO;

/**
 * Servlet implementation class AddCredentialServlet
 */
@WebServlet(name = "AddCredentialServlet",value="/credentials")
public class AddCredentialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CredentialDAO credentialDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCredentialServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.credentialDAO = new CredentialDAO();
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if(session.getAttribute("loggedInUser")== null) {
			response.sendRedirect("login.jsp");
			return;
		}
		Integer userId = (Integer) session.getAttribute("userID");
		String website = request.getParameter("website");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(website.length() > 40) {
			request.setAttribute("errorMessage", "Website length should be below 40!");
			request.getRequestDispatcher("add-credential.jsp").forward(request, response);
			return;
		}
		if(username.length() > 60) {
			request.setAttribute("errorMessage", "Username length should be below 60!");
			request.getRequestDispatcher("add-credential.jsp").forward(request, response);
			return;
		}
		if(password.length() > 60) {
			request.setAttribute("errorMessage", "Password length should be below 60!");
			request.getRequestDispatcher("add-credential.jsp").forward(request, response);
			return;
		}
		try {
			credentialDAO.saveCredential(userId, website, username, password);
			request.setAttribute("message", "Credential Added successfull!");
			request.getRequestDispatcher("add-credential.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			request.setAttribute("errorMessage", "Credential Failed to add!");
			request.getRequestDispatcher("add-credential.jsp").forward(request, response);
		}
		
	}

}
