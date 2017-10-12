package edu.psu.iot.generator.sensor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Util {
	private static final Logger logger = LogManager.getLogger();
	public static long getRandomLong(long min, long max) {
		logger.debug(">>getRandomLong()");
	    long leftLimit = min;
	    long rightLimit =max;
	    logger.info("leftLimit: {}", leftLimit);
	    logger.info("rightLimit: {}", rightLimit);
	    logger.debug("<<getRandomLong()");
	    return (leftLimit + (long) (Math.random() * (rightLimit - leftLimit)));
	}

}
