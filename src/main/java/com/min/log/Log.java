package com.min.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.min.utils.Constants;
import com.min.utils.PropertiesLoader;

public class Log {
		private static Logger logger_r = Logger.getLogger("routing");
		private static Logger logger_t = Logger.getLogger("target");
		private static Logger logger_root = Logger.getLogger(Log.class);
		public static int ROUT = 1;
		public static int TARGET = 2;
		
		static
		{
			try {
				InputStream inputStream = 
						PropertiesLoader.class.getClassLoader().getResourceAsStream(Constants.LOG4J_PROP);
				Properties prop = new Properties();
				prop.load(inputStream);
				PropertyConfigurator.configure(prop);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static String timeFormat = "yyyy-MM-dd HH:mm:ss";

		public static void out(Logger logger, String mes) {
			logger.info(mes);
		}
		
		public static void out(Logger logger, String mes, Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		public static void out(Logger logger, Exception e){
			logger.error(e.getMessage(), e);
		}

		public static void main(String[] args) throws Exception {
	        try{
	        	String p = null;
	        	p.equals("ddd");
	        }catch(Exception e){
	        	Log.out(logger_r, e);
	        }
	        Log.out(logger_r, "This is a test");
			Thread.sleep(1000);
			Log.out(logger_t, "This is a test2");
		}

		public static void info(String value) {
			logger_root.info(value);
		}
		public static void info(String value, int type){
			if(type == Log.ROUT) {
				logger_r.info(value);
			}
			else {
				logger_t.info(value);
			}
		}
}
