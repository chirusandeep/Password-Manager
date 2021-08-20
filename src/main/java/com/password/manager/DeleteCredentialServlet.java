package com.password.manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.password.manager.dao.CredentialDAO;

/**
 * Servlet implementation class DeleteCredentialServlet
 */
@WebServlet(name = "DeleteCredentialServlet", value = "/delete-credential")
public class DeleteCredentialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCredentialServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			Integer userid = (Integer) request.getSession().getAttribute("userID");
			if (userid == null) {
				response.sendRedirect("login.jsp");
				return;
			}
			CredentialDAO creddao = new CredentialDAO();
			creddao.deleteCredential(userid, id);
			return;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.sendError(400);
		}

	}

}
