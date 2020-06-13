package com.cdero.status.ping;

import java.io.*;
import java.net.*;

/**
 * @author 	Christopher DeRoche
 * @version	0.2
 * @since	0.2
 * 
 */

public class PingSocket {
	
	private final static int TIMEOUT = 1000;
	
	public static boolean pingSocket(String host, int port) {
		
		PingLogger log = new PingLogger(PingSocket.class.getSimpleName());
		
		try (Socket socket = new Socket()){
			
			socket.connect(new InetSocketAddress(host, port), TIMEOUT);
			socket.close();
		
		}catch (SocketTimeoutException timeoutException) {
			
			log.warning("Connection timed out when trying to connect to host " + host + " on port " + port);
			log.close();
			
			return false;
			
		}catch (IOException ioException) {
			
			log.severe("Error when trying to connect to host " + host + " on port " + port);
			log.close();
			
			return false;
			
		}
		
		log.info("Successfully connected to host " + host + " on port " + port);
		log.close();
		
		return true;
		
	}

}
