package com.cdero.status.mvc;

/**
 * @author 	Christopher DeRoche
 * @version	0.3
 * @since	0.1
 * 
 */

public class StatusTableModel {
	
	public String name;
	public String host;
	public String os;
	public String description;
	public String enabled;
	public boolean status;
	public String statusDivHTML;
	public String port;
	
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
			
			this.statusDivHTML = "<div class=\"status\" style=\"background-color: "+ "#008000" +";\"><br class=\"statusBr\">" + "Online" + "</div>";
			
		}else {
			
			this.statusDivHTML = "<div class=\"status\" style=\"background-color: "+ "#FF0000" +";\"><br class=\"statusBr\">" + "Offline" + "</div>";
			
		}
		
		this.status = status;
		
	}
	
	public String getStatusString() {
		
		return this.statusDivHTML;
		
	}
	
	public void setPort(String port) {
		
		this.port = port;
		
	}
	
	public String getPort() {
		
		return this.port;
		
	}

}
