package com.psu.group1.generator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Play {

	private static final Logger logger = LogManager.getLogger();
	
	public static void main(String[] args) {
		logger.debug(">>main()");
		// TODO Auto-generated method stub
		SensorService ss = new SensorService();
		ss.createSensor("Random Sensor", 1, 72, 90, 60, 0, 1000, SensorType.RANDOM, 0, 1000, 5000, true);
		ss.createSensor("Binary Sensor", 2, 1, 2, 1, 0, 1000, SensorType.BINARY, 0, 1000, 5000, false);
		ss.createSensor("Ramp Sensor", 3, 5, 10, 1, 0, 1000, SensorType.RAMP, 0, 1000, 5000, true);
		ss.createSensor("Sin Sensor", 4, 0, 0, 0, 0, 1000, SensorType.SIN, 10, 1000, 5000, true);
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
