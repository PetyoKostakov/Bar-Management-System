package com.sap.cloud.barsystem.ui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ManagerServlet
 */
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getRemoteUser();
		
		PrintWriter out = response.getWriter();
		showManagerForm(out);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

private void showManagerForm(PrintWriter aout){
	aout.println(
			"<!DOCTYPE html>\n"+
					"<html xmlns='http://www.w3.org/1999/xhtml'>\n"+
					"<head>\n"+
					    "<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>\n"+
					    "<title>Menager</title>\n"+
					   "<link href='StyleMenager.css' rel='stylesheet' />\n"+
					"</head>\n"+
					"<body>\n"+
					    "<div id='header'>\n"+
					       "<img src='Images/beer-icon.png' />\n"+
					       "<h2>Bar Management</h2>\n"+
					    "</div>\n"+
					    "<section id='dataContainer' style='width:700px'>\n"+
					        "<header id='dataHeader'>\n"+
					            "<span id='pageTitle'>\n"+
					               "Manager Panel\n"+
					            "</span>\n"+
					        "</header>\n"+
					        "<div class='line'></div>\n"+
					        "<div class='controlPanel'>\n"+
					           "<p>Adding employees</p>\n"+
					            "<div class='line'></div>\n"+
					        "</div>\n"+
					        "<div class='controlPanel'>\n"+
					            "<p>Adding items</p>\n"+
					            "<div class='line'></div>\n"+
					        "</div>\n"+
					        "<div class='controlPanel'>\n"+
					            "<p>Adding items</p>\n"+
					            "<div class='line'></div>\n"+
					        "</div>\n"+
					         "<div class='controlPanel'>\n"+
					            "<p>Analysis</p>\n"+
					             "<div class='line'></div>\n"+
					        "</div>\n"+
					    "</section>\n"+
					"</body>\n"+
					"</html>\n"
			);
}

}
