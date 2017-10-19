package edu.psu.iot.generator.sensor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class SensorFactory{
	private static final Logger logger = LogManager.getLogger();
	public static Sensor createSensor(SensorConfig config) throws SensorTypeInvalidException{
		logger.debug(">>createSensorSensorFactory()");
		Sensor sensor;
		logger.info("SensorType: {}", config.type);
		if(config.getType() == SensorType.BINARY){
			sensor = new BinarySensor(config);
		}
		else if(config.getType() == SensorType.RAMP){
			sensor = new RampSensor(config);
		}
		else if(config.getType() == SensorType.SIN){
			sensor = new SinSensor(config);
		}
		else if(config.getType() == SensorType.RANDOM){
			sensor = new RandomSensor(config);
		}
		else
		{
			throw new SensorTypeInvalidException("Sensor must be of type: " + SensorType.values().toString());
		}
		logger.debug("<<createSensorSensorFactory()");
		return sensor;
		
	}

}
