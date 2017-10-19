package edu.psu.iot.generator.sensor;

public class BinarySensor extends Sensor {

	public BinarySensor(SensorConfig config) {
		super(config);
		// TODO Auto-generated constructor stub
	}
	
	public double calcValue(){
		logger.debug(">>calcValue()");
		if(getCurrentValue() == getMax())
		{
			logger.info("currentValue Binary min: {}", getCurrentValue());
			logger.debug("<<calcValue(Min)");
			return getMin();
		}
		else
		{
			logger.info("currentValue Binary max: {}", getCurrentValue());
			logger.debug("<<calcValue(Max)");
			return getMax();
		}
	}

}

