package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	static private Connection connection;

	//So that no one create its object from outside and 
		//we can get single instance of Connection for complete project
	
	private ConnectionProvider() {
		super();
	}

	public static Connection getConnection() {
		if (connection == null) {
			String url = "jdbc:mysql://localhost:3306/ride_sharing_application";
			try {
				connection = DriverManager.getConnection(url, "root", "212731");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connection;
	}

}
