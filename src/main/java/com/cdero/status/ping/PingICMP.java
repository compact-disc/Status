package com.cdero.status.ping;

import java.io.*;
import java.net.*;

/**
 * @author 	Christopher DeRoche
 * @version	0.3
 * @since	0.1
 * 
 */

public class PingICMP {
	
	private final static int TIMEOUT = 1000;
	
	public static boolean ping(String host) {
		
		InetAddress address = null;
		
		try {
			
			address = InetAddress.getByName(host);
			
			if(!address.isReachable(TIMEOUT)) {
				
				PingLogger.warning("Connection timed out when trying to connect to host " + host);
				
				return false;
				
			}
			
		} catch (IOException ioException) {
			
			PingLogger.warning("Error when trying to connect to host " + host);
			
			return false;
			
		}
			
		PingLogger.info("Successfully connected to host " + host);
		
		return true;
		
	}

}
