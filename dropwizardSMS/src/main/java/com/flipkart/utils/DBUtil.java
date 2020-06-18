package com.flipkart.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;



public class DBUtil {
	
	private static Connection connection = null;
	
	private static Logger logger = Logger.getLogger(DBUtil.class);
	
	public static Connection getConnection() {
		
        if (connection != null)
            return connection;
        else {
            try {
                String driver = "com.mysql.cj.jdbc.Driver";
                String url = "jdbc:mysql://localhost/flipkartDB";
                String user = "root";
                String password = "sushma1998";
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                logger.error(e.getMessage());
            } catch (SQLException e) {
            	logger.error(e.getMessage());
            } 
            return connection;
        }

    }
	
	public static void closeConnection() {
		System.out.println("Closing Connection!!");
		try {
			if(connection != null) {
				connection.close();
			}
			else {
				logger.info("Connection already closed!");
			}
		}catch (SQLException e) {
			logger.error(e.getMessage());
        }
		
	}


}


