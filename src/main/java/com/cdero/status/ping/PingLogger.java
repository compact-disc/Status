package com.cdero.status.ping;

import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;
import java.util.logging.FileHandler;

/**
 * @author 	Christopher DeRoche
 * @version	0.0.1
 * @since	0.0.1
 * 
 */

public class PingLogger {
	
	private static final Logger logger = Logger.getLogger(PingLogger.class.getName());
	
	private FileHandler fileHandler;
	
	protected PingLogger() {
		
		fileHandler = null;
		
		try {
			
			fileHandler = new FileHandler("./Status/logs/PingLogs.txt", true);
			
			logger.addHandler(fileHandler);
			
			SimpleFormatter fileFormatter = new SimpleFormatter();
			
			fileHandler.setFormatter(fileFormatter);
			
		}catch (IOException ex) {
			
			ex.printStackTrace();
			
		}
		
	}
	
	protected void warning(String warning) {
		
		logger.warning(warning + "\n");
		
	}
	
	protected void info(String info) {
		
		logger.info(info + "\n");
		
	}
	
	protected void severe(String severe) {
		
		logger.severe(severe + "\n");
		
	}
	
	protected void close() {
		
		fileHandler.close();
		
	}

}
