package edu.psu.iot.generator.sensor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Play {

	private static final Logger logger = LogManager.getLogger();
	
	public static void main(String[] args) {
		logger.debug(">>main()");
		SensorService ss = new SensorService();
		
		
		//Simulate the "Validator" sending us a config
		Sensor config = new Sensor();
		config.setInterval(1000);
		config.setName("Duration Sensor");
		config.setDuration(5000);
		config.setRandomInterval(false);
		config.setId(100);
		ss.createSensor(config);
		
		
		//Until we have validator, some sample sensors
		//Create a Random Sensor config
		Sensor randomConfig = new Sensor();
		randomConfig.setName("Random Sensor");
		randomConfig.setId(1);
		randomConfig.setInitialValue(72);
		randomConfig.setMax(90);
		randomConfig.setMin(60);
		randomConfig.setType(PayloadType.RANDOM);
		randomConfig.setRandomInterval(true);
		ss.createSensor(randomConfig);
		
		//Create a Binary Sensor config
		Sensor binaryConfig = new Sensor();
		binaryConfig.setName("Binary Sensor");
		binaryConfig.setId(2);
		binaryConfig.setInitialValue(1);
		binaryConfig.setMax(2);
		binaryConfig.setMin(1);
		binaryConfig.setType(PayloadType.BINARY);
		ss.createSensor(binaryConfig);
		
		//Create a Ramp Sensor config
		Sensor rampConfig = new Sensor();
		rampConfig.setName("Ramp Sensor");
		rampConfig.setId(3);
		rampConfig.setInitialValue(5);
		rampConfig.setMax(10);
		rampConfig.setMin(1);
		rampConfig.setType(PayloadType.RAMP);
		rampConfig.setRandomInterval(true);
		ss.createSensor(rampConfig);
		
		//Create a Sin Sensor config
		Sensor sinConfig = new Sensor();
		sinConfig.setName("Sin Sensor");
		sinConfig.setId(4);
		sinConfig.setInitialValue(0);
		sinConfig.setMax(0);
		sinConfig.setMin(0);
		sinConfig.setType(PayloadType.SIN);
		sinConfig.setRandomInterval(true);
		ss.createSensor(sinConfig);
		
		
		logger.info("SS SensorList: {}", ss.getSensorList());
		logger.debug("<<main()");
	}
}
