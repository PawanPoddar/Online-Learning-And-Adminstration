package com.evidhya.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/query")
public class Query extends HttpServlet{
	int i_id=1;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user_name = req.getParameter("user_name");
		String email = req.getParameter("email");
		String mobile = req.getParameter("mobile");
		String messages = req.getParameter("message");
		
		try {
			Connection connection = com.evidhya.utilities.DBConnection.getConnection();
			boolean isValid=true;
			String message="";
			if(user_name==null || user_name.isEmpty()) {
				isValid=false;
				message="Please enter user name";
			} else if (email==null || email.isEmpty()) {
				isValid=false;
				message="Please enter email";
			}
			else if (mobile==null || mobile.isEmpty()) {
				isValid=false;
				message="Please enter mobile";
			}else if (messages==null || messages.isEmpty()) {
				isValid=false;
				message="Please enter message";
			}
			if(!isValid) {
				req.setAttribute("message", message);
				req.getRequestDispatcher("action/add_course").include(req, resp);
			} else {
				try {
				PreparedStatement stmt = connection.prepareStatement("insert into enquiry(e_id,user_name,user_email,user_mob,enquiry) values(?,?,?,?,?)");
				stmt.setInt(1, i_id);
				stmt.setString(2, user_name);
				stmt.setString(3, email);
				stmt.setString(4, mobile);
				stmt.setString(5, message);
				int i = stmt.executeUpdate();
				System.out.println("Record Updated: "+i);
				i_id++;
				req.getRequestDispatcher("/action/add_course").forward(req, resp);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
}
