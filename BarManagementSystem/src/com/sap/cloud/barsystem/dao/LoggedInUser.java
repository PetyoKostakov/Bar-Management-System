package com.sap.cloud.barsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.sql.DataSource;

public class LoggedInUser {
		
	    private DataSource dataSource;

	    /**
	     * Create new data access object with data source.
	     */
	    public LoggedInUser(DataSource newDataSource) throws SQLException {
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
	    
	    
	
    public int getAccess(String username) throws SQLException {
        Connection connection = dataSource.getConnection();
        int accessLevel=0;
        
       
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT ACCESS FROM USERS2 WHERE USERNAME="+"'"+username+"'");
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                

                accessLevel= rs.getInt(1);
            	
                   
            return accessLevel;                     
            }
            
            return 0;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

}
