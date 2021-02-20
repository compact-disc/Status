package com.cdero.status;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cdero.status.ping.PingLogger;

/**
 * @author 	Christopher DeRoche
 * @version	0.3
 * @since	0.1
 * 
 */

@SpringBootApplication
@EnableScheduling
public class StatusApplication {

	public static void main(String[] args) {
		
		// Initialize the logger for the application
		PingLogger.initializeLogger();
		
		/*
		 * Start Directory Checking
		 */
		
		//The root status program directory, placed in same directory as jar
		File statusDirectory = new File("./Status");
		
		//Directory that will store the hosts file(s)
		File hostsDirectory = new File("./Status/hosts");
		
		//Directory that will store all of the logs
		File logsDirectory = new File("./Status/logs");
		
		//If there is not a status root directory, create one
		if(!statusDirectory.exists()) {
			
			statusDirectory.mkdir();
			
		}
		
		//If there is not a hosts directory, create one
		if(!hostsDirectory.mkdir()) {
			
			hostsDirectory.mkdir();
			
		}
		
		//If there is not a logs directory, create one
		if(!logsDirectory.exists()) {
			
			logsDirectory.mkdir();
			
		}
		
		/*
		 * End Directory Checking
		 */
		
		
		/*
		 * Start Default Properties
		 */
		
		File defaultPropertiesFile = new File("./Status/hosts/default.properties");
		
		if(defaultPropertiesFile.exists()) {
			
			try(InputStream defaultInput = new FileInputStream("./Status/hosts/default.properties")) {
				
				String operatingSystem = System.getProperty("os.name");
				
				Properties defaultProperties = new Properties();
				defaultProperties.load(defaultInput);
				
				try(OutputStream defaultOutput = new FileOutputStream("./Status/hosts/default.properties")) {
					
					defaultProperties.setProperty("name", "Default");
					defaultProperties.setProperty("host", "127.0.0.1");
					defaultProperties.setProperty("port", "ICMP");
					defaultProperties.setProperty("os", operatingSystem);
					defaultProperties.setProperty("description", "Runs the status.cdero.com site.");
					defaultProperties.setProperty("enabled", defaultProperties.getProperty("enabled"));
					
					defaultProperties.store(defaultOutput, "Status default server and template for additional machines.\n"
					+ "If port is left blank ICMP or TCP on port 7 (Echo) will be used.");
					
				}
				
			} catch (IOException ex) {
				
				ex.printStackTrace();
				
			} 
			
		}else {
			
			try(OutputStream defaultOutput = new FileOutputStream("./Status/hosts/default.properties")) {
				
				String operatingSystem = System.getProperty("os.name");
				
				Properties defaultProperties = new Properties();
				
				defaultProperties.setProperty("name", "Default");
				defaultProperties.setProperty("host", "127.0.0.1");
				defaultProperties.setProperty("port", "ICMP");
				defaultProperties.setProperty("os", operatingSystem);
				defaultProperties.setProperty("description", "Runs the status.cdero.com site.");
				defaultProperties.setProperty("enabled", "true");
				
				defaultProperties.store(defaultOutput, "Status default server and template for additional machines.\n"
				+ "Default port is going to be ICMP unless specified.");
				
			} catch (IOException ex) {
				
				ex.printStackTrace();
				
			}
			
		}
		
		/*
		 * End Default Properties
		 */
		
		//Start the Spring Boot Application
		SpringApplication.run(StatusApplication.class, args);
		
		// Add a shut down hook to handle closing operations
		Runtime.getRuntime().addShutdownHook(new Thread() {
			
			public void run() {
				
				PingLogger.close();
				
			}
			
		});
		
	}

}
