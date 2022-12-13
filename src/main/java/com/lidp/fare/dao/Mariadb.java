package com.lidp.fare.dao;

import java.sql.*;

public class Mariadb {
    private String JDBC_DRIVER;
    private String DB_URL;
    private String USER;
    private String PASS;

    public Mariadb(){
        JDBC_DRIVER = "org.mariadb.jdbc.Driver";
        DB_URL = "jdbc:mariadb://localhost:3307/lidp";
        USER = "root";
        PASS = "Caliber300400*";
    }

    public Connection getMariaDBConnection() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public void testQuery(){
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            conn.close();

            //STEP 4: Execute a query
            //System.out.println("Creating table in given database...");
            //stmt = conn.createStatement();

            //String sql = "CREATE TABLE REGISTRATION";

            //stmt.executeUpdate(sql);
            //System.out.println("Created table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }
}