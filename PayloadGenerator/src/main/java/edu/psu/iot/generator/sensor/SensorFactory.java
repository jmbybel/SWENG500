package edu.psu.iot.generator.sensor;

public final class SensorFactory{
	
	public static Sensor createSensor(SensorConfig config) throws SensorTypeInvalidException{
		
		Sensor sensor;
		
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
		
		return sensor;
		
	}

}
