package edu.psu.iot.generator.sensor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.psu.iot.generator.interfaces.ISensor;

public final class PayloadFactory{
	private static final Logger logger = LogManager.getLogger();
	public static Payload createSensor(ISensor config) throws PayloadTypeInvalidException{
		logger.debug(">>createSensorSensorFactory()");
		Payload sensor;
		logger.info("SensorType: {}", config.getType());
		if(config.getType() == PayloadType.BINARY){
			sensor = new BinaryPayload(config);
		}
		else if(config.getType() == PayloadType.RAMP){
			sensor = new RampPayload(config);
		}
		else if(config.getType() == PayloadType.SIN){
			sensor = new SinPayload(config);
		}
		else if(config.getType() == PayloadType.RANDOM){
			sensor = new RandomPayload(config);
		}
		else
		{
			throw new PayloadTypeInvalidException("Sensor must be of type: " + PayloadType.values().toString());
		}
		logger.debug("<<createSensorSensorFactory()");
		return sensor;
		
	}

}
