package com.fl.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */

//@WebServlet("Login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String uname = request.getParameter("uname");
				String upass = request.getParameter("upass");
				
				if(uname.equals("admin") && upass.equals("admin")) {
					HttpSession session = request.getSession();
					session.setAttribute("username", uname);
					response.sendRedirect("Welcome.jsp");
					
				}
				else
				{
					
					
					response.sendRedirect("login.jsp");
				}
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
