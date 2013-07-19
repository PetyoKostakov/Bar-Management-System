package com.sap.cloud.barsystem.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.cloud.barsystem.Drink;
import com.sap.cloud.barsystem.dao.DrinkDAO;
import com.sap.security.core.server.csi.IXSSEncoder;
import com.sap.security.core.server.csi.XSSEncoder;

/**
 * Servlet implementation class WaiterScreenServlet
 */
public class WaiterScreenServlet extends HttpServlet {

       
    
    public WaiterScreenServlet() {
        super();
        
    }

	


	


	
	private void showWaiterForm(PrintWriter aout){
		aout.println(
				"<!DOCTYPE html>\n"+
						"<html xmlns='http://www.w3.org/1999/xhtml'>\n"+
						"<head>\n"+
						    "<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>\n"+
						    "<title>Drinks</title>\n"+
						   "<link href='StyleMenager.css' rel='stylesheet' />\n"+
						"</head>\n"+
						"<body>\n"+
						    "<div id='header'>\n"+
						       "<img src='Images/beer-icon.png' />\n"+
						       "<h2>Bar Management</h2>\n"+
						   
						       
						    "</div>\n"+
						    "<center><br><br><h2>Drinks List!</center></h2>"+
						    "<section id='dataContainer' style='width:400px'>\n"
						        
				);
	}
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DrinksList.class);

	private DrinkDAO drinkDAO;

	/** {@inheritDoc} */
	@Override
	public void init() throws ServletException {
		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/DefaultDB");
			drinkDAO = new DrinkDAO(ds);
		} catch (SQLException e) {
			throw new ServletException(e);
		} catch (NamingException e) {
			throw new ServletException(e);
		}
	}

	/** {@inheritDoc} */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		showWaiterForm(out);
//		response.getWriter().println("<p>Drinks list!</p>");
		try {
			appendDrinkTable(response);
//			appendAddForm(response);
		} catch (Exception e) {
			response.getWriter().println(
					"Persistence operation failed with reason: "
							+ e.getMessage());
			LOGGER.error("Persistence operation failed", e);
		}
	}

	/** {@inheritDoc} */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
//			doAdd(request);
			doGet(request, response);
		} catch (Exception e) {
			response.getWriter().println(
					"Persistence operation failed with reason: "
							+ e.getMessage());
			LOGGER.error("Persistence operation failed", e);
		}
	}

	private void appendDrinkTable(HttpServletResponse response)
			throws SQLException, IOException {
		// Append table that lists all users
		List<Drink> resultList = drinkDAO.selectAllDrinks();
		response.getWriter().println(
				"<p><table width='401' border=\"1\"><tr><th colspan=\"3\">"
						+ (resultList.isEmpty() ? "" : resultList.size() + " ")
						+ "Drinks in the menu</th></tr>");
		if (resultList.isEmpty()) {
			response.getWriter().println(
					"<tr><td colspan=\"4\">Database is empty</td></tr>");
		} else {
			response.getWriter()
					.println(
							"<tr><th>ID</th><th>Drink</th><th>Price</th></tr>");
		}
		IXSSEncoder xssEncoder = XSSEncoder.getInstance();
		for (Drink d : resultList) {
			response.getWriter().println(
					"<tr><td>" + xssEncoder.encodeHTML(  Integer.toString(d.getId())) + "</td>"
							+"<td>"	+	xssEncoder.encodeHTML(d.getDrinkName()) + "</td>"
							+"<td>"	+	xssEncoder.encodeHTML(Double.toString(d.getPrice())) + "</td>"
						
							
						

							+ "</tr>");
		}
		response.getWriter().println("</table></p>" +
			    "</section>\n"+
			    "<center><br><br><br><a href=\"LogoutServlet\">Logout</a>&nbsp;&nbsp;&nbsp;<a href=\"orders\">Order</a>&nbsp;&nbsp;&nbsp;<a href=\"manage\">Manage</a></center>"+
			"</body>\n"+
			"</html>\n");
		
	 //   "<center><br><a href=\"orders\">Order</a></center>" +
	}
	
	
}
