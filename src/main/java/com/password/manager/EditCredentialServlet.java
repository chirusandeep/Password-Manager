package com.password.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.password.manager.dao.CredentialDAO;

/**
 * Servlet implementation class EditCredentialServlet
 */
@WebServlet(name = "EditCredentialServlet", value = "/edit-credential")
public class EditCredentialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditCredentialServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer userid = (Integer) session.getAttribute("userID");
		if (userid == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			CredentialDAO creddao = new CredentialDAO();
			if (creddao.isBelongsTo(userid, id)) {
				String website = request.getParameter("website");
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				if(website.length() > 40) {
					request.setAttribute("errorMessage", "Website length should be below 40!");
					request.getRequestDispatcher("edit-credential.jsp").forward(request, response);
					return;
				}
				if(username.length() > 60) {
					request.setAttribute("errorMessage", "Username length should be below 60!");
					request.getRequestDispatcher("edit-credential.jsp").forward(request, response);
					return;
				}
				if(password.length() > 60) {
					request.setAttribute("errorMessage", "Password length should be below 60!");
					request.getRequestDispatcher("edit-credential.jsp").forward(request, response);
					return;
				}
				creddao.updateCredential(id, website, username, password, userid);
				request.setAttribute("message", "Successfully updated!");
				request.getRequestDispatcher("edit-credential.jsp").forward(request, response);
				return;
			}
			request.setAttribute("errorMessage", "Failed to update credential!");
			request.getRequestDispatcher("edit-credential.jsp").forward(request, response);
			return;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			request.setAttribute("errorMessage", "Failed to update credential!");
			request.getRequestDispatcher("edit-credential.jsp").forward(request, response);
			return;
		}
	}

}
