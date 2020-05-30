package com.cdero.status.ping;

import java.io.*;
import java.net.*;

/**
 * @author 	Christopher DeRoche
 * @version	0.0.1
 * @since	0.0.1
 * 
 */

public class Ping {
	
	public static boolean pingHost(String host) {
		
		PingLogger log = new PingLogger();
		
		InetAddress address = null;
		
		try {
			
			address = InetAddress.getByName(host);
			
			if(!address.isReachable(100)) {
				
				log.warning("Unable to connect to host " + host);
				log.close();
				
				return false;
				
			}
			
		} catch (IOException ex) {
			
			log.severe("IOException when trying to connect to host " + host);
			log.close();
			
			return false;
			
		}
		
		log.info("Successfully connected to host " + host);
		log.close();
		
		return true;
		
	}

}
