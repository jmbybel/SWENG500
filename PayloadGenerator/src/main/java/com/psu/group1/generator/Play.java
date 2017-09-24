package com.psu.group1.generator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Play {

	private static final Logger logger = LogManager.getLogger();
	
	public static void main(String[] args) {
		logger.debug(">>main()");
		// TODO Auto-generated method stub
		SensorService ss = new SensorService();
		ss.createSensor("New Sensor", 1, 72, 90, 60, 0, 1000, SensorType.RANDOM, 1000, 5000, true);
		logger.info("SS SensorList: {}", ss.sensorList);
		/*try {
			Thread.sleep(10000);
			ss.stopSensor(1);
			Thread.sleep(5000);
			ss.startSensor(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		logger.debug("<<main()");
	}
}
