package com.psu.group1.generator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Payload implements Runnable {
	
	private static final Logger logger = LogManager.getLogger();
	
	public void run() {
		logger.debug(">>run()");
		
		double temp = ((Math.random() * 20) + 60);
		logger.info("temp: {}", temp);
		System.out.println("Sensor value: " + temp + " degrees F");
		
		logger.debug("<<run()");
	}

}
