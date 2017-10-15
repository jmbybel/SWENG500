package edu.psu.iot.generator.sensor;

public class BinarySensor extends Sensor {

	public BinarySensor(SensorConfig config) {
		super(config);
		// TODO Auto-generated constructor stub
	}
	
	public double calcValue(){
		if(getCurrentValue() == getMax())
		{
			logger.info("currentValue Binary min: {}", getCurrentValue());
			return getMin();
		}
		else
		{
			logger.info("currentValue Binary max: {}", getCurrentValue());
			return getMax();
		}
	}

}

