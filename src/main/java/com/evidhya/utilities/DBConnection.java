package com.evidhya.utilities;

//package com.smoee.utilities;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static  Connection connection;
	
	public static Connection getConnection() throws FileNotFoundException {
		
		if(connection==null) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				System.out.println("oracle Errorrrr");
				String db="jdbc:oracle:thin:@localhost:1521:xe";
				String user = "System"; 
				String password = "root";
				//System.out.println(PropertyReader.getPropValue("db.url"));
				 connection = DriverManager.getConnection(
						db,
						user,
						password
						
						);
				System.out.println("oracle Errorrrr1");
				
				
			}catch(InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Succesful");
		return connection;
	}
	
}
