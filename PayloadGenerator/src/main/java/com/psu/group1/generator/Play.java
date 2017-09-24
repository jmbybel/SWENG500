package com.psu.group1.generator;

public class Play {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SensorService ss = new SensorService();
		ss.createSensor("New Sensor", 1, 72, 90, 60, 0, 1000, SensorType.RANDOM);
	}

}
