package com.psu.group1.generator;

public class Payload implements Runnable {

	public void run() {
		
		double temp = ((Math.random() * 20) + 60);
		System.out.println("Sensor value: " + temp + " degrees F");
		
	}

}
