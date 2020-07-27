package com.fl.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fl.dao.UserDAO;
import com.fl.model.User;


 
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDAO userDAO = new UserDAO();

	/*
	 * public UserServlet() { super(); }
	 */

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		try {

			switch (action) {

			case "/newuser":
				userAdd(request, response);
				break;
				
			case "/userform":
				userform(request, response);
				break;

			case "/deleteuser":
				deleteUser(request, response);
				break;

			case "/edituser":
				editform(request, response);
				break;
				
			case "/updateuser":
				updateuser(request, response);
				
			case "/list":
				userList(request, response);

			default:
				userList(request, response);
				break;
			}

		} catch (Exception ex) {
			System.out.println("Exception Caught from swicth  : ");
			System.out.println(ex.getMessage());
		}
	}

	private void updateuser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("Hi Update..");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		System.out.println("Hi Update.2.");
		User book = new User(id, name, email, country);
		userDAO.updateUser(book);
		response.sendRedirect("list");
	}

	private void editform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = userDAO.selectUser(id);
		RequestDispatcher rd = request.getRequestDispatcher("user-form2.jsp");
		request.setAttribute("user", existingUser);
		System.out.println("showEditForm method ...");
		rd.forward(request, response);
	}

	private void userform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("userFrom ...");
		RequestDispatcher rd = request.getRequestDispatcher("user-form2.jsp");
		rd.forward(request, response);
		
	}


	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		if(userDAO.deleteUser(id))
			response.sendRedirect("list");
		else
			System.out.println("There is some error in deleting user.");

	}

	private void userList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, ClassNotFoundException {

		List<User> userList = userDAO.selectAllUsers();
		request.setAttribute("listUser", userList);
		
		System.out.println(userList);
		
		RequestDispatcher rd = request.getRequestDispatcher("user-list.jsp");
		rd.forward(request, response);

	}

	private void userAdd(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String name=request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		User newUser = new User(name, email, country);
		userDAO.insertUser(newUser);
		response.sendRedirect("list");

	}

	/*
	 * protected void doPost(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { // TODO Auto-generated
	 * method stub doGet(request, response); }
	 */

}
