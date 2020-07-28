package com.fl.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fl.model.User;
import com.fl.userDAO.UserDAO;

@WebServlet("/")
public class MyServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private UserDAO userDAO;
    
    public MyServelet() {
        super();
        userDAO = new UserDAO();
     }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		System.out.println("1  update method ...");
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("2  Hi Update..");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		System.out.println("3    Hi Update.2.");
		User book = new User(id, name, email, country);
		userDAO.updateUser(book);
		response.sendRedirect("list");
		 
	}

	private void editform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		 System.out.println("editform method ...");
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = userDAO.selectUser(id);
		 System.out.println("editform method ...");
		RequestDispatcher rd = request.getRequestDispatcher("edit-form.jsp");
		request.setAttribute("user", existingUser);
		System.out.println("editform method ...");
		rd.forward(request, response);
		System.out.println("editform method ...");
	}

	private void userform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("user-form.jsp");
		rd.forward(request, response);
		
	}


	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		 System.out.println("deleting User");
	 
		 int id=Integer.parseInt(request.getParameter("id"));	
			if(userDAO.deleteUser(id)) {;
			response.sendRedirect("list");
			}
			else {
				System.out.println("erro in ndel user");
			}
	}

	private void userList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, ClassNotFoundException {
		
		List<User> userList = userDAO.selectAllUser();
		request.setAttribute("listUser", userList);
		
		System.out.println(userList);
		
		RequestDispatcher rd = request.getRequestDispatcher("user-list.jsp");
		rd.forward(request, response);
		 

	}

	private void userAdd(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		 System.out.println("adding user...");
		 //int id=Integer.parseInt(request.getParameter(id));	
		 	String name=request.getParameter("name");
			String email = request.getParameter("email");
			String country = request.getParameter("country");
			User newUser = new User(name, email, country);
			userDAO.insertUser(newUser);
			response.sendRedirect("list");

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
