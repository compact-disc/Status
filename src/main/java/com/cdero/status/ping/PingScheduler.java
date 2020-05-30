package com.cdero.status.ping;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.cdero.status.StatusApplication;
import com.cdero.status.mvc.StatusTableModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author 	Christopher DeRoche
 * @version	0.0.1
 * @since	0.0.1
 * 
 */

@Component
public class PingScheduler {
	
	private static ArrayList<StatusTableModel> statusTable;
	private Properties defaultProperties;
	
	@Scheduled(fixedRate = 600000)
	private void checkStatus() {
		
		statusTable = new ArrayList<>();
		
		loadDefaultProperties();
		checkStatusOfAllMachines();
		
	}
	
	public static ArrayList<StatusTableModel> getStatusTable() {
		
		return statusTable;
		
	}
	
	private void loadDefaultProperties() {
		
		this.defaultProperties = new Properties();
		
		try(InputStream defaultInput = new FileInputStream("./Status/hosts/default.properties")) {

			this.defaultProperties.load(defaultInput);
			
		} catch (IOException ex) {
			
			ex.printStackTrace();
			
		} 
		
		if(this.defaultProperties.getProperty("enabled").equals("false")) {
			
			return;
			
		}else {
			
			StatusTableModel defaultStatus = new StatusTableModel();
			defaultStatus.setName(this.defaultProperties.getProperty("name"));
			defaultStatus.setHost(this.defaultProperties.getProperty("host"));
			defaultStatus.setOS(this.defaultProperties.getProperty("os"));
			defaultStatus.setServices(this.defaultProperties.getProperty("services"));
			defaultStatus.setDescription(this.defaultProperties.getProperty("description"));
			defaultStatus.setEnabled(this.defaultProperties.getProperty("enabled"));
			defaultStatus.setStatus(Ping.pingHost(this.defaultProperties.getProperty("host")));
			
			statusTable.add(defaultStatus);
			
		}
		
	}
	
	private void checkStatusOfAllMachines() {
		
		File hostsDirectory = new File("./Status/hosts/");
		File[] listOfFiles = hostsDirectory.listFiles();
		
		if(listOfFiles != null) {
			
			for(File host : listOfFiles) {
				
				if(host.getName().equals("default.properties")) {
					
					continue;
					
				}
				
				Properties hostProperties = new Properties();
				StatusTableModel statusModel = new StatusTableModel();
				
				try(InputStream properyInput = new FileInputStream("./Status/hosts/" + host.getName())) {
					
					hostProperties.load(properyInput);
					
				} catch (IOException ex) {
					
					ex.printStackTrace();
					
				}
				
				if(hostProperties.getProperty("enabled").equals("true")) {
					
					statusModel.setName(hostProperties.getProperty("name"));
					statusModel.setHost(hostProperties.getProperty("host"));
					statusModel.setOS(hostProperties.getProperty("os"));
					statusModel.setServices(hostProperties.getProperty("services"));
					statusModel.setDescription(hostProperties.getProperty("description"));
					statusModel.setEnabled(hostProperties.getProperty("enabled"));
					statusModel.setStatus(Ping.pingHost(hostProperties.getProperty("host")));
					
					statusTable.add(statusModel);
					
				}
				
			}
			
		}
		
	}

}
