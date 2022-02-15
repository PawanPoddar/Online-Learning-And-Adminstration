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
@WebServlet("/lecture")
public class upload_lecture extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int course_id=1;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String course_id = req.getParameter("course_id");
		String lecture_name = req.getParameter("lecture_name");
		String author = req.getParameter("author");
		String files = req.getParameter("files");
		
		try {
			Connection connection = com.evidhya.utilities.DBConnection.getConnection();
			boolean isValid=true;
			String message="";
			if(course_id==null || lecture_name.isEmpty()) {
				isValid=false;
				message="Please enter fill all the field";
			} /*else if (teach_id==0) {
				isValid=false;
				message="Please entre teacher name";
			}*/ else if (author==null || files.isEmpty()) {
				isValid=false;
				message="Please fill all the field";
			}
			
			if(!isValid) {
				req.setAttribute("message", message);
				req.getRequestDispatcher("action/lecture").include(req, resp);
			} else {
				try {
				PreparedStatement stmt = connection.prepareStatement("insert into videos(course_id,lecture_name,author,files) values(?,?,?,?)");
				stmt.setString(1, course_id);
				stmt.setString(2, lecture_name);
				stmt.setString(3, author);
				stmt.setString(4, files);
				int i = stmt.executeUpdate();
				System.out.println("Record Updated: "+i);
			
				req.getRequestDispatcher("/action/lecture").forward(req, resp);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
