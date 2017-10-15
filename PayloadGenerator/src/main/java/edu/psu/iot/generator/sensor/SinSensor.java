package edu.psu.iot.generator.sensor;

public class SinSensor extends Sensor {

	public SinSensor(SensorConfig config) {
		super(config);
		// TODO Auto-generated constructor stub
	}
	
	public double calcValue(){
		
		sinValue += getSinInterval(); 
		logger.info("currentValue Sin: {}", getCurrentValue());
		logger.info("sinValue: {}", sinValue);
		
		return Math.sin(sinValue);
	}

}
