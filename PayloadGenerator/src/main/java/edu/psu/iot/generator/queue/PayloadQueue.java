package edu.psu.iot.generator.queue;

import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public final class PayloadQueue {
	private static final Logger logger = LogManager.getLogger();
	public static LinkedList<JSONObject> queue = new LinkedList<JSONObject>();
	
	public PayloadQueue() {
		// TODO Auto-generated constructor stub
	}
	
	public static LinkedList<JSONObject> getQueue(){
		logger.debug(">>getQueue()");
		logger.debug("<<getQueue()");
		logger.info("queue: {}", queue);
		return queue;
	}

}
