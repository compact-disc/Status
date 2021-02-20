package com.cdero.status.ping;

import java.io.*;
import java.net.*;

/**
 * @author 	Christopher DeRoche
 * @version	0.3
 * @since	0.2
 * 
 */

public class PingTCP {
	
	private final static int TIMEOUT = 1000;
	
	public static boolean ping(String host, int port) {
		
		try (Socket socket = new Socket()){
			
			socket.connect(new InetSocketAddress(host, port), TIMEOUT);
			socket.close();
		
		}catch (SocketTimeoutException timeoutException) {
			
			PingLogger.warning("Connection timed out when trying to connect to host " + host + " on port " + port);
			
			return false;
			
		}catch (IOException ioException) {
			
			PingLogger.warning("Error when trying to connect to host " + host + " on port " + port);
			
			return false;
			
		}
			
		PingLogger.info("Successfully connected to host " + host + " on port " + port);
		
		return true;
		
	}

}
