package com.min.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesLoader {
	

	public static Properties load() {
		Properties prop = new Properties();
		
		try {
			InputStream inputStream = 
					PropertiesLoader.class.getClassLoader().getResourceAsStream(Constants.PROPS_FIEL);
			prop.load(inputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
	}

}
