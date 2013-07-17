package com.sap.cloud.barsystem.dao;



import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.sap.cloud.barsystem.Drink;

/**
 * Data access object encapsulating all JDBC operations for a user.
 */
public class DrinkDAO {
    private DataSource dataSource;

    /**
     * Create new data access object with data source.
     */
    public DrinkDAO(DataSource newDataSource) throws SQLException {
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
        checkTable();
    }

    /**
     * Add a drink to the table.
     */
    
    
    public void addDrink(Drink drink) throws SQLException {
        Connection connection = dataSource.getConnection();

        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("INSERT INTO DRINKS (ID, DRINKNAME, PRICE) VALUES (?, ?, ?)");
            pstmt.setInt(1, drink.getId());
            pstmt.setString(2, drink.getDrinkName());
            pstmt.setDouble(3,drink.getPrice());

            pstmt.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Get all drinks from the table.
     */
    public List<Drink> selectAllDrinks() throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT ID, DRINKNAME, PRICE FROM DRINKS");
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Drink> list = new ArrayList<Drink>();
            while (rs.next()) {
                Drink d = new Drink();
                d.setId(rs.getInt(1));
                d.setDrinkName(rs.getString(2));
                d.setPrice(rs.getDouble(3));
            
                list.add(d);
            }
            return list;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    
    
    
    
    public Double getSingleDrinkPrice(String drinkName) throws SQLException {
        Connection connection = dataSource.getConnection();
        double singlePrice=10;
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT PRICE FROM DRINKS WHERE DRINKNAME='Water'");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                rs.getDouble(1);
   
            }
            
            return singlePrice;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    /**
     * Check if the drink table already exists and create it if not.
     */
    private void checkTable() throws SQLException {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            if (!existsTable(connection)) {
                createTable(connection);
                fillTable(connection);
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Check if the user table already exists.
     */
    private boolean existsTable(Connection conn) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet rs = meta.getTables(null, null, "DRINKS", null);
        while (rs.next()) {
            String name = rs.getString("TABLE_NAME");
            if (name.equals("DRINKS")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Create the drink table.
     */
    private void createTable(Connection connection) throws SQLException {
        PreparedStatement pstmt = connection
                .prepareStatement("CREATE TABLE DRINKS "
                        + "(ID INT PRIMARY KEY, "
                        + "DRINKNAME VARCHAR (255),"
        				+ "PRICE DOUBLE)"
                        );

        pstmt.executeUpdate();
                
    }
    
    private void fillTable(Connection connection) throws SQLException{
    	PreparedStatement pstmt = connection
    			.prepareStatement("INSERT INTO DRINKS (ID, DRINKNAME, PRICE) VALUES (?,?,?), (?,?,?), (?,?,?), (?,?,?), (?,?,?), (?,?,?), (?,?,?), (?,?,?), (?,?,?), (?,?,?)");
        pstmt.setInt(1, 1);
        pstmt.setString(2, "Water");
        pstmt.setDouble(3, 1);
     
        
        pstmt.setInt(4, 2);
        pstmt.setString(5, "Coffee");
        pstmt.setDouble(6,  1.8);
        
        pstmt.setInt(7, 3);
        pstmt.setString(8, "Whisky");
        pstmt.setDouble(9, 10);
        
        
        pstmt.setInt(10, 4);
        pstmt.setString(11, "Juice");
        pstmt.setDouble(12, 2);
        
        
        pstmt.setInt(13, 5);
        pstmt.setString(14, "Beer");
        pstmt.setDouble(15, 2.5);
        
        
        pstmt.setInt(16, 6);
        pstmt.setString(17, "Wine");
        pstmt.setDouble(18, 25);
        
        
        pstmt.setInt(19, 7);
        pstmt.setString(20, "Vodka");
        pstmt.setDouble(21, 5);
        
        
        pstmt.setInt(22, 8);
        pstmt.setString(23, "Rakia");
        pstmt.setDouble(24, 8);
        
        
        pstmt.setInt(25, 9);
        pstmt.setString(26, "Jin");
        pstmt.setDouble(27, 1);
        
        
        pstmt.setInt(28, 10);
        pstmt.setString(29, "Soda");
        pstmt.setDouble(30, 1);
        
        
        
       

        

        pstmt.executeUpdate();
    }
    
    
}