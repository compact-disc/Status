package com.cdero.status.mvc;

/**
 * @author 	Christopher DeRoche
 * @version	0.0.1
 * @since	0.0.1
 * 
 */

public class StatusTableModel {
	
	public String name;
	public String host;
	public String os;
	public String services;
	public String description;
	public String enabled;
	public boolean status;
	public String statusString;
	
	public String getName() {
		
		return this.name;
		
	}
	
	public void setName(String name) {
		
		this.name = name;
		
	}
	
	public String getHost() {
		
		return this.host;
		
	}
	
	public void setHost(String host) {
		
		this.host = host;
		
	}
	
	public String getOS() {
		
		return this.os;
		
	}
	
	public void setOS(String os) {
		
		
		this.os = os;
	}
	
	public String getServices() {
		
		return this.services;
		
	}
	
	public void setServices(String services) {
		
		this.services = services;
		
	}
	
	public String getDescription() {
		
		return this.description;
		
	}
	
	public void setDescription(String description) {
		
		this.description = description;
		
	}
	
	public String getEnabled() {
		
		return this.enabled;
		
	}
	
	public void setEnabled(String enabled) {
		
		this.enabled = enabled;
		
	}
	
	public boolean getStatus() {
		
		return this.status;
		
	}
	
	public void setStatus(boolean status) {
		
		if(status) {
			
			this.statusString = "<div class=\"status\" style=\"background-color: "+ "#008000" +";\"> " + "Online" + "</div>";
			
		}else {
			
			this.statusString = "<div class=\"status\" style=\"background-color: "+ "#FF0000" +";\"> " + "Offline" + "</div>";
			
		}
		
		this.status = status;
		
	}
	
	public String getStatusString() {
		
		return this.statusString;
		
	}

}
