package com.psu.group1.generator;

public class Play {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SensorService ss = new SensorService();
		ss.createSensor("New Sensor", 1, 72, 90, 60, 0, 1000, SensorType.RANDOM, 1000, 5000, true);
		/*try {
			Thread.sleep(10000);
			ss.stopSensor(1);
			Thread.sleep(5000);
			ss.startSensor(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
