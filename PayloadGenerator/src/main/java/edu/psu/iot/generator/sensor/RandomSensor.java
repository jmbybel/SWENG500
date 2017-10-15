package edu.psu.iot.generator.sensor;

public class RandomSensor extends Sensor {

	public RandomSensor(SensorConfig config) {
		super(config);
		// TODO Auto-generated constructor stub
	}
	
	public double calcValue(){
		logger.info("currentValue Random: {}", getCurrentValue());
		return ((getMax()-getMin())*Math.random()) + getMin();
	}

}
