package com.sap.cloud.barsystem.ui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginScreenServlet
 */
public class LoginScreenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginScreenServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
        PrintWriter out = response.getWriter(); 
        	
            showLoginForm(out); 
           
        }
	
	
		
		
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			
				
			}

		
		
			private void showLoginForm(PrintWriter aout){
				aout.println(
						"<!DOCTYPE html>\n"+
						"<html xmlns='http://www.w3.org/1999/xhtml'>\n"+		
						"<head>\n"+
						"<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>\n"+
						"<title>Login-Bar Management System</title>\n"+
						"<link href='StyleLogin.css' rel='stylesheet' />\n"+
						"</head>\n"+
						"<body>\n"+
						"<div id='header'>\n"+
						"<img src='Images/beer-icon.png' />\n"+
						"<h2>Bar Management</h2>\n"+
						"</div>\n"+
						"<form method='POST' style='width:480px;height:240px' action='https://loginscreenp1940022967trial.hanatrial.ondemand.com/BMSTEST/WaiterScreenServlet'> <!--+ aCaptionText + '-->\n"+
						"<span id='info'>\n"+
						"<p id='login'>Login</p>\n"+
						"<div id='line'></div>\n"+
						"<span id='username'>\n"+
						"<p>Username</p>\n"+
						"<input id='nameField' type='text' name='username'/><br/>\n"+
						"</span>\n"+
						"<span id='password'>\n"+
						"<p>Password</p>\n"+
						"<input id='passwordField' type='password' name='password'/><br/>\n"+
						"</span>\n"+
						"</span>\n"+
						"<a>\n"+
						"<input id='button' type='submit' value='Login'/>\n"+
						"</a>\n"+
						"</form>\n"+
						"</body>\n"+
						"</html>\n"
						
			);	
	}
	
	

}
