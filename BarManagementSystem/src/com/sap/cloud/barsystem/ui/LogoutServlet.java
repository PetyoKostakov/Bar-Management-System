package com.sap.cloud.barsystem.ui;

import java.io.IOException;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sap.security.auth.login.LoginContextFactory;


public class LogoutServlet extends HttpServlet {

  //Call logout if the user is logged in
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
	LoginContext loginContext = null;
  if (request.getRemoteUser() != null) { 

    try { 
      loginContext = LoginContextFactory.createLoginContext(); 
      loginContext.logout();
      response.getWriter().println("You have successfully logged out."); 
     
    } catch (LoginException e) { 
      // Servlet container handles the login exception
      // It throws it to the application for its information
      response.getWriter().println("Logout failed. Reason: " + e.getMessage()); 
    }
   } else {
    response.getWriter().println("You have successfully logged out."); 
   }
	}
	
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
            		throws ServletException, IOException {
		doPost(request, response);
	}
	
	
	
}