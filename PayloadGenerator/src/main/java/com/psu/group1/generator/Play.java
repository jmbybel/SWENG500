package com.psu.group1.generator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Play {

	private static final Logger logger = LogManager.getLogger();
	
	public static void main(String[] args) {
		logger.debug(">>main()");
		SensorService ss = new SensorService();
		
		
		//Simulate the "Validator" sending us a config
		SensorConfig config = new SensorConfig();
		config.setInterval(1000);
		config.setRandomInterval(false);
		ss.createSensor(config);
		
		
		//Until we have validator, some sample sensors
		//Create a Random Sensor config
		SensorConfig randomConfig = new SensorConfig();
		randomConfig.setName("Random Sensor");
		randomConfig.setId(1);
		randomConfig.setInitialValue(72);
		randomConfig.setMax(90);
		randomConfig.setMin(60);
		randomConfig.setType(SensorType.RANDOM);
		randomConfig.setRandomInterval(true);
		ss.createSensor(randomConfig);
		
		//Create a Binary Sensor config
		SensorConfig binaryConfig = new SensorConfig();
		binaryConfig.setName("Binary Sensor");
		binaryConfig.setId(2);
		binaryConfig.setInitialValue(1);
		binaryConfig.setMax(2);
		binaryConfig.setMin(1);
		binaryConfig.setType(SensorType.BINARY);
		ss.createSensor(binaryConfig);
		
		//Create a Ramp Sensor config
		SensorConfig rampConfig = new SensorConfig();
		rampConfig.setName("Ramp Sensor");
		rampConfig.setId(3);
		rampConfig.setInitialValue(5);
		rampConfig.setMax(10);
		rampConfig.setMin(1);
		rampConfig.setType(SensorType.RAMP);
		rampConfig.setRandomInterval(true);
		ss.createSensor(rampConfig);
		
		//Create a Sin Sensor config
		SensorConfig sinConfig = new SensorConfig();
		sinConfig.setName("Sin Sensor");
		sinConfig.setId(4);
		sinConfig.setInitialValue(0);
		sinConfig.setMax(0);
		sinConfig.setMin(0);
		sinConfig.setType(SensorType.SIN);
		sinConfig.setRandomInterval(true);
		ss.createSensor(sinConfig);
		
		
		logger.info("SS SensorList: {}", ss.sensorList);
		logger.debug("<<main()");
	}
}
