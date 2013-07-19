package com.sap.cloud.barsystem.dao;



import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.sap.cloud.barsystem.User;

/**
 * Data access object encapsulating all JDBC operations for a user.
 */
public class UserDAO {
    private DataSource dataSource;

    /**
     * Create new data access object with data source.
     */
    public UserDAO(DataSource newDataSource) throws SQLException {
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
     * Add a user to the table.
     */
    public void addUser(User user) throws SQLException {
        Connection connection = dataSource.getConnection();

        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("INSERT INTO USERS2 (USERNAME, FIRSTNAME, LASTNAME, ACCESS) VALUES (?, ?, ?, ?)");
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getFirstName());
            pstmt.setString(3, user.getLastName());
    
            pstmt.setInt(4, user.getAccess());
            pstmt.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Get all users from the table.
     */
    public List<User> selectAllUsers() throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT USERNAME, FIRSTNAME, LASTNAME, ACCESS FROM USERS2");
            ResultSet rs = pstmt.executeQuery();
            ArrayList<User> list = new ArrayList<User>();
            while (rs.next()) {
                User u = new User();
                u.setUserName(rs.getString(1));
                u.setFirstName(rs.getString(2));
                u.setLastName(rs.getString(3));
    
                u.setAccess(rs.getInt(4));
                list.add(u);
            }
            return list;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Check if the person table already exists and create it if not.
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
     * Check if the user table already exists.
     */
    private boolean existsTable(Connection conn) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet rs = meta.getTables(null, null, "USERS2", null);
        while (rs.next()) {
            String name = rs.getString("TABLE_NAME");
            if (name.equals("USERS2")) {
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
                .prepareStatement("CREATE TABLE USERS2 "
                        + "(USERNAME VARCHAR(255), "
                        + "FIRSTNAME VARCHAR (255),"
                        + "LASTNAME VARCHAR (255),"
                
                        + "ACCESS INT,"
                        + "PRIMARY KEY (USERNAME))");
        pstmt.executeUpdate();
    }
}