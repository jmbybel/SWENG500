package edu.psu.iot.generator.queue;

import java.util.LinkedList;

import org.json.JSONObject;

public final class PayloadQueue {

	public static LinkedList<JSONObject> queue = new LinkedList<JSONObject>();
	
	public PayloadQueue() {
		// TODO Auto-generated constructor stub
	}
	
	public static LinkedList<JSONObject> getQueue(){
		return queue;
	}

}
