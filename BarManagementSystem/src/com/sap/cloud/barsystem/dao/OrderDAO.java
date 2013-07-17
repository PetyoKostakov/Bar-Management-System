package com.sap.cloud.barsystem.dao;



import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.sap.cloud.barsystem.Order;

/**
 * Data access object encapsulating all JDBC operations for a order.
 */
public class OrderDAO {
    private DataSource dataSource;

    /**
     * Create new data access object with data source.
     */
    public OrderDAO(DataSource newDataSource) throws SQLException {
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
     * Add a order to the table.
     */
    public void addOrder(Order order) throws SQLException {
        Connection connection = dataSource.getConnection();

        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("INSERT INTO ORDERS (ID, ITEMS,  ENDPRICE, ORDEREDBY) VALUES (?, ?, ?, ?)");
            pstmt.setInt(1, order.getId());
            pstmt.setString(2, order.getItems());
            
            pstmt.setDouble(3, order.getEndPrice());
            pstmt.setString(4,order.getOrderedBy());                         /********  Ordered by logged in user *********/
            pstmt.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Get all orders from the table.
     */
    int i=0;
    public List<Order> selectAllOrders() throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT ID, ITEMS , ENDPRICE, ORDEREDBY FROM ORDERS");
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Order> list = new ArrayList<Order>();
            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt(1));
                o.setItems(rs.getString(2));
               
                o.setEndPrice(rs.getDouble(3));
                o.setOrderedBy(rs.getString(4));
                list.add(o);
                i++;
            }
            return list;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    
    
    public int getNumberOfRows(){
    	
    	return this.i;
    }
    
    
    public void deleteOrder(int id) throws SQLException{
    	Connection connection = dataSource.getConnection();
        PreparedStatement pstmt = connection
                .prepareStatement("DELETE FROM ORDERS WHERE ID="+id);
        int rs = pstmt.executeUpdate();
    	
    }
    
    
    
    
    

    /**
     * Check if the order table already exists and create it if not.
     */
    private void checkTable() throws SQLException {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            if (!existsTable(connection)) {
                createTable(connection);
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Check if the orders table already exists.
     */
    private boolean existsTable(Connection conn) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet rs = meta.getTables(null, null, "ORDERS", null);
        while (rs.next()) {
            String name = rs.getString("TABLE_NAME");
            if (name.equals("ORDERS")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Create the person table.
     */
    private void createTable(Connection connection) throws SQLException {
        PreparedStatement pstmt = connection
                .prepareStatement("CREATE TABLE ORDERS "
                        + "(ID INT , "
                        + "ITEMS VARCHAR (255),"
                      
                        + "ENDPRICE DOUBLE,"
                        + "ORDEREDBY VARCHAR (255),"
                        + "PRIMARY KEY (ID))");
        pstmt.executeUpdate();
    }
}