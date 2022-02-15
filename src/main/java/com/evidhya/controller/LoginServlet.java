package com.evidhya.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.evidhya.utilities.DBConnection;



public class LoginServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
//		System.out.println(uri);
		int user_type = Integer.parseInt(req.getParameter("usr_type"));
		String usr_name = req.getParameter("username");
		String password = req.getParameter("password");
		try {
			Connection connection = com.evidhya.utilities.DBConnection.getConnection();
			boolean isValid=true;
			String message="";
			if(user_type==0) {
				isValid=false;
				message="Please select your type";
			} else if (usr_name==null || usr_name.isEmpty()) {
				isValid=false;
				message="Please entre username";
			} else if (password==null || password.isEmpty()) {
				isValid=false;
				message="Please enter password";
			}
			if(!isValid) {
				req.setAttribute("message", message);
				req.getRequestDispatcher("index.jsp").include(req, resp);
			} else if(user_type!=0){
				try {
//				Statement stmt=connection.createStatement();
//				String qu="select *From admin where admin_email="+usr_name+"OR admin_pass="+password+"LIMIT 1";
				String qstr="";
				if(user_type==1)
					qstr="select *from admin where admin_email=? OR admin_pass=?";
				if(user_type==2)
					qstr="select teacher_name,teacher_pass from teacher where teacher_name=? OR teacher_pass=?";
				if(user_type==3)
					qstr="select *from teacher where admin_email=? OR admin_pass=?";

				PreparedStatement stmt = connection.prepareStatement(qstr);
				stmt.setString(1, usr_name);
				stmt.setString(2, password);
				ResultSet rs=stmt.executeQuery();  
//				
				System.out.println(usr_name);
				System.out.println(password);
				
				String dbUpass=null;
				int idx=0;
				while(rs.next()) {
					idx++;
					dbUpass = rs.getString(2);
					System.out.println(dbUpass);
				} 				
				if(idx==0){
					req.setAttribute("message", "Username or Email didn't find");
					req.getRequestDispatcher("index.jsp").include(req, resp);
				}else{
					if(!dbUpass.equals(password)){
						req.setAttribute("message", "Incorrect password");
						req.getRequestDispatcher("index.jsp").include(req, resp);
						}else{ 
						req.getSession().setAttribute("login_user", true);
						if(user_type==1) {
					        getServletContext().setAttribute("name",usr_name);
							req.getRequestDispatcher("action/admin").forward(req, resp);
						}
						if(user_type==2) {
							getServletContext().setAttribute("name",usr_name);
							req.getRequestDispatcher("action/teacher_dashboard").forward(req, resp);
						}
						if(user_type==3)
							req.getRequestDispatcher("action/admin").forward(req, resp);
					
					}}
				 
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
}

