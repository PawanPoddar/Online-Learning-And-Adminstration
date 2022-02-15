package com.evidhya.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/update_teacher")
public class Update_Teacher extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String teach_name = req.getParameter("t_name");
		String teach_age = req.getParameter("t_age");
		String teach_spec = req.getParameter("t_spec");
		String teach_course = req.getParameter("t_course");
		String teach_pass = req.getParameter("t_password");
		String teach_repass = req.getParameter("t_repassword");
		
		try {
			Connection connection = com.evidhya.utilities.DBConnection.getConnection();
			boolean isValid=true;
			String message="";
			if(teach_name==null || teach_name.isEmpty()) {
				isValid=false;
				message="Please enter teacher name";
			} /*else if (teach_id==0) {
				isValid=false;
				message="Please entre teacher name";
			}*/ else if (teach_age==null || teach_age.isEmpty()) {
				isValid=false;
				message="Please enter teacher age";
			}
			else if (teach_spec==null || teach_spec.isEmpty()) {
				isValid=false;
				message="Please enter teacher speciality";
			}
			else if (teach_course==null || teach_course.isEmpty()) {
				isValid=false;
				message="Please enter teacher course";
			}
			else if (teach_pass==null || teach_pass.isEmpty()) {
				isValid=false;
				message="Please enter teacher password";
			}
			else if (teach_pass==null || teach_pass.isEmpty()) {
				isValid=false;
				message="Please enter teacher re-password";
			}
			if(!isValid) {
				req.setAttribute("message", message);
				req.getRequestDispatcher("action/update_teacher").include(req, resp);
			} else {
				try {
				PreparedStatement stmt = connection.prepareStatement("update teacher set teacher_name=?, teacher_age=?, specialist=?, course_id=?, teacher_pass=? where teacher_id=? ");
				
				stmt.setString(1, teach_name);
				stmt.setString(2, teach_age);
				stmt.setString(3, teach_spec);
				stmt.setString(4, teach_course);
				stmt.setString(5, teach_pass);
				stmt.setString(6, id);
				int i = stmt.executeUpdate();
				System.out.println("Record Updated: "+i);
				if(i==1){
					message="Record updated";
					req.setAttribute(message, message);
					resp.sendRedirect(req.getContextPath() + "/jsp/update_teacher.jsp?id="+id);
				}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
