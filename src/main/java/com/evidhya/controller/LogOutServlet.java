package com.evidhya.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet{

	
	public void doGet(HttpServletRequest req , HttpServletResponse res){
		try{
			req.getSession().invalidate();
			req.getRequestDispatcher("/").include(req, res);
			res.sendRedirect(req.getContextPath() + "/index.jsp");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
