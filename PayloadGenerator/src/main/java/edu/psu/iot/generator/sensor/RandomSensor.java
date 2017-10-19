package edu.psu.iot.generator.sensor;

public class RandomSensor extends Sensor {

	public RandomSensor(SensorConfig config) {
		super(config);
		// TODO Auto-generated constructor stub
	}
	
	public double calcValue(){
		logger.debug(">>calcValue()");
		logger.info("currentValue Random: {}", getCurrentValue());
		logger.debug("<<calcValue()");
		return ((getMax()-getMin())*Math.random()) + getMin();
	}

}
