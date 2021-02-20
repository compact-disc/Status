package com.cdero.status.ping;

import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;
import java.util.logging.FileHandler;

/**
 * @author 	Christopher DeRoche
 * @version	0.3
 * @since	0.1
 * 
 */

public class PingLogger {
	
	private static final Logger logger = Logger.getLogger(PingLogger.class.getName());
	
	private static FileHandler fileHandler;
	
	public static void initializeLogger() {
		
		fileHandler = null;
		
		try {
			
			fileHandler = new FileHandler("./Status/logs/StatusLogs.txt", true);
			
			logger.addHandler(fileHandler);
			
			SimpleFormatter fileFormatter = new SimpleFormatter();
			
			fileHandler.setFormatter(fileFormatter);
			
		}catch (IOException ex) {
			
			ex.printStackTrace();
			
		}
		
	}
	
	public static void warning(String warning) {
		
		logger.warning(warning);
		
	}
	
	public static void info(String info) {
		
		logger.info(info);
		
	}
	
	public static void severe(String severe) {
		
		logger.severe(severe);
		
	}
	
	public static void close() {
		
		fileHandler.close();
		
	}

}
