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
 * @version	0.2
 * @since	0.1
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
			defaultStatus.setPort(this.defaultProperties.getProperty("port"));
			defaultStatus.setDescription(this.defaultProperties.getProperty("description"));
			defaultStatus.setEnabled(this.defaultProperties.getProperty("enabled"));
			defaultStatus.setStatus(Ping.pingHost(this.defaultProperties.getProperty("host")));
			
			defaultStatus.setPort("ICMP");
			
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
					statusModel.setPort(hostProperties.getProperty("port"));
					statusModel.setDescription(hostProperties.getProperty("description"));
					statusModel.setEnabled(hostProperties.getProperty("enabled"));
					
					if(statusModel.getPort().isEmpty() || statusModel.getPort().equalsIgnoreCase("icmp")) {
						
						statusModel.setStatus(Ping.pingHost(statusModel.getHost()));
						statusModel.setPort("ICMP");
						
					}else {
						
						statusModel.setStatus(PingSocket.pingSocket(statusModel.getHost(), Integer.parseInt(statusModel.getPort())));
						
					}
					
					
					statusTable.add(statusModel);
					
				}
				
			}
			
		}
		
	}

}
