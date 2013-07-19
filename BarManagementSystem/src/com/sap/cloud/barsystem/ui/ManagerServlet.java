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

import com.sap.cloud.barsystem.User;
import com.sap.cloud.barsystem.dao.UserDAO;
import com.sap.security.core.server.csi.IXSSEncoder;
import com.sap.security.core.server.csi.XSSEncoder;

/**
 * Servlet implementation class ManagerServlet
 */
public class ManagerServlet extends HttpServlet {

       
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


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */


private void showManagerForm(PrintWriter aout){
	aout.println(
			"<!DOCTYPE html>\n"+
					"<html xmlns='http://www.w3.org/1999/xhtml'>\n"+
					"<head>\n"+
					    "<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>\n"+
					    "<title>Register</title>\n"+
					   "<link href='StyleMenager.css' rel='stylesheet' />\n"+
					"</head>\n"+
					"<body>\n"+
					    "<div id='header'>\n"+
					       "<img src='Images/beer-icon.png' />\n"+
					       "<h2>Bar Management</h2>\n"+
					    "</div>\n"+
					    "<center><br><br><h2>User management!</center></h2>"+
					    "<section id='dataContainer' style='width:700px'>\n"
					        
			);
}


private static final long serialVersionUID = 1L;
private static final Logger LOGGER = LoggerFactory
		.getLogger(UserRegistration.class);

private UserDAO userDAO;

/** {@inheritDoc} */
@Override
public void init() throws ServletException {
	try {
		InitialContext ctx = new InitialContext();
		DataSource ds = (DataSource) ctx
				.lookup("java:comp/env/jdbc/DefaultDB");
		userDAO = new UserDAO(ds);
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
	showManagerForm(out);
//	response.getWriter().println("<p>User registration!</p>");
	try {
		appendUserTable(response);
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
		doAdd(request);
		doGet(request, response);
	} catch (Exception e) {
		response.getWriter().println(
				"Persistence operation failed with reason: "
						+ e.getMessage());
		LOGGER.error("Persistence operation failed", e);
	}
}

private void appendUserTable(HttpServletResponse response)
		throws SQLException, IOException {
	// Append table that lists all users
	List<User> resultList = userDAO.selectAllUsers();
	response.getWriter().println(
			"<p><table border=\"1\" width='701'><tr><th colspan=\"4\">"
					+ (resultList.isEmpty() ? "" : resultList.size() + " ")
					+ "Registered Users</th></tr>");
	if (resultList.isEmpty()) {
		response.getWriter().println(
				"<tr><td colspan=\"4\">There aren't any registered users</td></tr>");
	} else {
		response.getWriter()
				.println(
						"<tr><th>First name</th><th>Last name</th><th>User Name</th><th>Access</th></tr>");
	}
	IXSSEncoder xssEncoder = XSSEncoder.getInstance();
	for (User u : resultList) {
		response.getWriter().println(
				"<tr><td>" + xssEncoder.encodeHTML(u.getFirstName()) + "</td>"
						+"<td>"	+	xssEncoder.encodeHTML(u.getLastName()) + "</td>"
						+ "<td>" + xssEncoder.encodeHTML(u.getUserName()) + "</td>"
					
						
						+ "<td>"+ xssEncoder.encodeHTML(Integer.toString(u.getAccess())) + "</td>"

						+ "</tr>");
	}
	response.getWriter().println("</table></p>" +
					    "</section>\n"+
					"</body>\n"+
					"</html>\n");
}

private void appendAddForm(HttpServletResponse response) throws IOException {
	// Append form through which new users can be added
	response.getWriter()
			.println(
					"<center><p><form action=\"\" method=\"post\">"
							+ "First name:<input type=\"text\" name=\"FirstName\">"
							+ "&nbsp;Last name:<input type=\"text\" name=\"LastName\">"
							+ "&nbsp;User Name:<input type=\"text\" name=\"UserName\">"
						
							+ "&nbsp;Acess:<input type=\"text\" name=\"Access\">"
							+ "&nbsp;<input type=\"submit\" value=\"Add User\">"
							+ "</form></p>" +
							"<center><br><br><br><a href=\"LogoutServlet\">Logout</a>&nbsp;&nbsp;&nbsp;<a href=\"orders\">Order</a>&nbsp;&nbsp;&nbsp;<a href=\"drinkslist\">Drinks</a></center>");
}

private void doAdd(HttpServletRequest request) throws ServletException,
		IOException, SQLException {
	// Extract name of user to be added from request
	String firstName = request.getParameter("FirstName");
	String lastName = request.getParameter("LastName");
	String userName = request.getParameter("UserName");

	String access = request.getParameter("Access");
	

	// Add user if name is not null/empty
	if (firstName != null && lastName != null
			&& !firstName.trim().isEmpty() && !lastName.trim().isEmpty()) {
		User user = new User();
		user.setFirstName(firstName.trim());
		user.setLastName(lastName.trim());
		user.setUserName(userName.trim());
	
		user.setAccess(Integer.parseInt(access.trim()));
		userDAO.addUser(user);
	}
}



}
