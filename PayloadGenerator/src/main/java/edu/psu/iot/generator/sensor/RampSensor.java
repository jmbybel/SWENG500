package edu.psu.iot.generator.sensor;

public class RampSensor extends Sensor {

	public RampSensor(SensorConfig config) {
		super(config);
		// TODO Auto-generated constructor stub
		
	}
	
	public double calcValue(){
		if(getCurrentValue() == getMax())
		{
			rampFlagUp = false;
			rampFlagDown = true;
			logger.info("currentValue Ramp max: {}", getCurrentValue());
		}
		if(getCurrentValue() == getMin())
		{
			rampFlagUp = true;
			rampFlagDown = false;
			logger.info("currentValue Ramp min: {}", getCurrentValue());
		}
		
		if(getCurrentValue() <= getMax() && rampFlagUp == true)
		{
			
			logger.info("currentValue Ramp up: {}", getCurrentValue());
			rampFlagUp = true;
			return getCurrentValue() + 1;
		}
		else
		{
			
			logger.info("currentValue Ramp down: {}", getCurrentValue());
			rampFlagDown = true;
			return getCurrentValue() - 1;
		}
	}
	
}
