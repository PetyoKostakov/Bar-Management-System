

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WaiterScreenServlet
 */
public class WaiterScreenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public WaiterScreenServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		showWaiterForm(out);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	private void showWaiterForm(PrintWriter aout){
		aout.println(
						"<!DOCTYPE html>\n"+
						"<html xmlns='http://www.w3.org/1999/xhtml'>\n"+
						"<head>\n"+
						"<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>\n"+
						"<title></title>\n"+
						"<link href='StyleOrderWaiter.css' rel='stylesheet' />\n"+
						"</head>\n"+
						"<body>\n"+
						"<div id='header'>\n"+
						"<img src='Images/beer-icon.png' />\n"+
						"<h2>Bar Management</h2>\n"+
						"</div>\n"+
						"<section id='dataContainer' style='width:700px'>\n"+
						"<header id='dataHeader'>\n"+
						"<span id='pageTitle'>\n"+
						"Orders\n"+
						"</span>\n"+
						"<span id='buttons'>\n"+
						"<form method='POST' action='https://loginscreenp1940022967trial.hanatrial.ondemand.com/BMSTEST/AddOrdersWaiterServlet'> <!--+ aCaptionText + '-->\n"+
						"<input type='submit' id='addOrder' value='Add Order' />\n"+
						"</form>\n"+
						"</span>\n"+
						"</header>\n"+
						"<div class='line'></div>\n"+
						"<section class='order'>\n"+
						"<p class='orderTitle'>Order 1</p>\n"+
						"<div class='line'></div>\n"+
						"<table class='orderItem'>\n"+
						"<colgroup>\n"+
						"<col style='width: 220px' />\n"+
						"<col style='width: 220px' />\n"+
						"<col style='width: 220px' />\n"+
						"</colgroup>\n"+
						"<thead>\n"+
						"<tr>\n"+
						"<td>Item</td>\n"+
						"<td>Amount</td>\n"+
						"<td>Price</td>\n"+
						"</tr>\n"+
						"</thead>\n"+
						"<tbody>\n"+
						"<tr>\n"+
						"<td>Beer</td>\n"+
						"<td>10</td>\n"+
						"<td>1.5</td>\n"+
						"</tr>\n"+
						"<tr>\n"+
						"<td>Water</td>\n"+
						"<td>5</td>\n"+
						"<td>1</td>\n"+
						"</tr>\n"+
						"<tr>\n"+
						"<td>Beer</td>\n"+
						"<td>10</td>\n"+
						"<td>1.5</td>\n"+
						"</tr>\n"+
						"<tr>\n"+
						"<td>Water</td>\n"+
						"<td>5</td>\n"+
						"<td>1</td>\n"+
						"</tr>\n"+
						"</tbody>\n"+
						"</table>\n"+
						"</section>\n"+
						"<section class='order'>\n"+
						"<p class='orderTitle'>Order 1</p>\n"+
						"<div class='line'></div>\n"+
						"<table class='orderItem'>\n"+
						"<colgroup>\n"+
						"<col style='width: 220px' />\n"+
						"<col style='width: 220px' />\n"+
						"<col style='width: 220px' />\n"+
						"</colgroup>\n"+
						"<thead>\n"+
						"<tr>\n"+
						"<td>Item</td>\n"+
						"<td>Amount</td>\n"+
						"<td>Price</td>\n"+
						"</tr>\n"+
						"</thead>\n"+
						"<tbody>\n"+
						"<tr>\n"+
						"<td>Beer</td>\n"+
						"<td>10</td>\n"+
						"<td>1.5</td>\n"+
						"</tr>\n"+
						"<tr>\n"+
						"<td>Water</td>\n"+
						"<td>5</td>\n"+
						"<td>1</td>\n"+
						"</tr>\n"+
						"<tr>\n"+
						"<td>Beer</td>\n"+
						"<td>10</td>\n"+
						"<td>1.5</td>\n"+
						"</tr>\n"+
						"<tr>\n"+
						"<td>Water</td>\n"+
						"<td>5</td>\n"+
						"<td>1</td>\n"+
						"</tr>\n"+
						"</tbody>\n"+
						"</table>\n"+
						"</section>\n"+
						"<section class='order'>\n"+
						"<p class='orderTitle'>Order 1</p>\n"+
						"<div class='line'></div>\n"+
						"<table class='orderItem'>\n"+
						"<colgroup>\n"+
						"<col style='width: 220px' />\n"+
						"<col style='width: 220px' />\n"+
						"<col style='width: 220px' />\n"+
						"</colgroup>\n"+
						"<thead>\n"+
						"<tr>\n"+
						"<td>Item</td>\n"+
						"<td>Amount</td>\n"+
						"<td>Price</td>\n"+
						"</tr>\n"+
						"</thead>\n"+
						"<tbody>\n"+
						"<tr>\n"+
						"<td>Beer</td>\n"+
						"<td>10</td>\n"+
						"<td>1.5</td>\n"+
						"</tr>\n"+
						"<tr>\n"+
						"<td>Water</td>\n"+
						"<td>5</td>\n"+
						"<td>1</td>\n"+
						"</tr>\n"+
						"<tr>\n"+
						"<td>Beer</td>\n"+
						"<td>10</td>\n"+
						"<td>1.5</td>\n"+
						"</tr>\n"+
						"<tr>\n"+
						"<td>Water</td>\n"+
						"<td>5</td>\n"+
						"<td>1</td>\n"+
						"</tr>\n"+
						"</tbody>\n"+
						"</table>\n"+
						"</section>\n"+
						"</section>\n"+
						"</body>\n"+
						"</html>\n"
						
						
						);
	}
	
	
}
