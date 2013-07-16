package com.sap.cloud.barsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import javax.sql.DataSource;

import com.sap.cloud.barsystem.User;

public class UserLogin {
		
	    private DataSource dataSource;

	    /**
	     * Create new data access object with data source.
	     */
	    public UserLogin(DataSource newDataSource) throws SQLException {
	        setDataSource(newDataSource);
	    }

	    /**
	     * Get data source which is used for the database operations.
	     */
	    public DataSource getDataSource() {
	        return dataSource;
	    }

	    /**
	     * Set data source to be used for the database operations.
	     */
	    public void setDataSource(DataSource newDataSource) throws SQLException {
	        this.dataSource = newDataSource;
	       
	    }
		
		
	    /* If User name and password are valid returns user
	     * else if not valid returns null
	     */
	    
	    
	
    public User UserValidation(String username, String password) throws SQLException {
        Connection connection = dataSource.getConnection();
        User u = new User();
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT USERNAME, FIRSTNAME, LASTNAME, ACCESS FROM USERS2 WHERE PASSWORD="+password+ "AND USERNAME="+username);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                
                u.setUserName(rs.getString(1));
                u.setFirstName(rs.getString(2));
                u.setLastName(rs.getString(3));
                u.setAccess(rs.getInt(4));
                   
            return u;                     
            }
            
            return null;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

}
