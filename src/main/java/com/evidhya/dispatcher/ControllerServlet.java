package com.evidhya.dispatcher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,
			HttpServletResponse response)throws ServletException,IOException {
		doPost(request,response);	
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response){
		try{
			String uri=request.getRequestURI();
			String action=uri.substring(uri.lastIndexOf("/")+1);
			//System.out.println("Uri"+uri);
			//System.out.println("Action"+action);
			String handlerJsp="/jsp/"+action+".jsp";
			//System.out.println("HandlerJsp: "+handlerJsp);
			RequestDispatcher rd=request.getRequestDispatcher(handlerJsp);
			rd.include(request,response);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
