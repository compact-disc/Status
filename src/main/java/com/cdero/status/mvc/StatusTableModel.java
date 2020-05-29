package com.cdero.status.mvc;

/**
 * @author 	Christopher DeRoche
 * @version	0.0.1
 * @since	0.0.1
 * 
 */

public class StatusTableModel {
	
	protected String name;
	protected String host;
	protected String os;
	protected String services;
	protected String description;
	
	protected String getName() {
		
		return this.name;
		
	}
	
	protected void setName(String name) {
		
		this.name = name;
		
	}
	
	protected String getHost() {
		
		return this.host;
		
	}
	
	protected void setHost(String host) {
		
		this.host = host;
		
	}
	
	protected String getOS() {
		
		return this.os;
		
	}
	
	protected void setOS(String os) {
		
		
		this.os = os;
	}
	
	protected String getServices() {
		
		return this.services;
		
	}
	
	protected void setServices(String services) {
		
		this.services = services;
		
	}
	
	protected String getDescription() {
		
		return this.description;
		
	}
	
	protected void setDescription(String description) {
		
		this.description = description;
		
	}

}
