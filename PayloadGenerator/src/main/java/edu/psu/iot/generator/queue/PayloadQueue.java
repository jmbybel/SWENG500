package edu.psu.iot.generator.queue;

import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public final class PayloadQueue {
	private static final Logger logger = LogManager.getLogger();
	public static CircularFifoQueue<JSONObject> queue = new CircularFifoQueue<JSONObject>(1000);
	
	public PayloadQueue() {
		// TODO Auto-generated constructor stub	  
	}
	
	public static CircularFifoQueue<JSONObject> getQueue(){
		logger.debug(">>getQueue()");
		logger.debug("<<getQueue()");
		logger.info("queue: {}", queue);
		return queue;
	}

}
