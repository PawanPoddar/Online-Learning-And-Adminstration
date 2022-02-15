package com.evidhya.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/register")
public class registration extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int course_id=1;
	@Override

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String user_id = req.getParameter("user_id");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String pin = req.getParameter("pin");
		String password = req.getParameter("password");

		
		try {
			Connection connection = com.evidhya.utilities.DBConnection.getConnection();
			boolean isValid=true;
			String message="";
			if(user_id==null || email.isEmpty()) {
				isValid=false;
				message="Please enter fill all the field";
			} /*else if (teach_id==0) {
				isValid=false;
				message="Please entre teacher name";
			}*/ else if (phone==null || pin.isEmpty()) {
				isValid=false;
				message="Please fill all the field";
			}
			else if (password==null || name.isEmpty()) {
				isValid=false;
				message="Please fill all the field";
			}
			
			if(!isValid) {
				req.setAttribute("message", message);
				req.getRequestDispatcher("action/lecture").include(req, resp);
			} else {
				try {
					
				PreparedStatement stmt = connection.prepareStatement("insert into register(name,user_id,email,phone,pin,password) values(?,?,?,?,?,?)");
				stmt.setString(1, name);
				stmt.setString(2, user_id);
				stmt.setString(3, email);
				stmt.setString(4, phone);
				stmt.setString(5, pin);
				stmt.setString(6, password);

				int i = stmt.executeUpdate();
				System.out.println("Record Updated: "+i);
				message="Successfully Registered";
				req.getRequestDispatcher("action/registerForm").forward(req, resp);
				
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
