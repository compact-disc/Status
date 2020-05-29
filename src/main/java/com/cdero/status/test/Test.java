package com.cdero.status.test;

/**
 * @author 	Christopher DeRoche
 * @version	0.0.1
 * @since	0.0.1
 * 
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Test {
	
	private static final Logger log = LoggerFactory.getLogger(Test.class);
	
	//Run this method every 60 seconds (60 seconds = 60,000)
	@Scheduled(fixedRate = 60000)
	public void printOutFromSchedule() {
		
		log.info("This is a timed line.");
		
	}

}
