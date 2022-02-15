package com.evidhya.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.evidhya.utilities.DBConnection;
@WebServlet("/delete_teacher")
public class Delete_Teacher extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		try{
			Connection con = DBConnection.getConnection();
			
			PreparedStatement stmt = con.prepareStatement("delete from teacher where teacher_id=?");
			stmt.setString(1, id);
			stmt.executeUpdate();
			resp.sendRedirect(req.getContextPath() + "/jsp/teacher_list.jsp");
		}catch(Exception e){e.printStackTrace();}
	
	}
	
}
