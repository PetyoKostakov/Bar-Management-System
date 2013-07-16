package com.sap.cloud.barsystem.ui;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddOrdersWaiterServlet
 */
public class AddOrdersWaiterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOrdersWaiterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		showAddOrdersWaiterForm(out);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


	private void showAddOrdersWaiterForm(PrintWriter aout){
		aout.println(
				
						"<!DOCTYPE html>\n"+
						"<html xmlns='http://www.w3.org/1999/xhtml'>\n"+
						"<head>\n"+
						"<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>\n"+
						"<title>Weiter-Add Order</title>\n"+
						"<link href='StyleAddOrderWaiter.css' rel='stylesheet' />\n"+
						"</head>\n"+
						"<body>\n"+
						"<div id='header'>\n"+
						"<img src='Images/beer-icon.png' />\n"+
						"<h2>Bar Management</h2>\n"+
						"</div>\n"+
						"<section id='dataContainer' style='width:700px'>\n"+
						"<header id='dataHeader'>\n"+
						"<span id='pageTitle'>\n"+
						"Adding order\n"+
						"</span>\n"+
						"</header>\n"+
						"<div class='line'></div>\n"+
						"<section class='order'>\n"+
						"<form>\n"+
						"<header >\n"+
						"<span id='orderTitle' >Order</span>\n"+
						"<input id='addButton' type='button' type='submit' value='Add order' />\n"+
						"</header>\n"+
						"<div class='line'></div>\n"+
						"<div class='orderItem'>\n"+
						"<label>Name of order</label>\n"+
						"<input type='text' id='orderName' class='input'/>\n"+
						"<br />\n"+
						"<label>Item</label>\n"+
						"<input type='text' id='orderItem' class='input' />\n"+
						"<label>Amount</label>\n"+
						"<input type='text' id='Text1' class='input' />\n"+
						"<label>Price</label>\n"+
						"<input type='text' id='orderPrice' class='input' />\n"+
						"</div>\n"+
						"</form>\n"+
						"</section>\n"+
						"</section>\n"+
						"</body>\n"+
						"</html>\n"
						
												
			);
	}


}
