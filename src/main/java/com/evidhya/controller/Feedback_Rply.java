package com.evidhya.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/feed_rply")
public class Feedback_Rply extends HttpServlet{
	int i_id=1;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fd_name = req.getParameter("fd_name");
		String fd_email = req.getParameter("fd_email");
	//	String mobile = req.getParameter("mobile");
		String messages = req.getParameter("message");
		
		try {
			Connection connection = com.evidhya.utilities.DBConnection.getConnection();
			boolean isValid=true;
			String message="";
			if(fd_name==null || fd_name.isEmpty()) {
				isValid=false;
				message="Please enter feedback name";
			} else if (fd_email==null || fd_email.isEmpty()) {
				isValid=false;
				message="Please enter email";
			}
			else if (messages==null || messages.isEmpty()) {
				isValid=false;
				message="Please entre message";
			}
			if(!isValid) {
				req.setAttribute("message", message);
				req.getRequestDispatcher("action/feedback_rply").include(req, resp);
			} else {
				try {
				PreparedStatement stmt = connection.prepareStatement("insert into feedback(f_id,user_id,user_name,f_description) values(?,?,?,?)");
				stmt.setInt(1,i_id);
				stmt.setString(2, fd_name);
				stmt.setString(3, fd_email);
				//stmt.setString(4, mobile);
				stmt.setString(4, messages);
				int i = stmt.executeUpdate();
				System.out.println("Record Updated: "+i);
				i_id++;
				req.getRequestDispatcher("/action/feedback_rply").forward(req, resp);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
}
