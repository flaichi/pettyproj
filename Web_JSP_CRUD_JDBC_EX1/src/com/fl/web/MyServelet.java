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
		
		 
	}

	private void editform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	}

	private void userform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		
	}


	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		 

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
		 

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
