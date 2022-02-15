package com.evidhya.utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
	
	public static String getPropValues(String key) {
		Properties prop = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = PropertyReader.class.getClassLoader().getResourceAsStream("application.properties");
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file not found in the classpath");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return prop.getProperty(key);
	}
}
