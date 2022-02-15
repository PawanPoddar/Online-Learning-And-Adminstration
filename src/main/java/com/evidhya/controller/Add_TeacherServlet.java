package com.evidhya.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add_teacher")
public class Add_TeacherServlet extends HttpServlet{
	int teach_id=1;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String teach_name = req.getParameter("t_name");
		String teach_age = req.getParameter("t_age");
		String teach_spec = req.getParameter("t_spec");
		String teach_course = req.getParameter("t_course");
		String teach_password = req.getParameter("t_password");
		String teach_repassword = req.getParameter("t_repassword");
		
		
	try {
		Connection connection = com.evidhya.utilities.DBConnection.getConnection();
		boolean isValid=true;
		String message="";
		if(teach_name==null || teach_name.isEmpty()) {
			isValid=false;
			message="Please enter course name";
		} else if (teach_age==null || teach_age.isEmpty()) {
			isValid=false;
			message="Please entre teacher age";
		} else if (teach_spec==null || teach_spec.isEmpty()) {
			isValid=false;
			message="Please enter teacher speciality";
		} else if (teach_course==null || teach_course.isEmpty()) {
			isValid=false;
			message="Please enter teacher course";
		} else if (teach_password==null || teach_password.isEmpty()) {
			isValid=false;
			message="Please enter teacher password";
		} else if (teach_repassword==null || teach_repassword.isEmpty()) {
			isValid=false;
			message="Please enter teacher re-password";
		} else if (!teach_password.equals(teach_repassword)) {
			isValid=false;
			message="Password not match";
		}
		
		if(!isValid) {
			req.setAttribute("message", message);
			req.getRequestDispatcher("action/add_teacher").include(req, resp);
		} else {
			try {
			PreparedStatement stmt = connection.prepareStatement("insert into teacher(teacher_id,teacher_name,teacher_age,specialist,course_id,teacher_pass) values(?,?,?,?,?,?)");
			stmt.setInt(1, teach_id);
			stmt.setString(2, teach_name);
			stmt.setString(3, teach_age);
			stmt.setString(4, teach_spec);
			stmt.setString(5, teach_course);
			stmt.setString(6, teach_password);
			int i = stmt.executeUpdate();
			System.out.println("Record Updated: "+i);
			teach_id++;
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
