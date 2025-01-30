/**
 * 
 */
package com.EAD.LibrarySystem;
import java.sql.*;

/**
 * 
 */
public class DatabaseConnection {

	/**
	 * 
	 */
		public static Connection getConnection( ) {
			Connection connection = null;
			try {
				String url = "jdbc:mysql://localhost:3306/LibraryManagement";
				String username = "root";
				String password = "1234";
				connection = DriverManager.getConnection(url, username, password);
			} catch (Exception e) {
				System.out.println("Database Error: " + e.getMessage());
			} 
			return connection;	
		}
}