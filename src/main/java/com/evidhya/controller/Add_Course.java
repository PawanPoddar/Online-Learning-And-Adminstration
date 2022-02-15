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
@WebServlet("/add_course")
public class Add_Course extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int course_id=1;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String course_name = req.getParameter("crs_name");
		String course_category = req.getParameter("crs_catgry");
		String teach_id = req.getParameter("t_name");
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
				PreparedStatement stmt = connection.prepareStatement("insert into course(course_id,course_name,teach_id,course_categories,course_desc) values(?,?,?,?,?)");
				stmt.setInt(1, course_id);
				stmt.setString(2, course_name);
				stmt.setString(3, teach_id);
				stmt.setString(4, course_category);
				stmt.setString(5, crs_desc);
				int i = stmt.executeUpdate();
				System.out.println("Record Updated: "+i);
				course_id++;
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
