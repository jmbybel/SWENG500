package edu.psu.iot.generator.sensor;

public class SinSensor extends Sensor {

	public SinSensor(SensorConfig config) {
		super(config);
		// TODO Auto-generated constructor stub
	}
	
	public double calcValue(){
		logger.debug(">>calcValue(Sin)");
		sinValue += getSinInterval(); 
		logger.info("currentValue Sin: {}", getCurrentValue());
		logger.info("sinValue: {}", sinValue);
		logger.debug("<<calcValue(Sin)");
		return Math.sin(sinValue);
	}

}
