package com.evidhya.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update_course")
public class Update_Course extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String course_name = req.getParameter("crs_name");
		String course_category = req.getParameter("crs_catgry");
		String teach_name = req.getParameter("t_name");
		String crs_desc = req.getParameter("crs_des");
		
		try {
			Connection connection = com.evidhya.utilities.DBConnection.getConnection();
			boolean isValid=true;
			String message="";
			if(course_name==null || course_name.isEmpty()) {
				isValid=false;
				message="Please enter course name";
			} /*else if (teach_id==0) {
				isValid=false;
				message="Please entre teacher name";
			}*/ else if (crs_desc==null || crs_desc.isEmpty()) {
				isValid=false;
				message="Please enter course description";
			}
			else if (course_category==null || course_category.isEmpty()) {
				isValid=false;
				message="Please enter course description";
			}
			if(!isValid) {
				req.setAttribute("message", message);
				req.getRequestDispatcher("action/add_course").include(req, resp);
			} else {
				try {
				PreparedStatement stmt = connection.prepareStatement("update course set course_name=?, course_categories=?,course_desc=?, author=? where course_id=? ");
				
				stmt.setString(1, course_name);
				stmt.setString(2, course_category);
				stmt.setString(3, crs_desc);
				stmt.setString(4, teach_name);
				stmt.setString(5, id);
				int i = stmt.executeUpdate();
				System.out.println("Record Updated: "+i);
				if(i==1){
					message="Record updated";
					req.setAttribute(message, message);
					resp.sendRedirect(req.getContextPath() + "/jsp/update_course.jsp?id="+id);
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
