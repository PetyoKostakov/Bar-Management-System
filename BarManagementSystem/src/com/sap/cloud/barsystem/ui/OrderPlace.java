package com.sap.cloud.barsystem.ui;

import java.io.IOException;
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

import com.sap.cloud.barsystem.Order;
import com.sap.cloud.barsystem.dao.DrinkDAO;
import com.sap.cloud.barsystem.dao.OrderDAO;
import com.sap.security.core.server.csi.IXSSEncoder;
import com.sap.security.core.server.csi.XSSEncoder;

/**
 * Servlet implementing a simple JDBC based persistence sample application for
 * SAP HANA Cloud.
 */
public class OrderPlace extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OrderPlace.class);

	private OrderDAO orderDAO;
	int id =1;
	private DrinkDAO drinkDAO;
	String itemsString="";
	Double tottalCost=0.0;

	/** {@inheritDoc} */
	@Override
	public void init() throws ServletException {
		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/DefaultDB");
			orderDAO = new OrderDAO(ds);
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
		response.getWriter().println("<p>Order Placement!</p>");
		try {
			appendOrderTable(response);
			appendAddForm(response);
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
			
			if( (request.getParameter("placeOrder"))!=null) {
				doAdd(request);

				}
			
			if ((request.getParameter("addItem"))!=null){
				addItemstoOrder(request);
				
			}
			
			if(tottalCost!=0.0){
			response.getWriter()
			.println(
					"<p>"+itemsString+" "+ tottalCost+ "</p>");
			}			
			
			doGet(request, response);
			
			
			
			
			
		} catch (Exception e) {
			response.getWriter().println(
					"Persistence operation failed with reason: "
							+ e.getMessage());
			LOGGER.error("Persistence operation failed", e);
		}
	}

	private void appendOrderTable(HttpServletResponse response)
			throws SQLException, IOException {
		// Append table that lists all orders
		List<Order> resultList = orderDAO.selectAllOrders();
		response.getWriter().println(
				"<p><table border=\"1\"><tr><th colspan=\"5\">"
						+ (resultList.isEmpty() ? "" : resultList.size() + " ")
						+ "Entries in the Database</th></tr>");
		if (resultList.isEmpty()) {
			response.getWriter().println(
					"<tr><td colspan=\"4\">Database is empty</td></tr>");
		} else {
			response.getWriter()
					.println(
							"<tr><th>ID</th><th>Items</th><th>Amount</th><th>Cost</th><th>Ordered by</th></tr>");
		}
		IXSSEncoder xssEncoder = XSSEncoder.getInstance();
		for (Order o : resultList) {
			response.getWriter().println(
					"<tr><td>" + xssEncoder.encodeHTML(Integer.toString(o.getId())) + "</td>"
							+"<td>"	+	xssEncoder.encodeHTML(o.getItems()) + "</td>"
							+ "<td>" + xssEncoder.encodeHTML(Integer.toString(o.getAmount())) + "</td>"
							+ "<td>" + xssEncoder.encodeHTML(Double.toString(o.getEndPrice())) + "</td>"
							
							+ "<td>"+ xssEncoder.encodeHTML(o.getOrderedBy()) + "</td>"

							+ "</tr>");
		}
		response.getWriter().println("</table></p>");
	}

	private void appendAddForm(HttpServletResponse response) throws IOException {
		// Append form through which new orders can be added
		response.getWriter()
				.println(
						"<p><form action=\"\" method=\"post\">"
				//				+ "ID:<input type=\"text\" name=\"Id\">"
								+ "&nbsp;Items:<input type=\"text\" name=\"Item\">"
								+ "&nbsp;Amount:<input type=\"text\" name=\"Amount\">"
								+ "&nbsp;Cost:<input type=\"text\" name=\"EndPrice\">"
	//							+ "&nbsp;Ordered by:<input type=\"text\" name=\"OrderedBy\">"
								+ "&nbsp;<input type=\"submit\" name=\"addItem\" value=\"Add Item\">"
								
								+ "</form></p>");
		
		
		response.getWriter()
		.println(
				"<p><form action=\"\" method=\"post\">"
						
						+ "&nbsp;<input type=\"submit\" name=\"placeOrder\" value=\"Place Order\">"
						+ "&nbsp;Ordered by:<input type=\"text\" name=\"OrderedBy\">"
						+ "</form></p>");
		
		
		
		
	}

	
	
	
	
	private void addItemstoOrder(HttpServletRequest request) throws ServletException,
	IOException, SQLException {
		
		String item = request.getParameter("Item");
		String amount = request.getParameter("Amount");
		String endPrice = request.getParameter("EndPrice");
		itemsString = itemsString + amount+ "x" +item + " ";
		tottalCost=tottalCost+ Double.parseDouble(amount) * Double.parseDouble(endPrice);
		
	}
	
	
	
	
	
	
	
	
	private void doAdd(HttpServletRequest request) throws ServletException,
			IOException, SQLException {
		// Extract name of order to be added from request
//		double endPrice=0;
//		String items="";
	//	String id = request.getParameter("Id");
	//	String item = request.getParameter("Item");
	//	String amount = request.getParameter("Amount");
	//	String endPrice = request.getParameter("EndPrice");
		String orderedBy = request.getParameter("OrderedBy");
		
	//	items= items +"3x " + item + " " ;
	//	endPrice = endPrice + (drinkDAO.singleDrinkPrice(item)*Double.parseDouble(amount));

		// Add order if name is not null/empty
		if (id != 0 &&  orderedBy!="" && itemsString!="" && tottalCost!=0.0) {
			Order order = new Order();
	//		order.setId(Integer.parseInt(id.trim()));
			order.setId(id++);
			order.setItems(itemsString);
			order.setAmount(1);
			order.setEndPrice(tottalCost);
			order.setOrderedBy(orderedBy);
			orderDAO.addOrder(order);
			itemsString="";
			tottalCost=0.0;
		}
	}
}
