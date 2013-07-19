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
                    .prepareStatement("INSERT INTO DRINKS10 (ID, DRINKNAME, PRICE) VALUES (?, ?, ?)");
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
                    .prepareStatement("SELECT ID, DRINKNAME, PRICE FROM DRINKS10");
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
        double singlePrice=0;
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT PRICE FROM DRINKS10 WHERE DRINKNAME=?");
            pstmt.setString(1, drinkName);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                singlePrice=rs.getDouble(1);
   
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
        ResultSet rs = meta.getTables(null, null, "DRINKS10", null);
        while (rs.next()) {
            String name = rs.getString("TABLE_NAME");
            if (name.equals("DRINKS10")) {
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
                .prepareStatement("CREATE TABLE DRINKS10 "
                        + "(ID INT PRIMARY KEY, "
                        + "DRINKNAME VARCHAR (255),"
        				+ "PRICE DOUBLE)"
                        );

        pstmt.executeUpdate();
                
    }
    
    private void fillTable(Connection connection) throws SQLException{
    	insertDrink(connection, 1, "Water", 1);
    	insertDrink(connection, 2, "Coffee", 1.8);
        insertDrink(connection, 3, "Whisky", 10);
        insertDrink(connection, 4, "Juice", 2);
        insertDrink(connection, 5, "Beer", 2.5);        
        insertDrink(connection, 6, "Vodka", 5);
        insertDrink(connection, 7, "Rakia", 8);
        insertDrink(connection, 8, "Jin", 1);
        insertDrink(connection, 9, "Soda", 1);
        insertDrink(connection, 10, "Wine", 25);
        
        
    }
    
    private void insertDrink(Connection connection, int id, String drinkName, double price) throws SQLException {
    	PreparedStatement pstmt = connection
    			.prepareStatement("INSERT INTO DRINKS10 (ID, DRINKNAME, PRICE) VALUES (?,?,?)");
        pstmt.setInt(1, id);
        pstmt.setString(2, drinkName);
        pstmt.setDouble(3, price);
        pstmt.executeUpdate();
    }
    
    
}