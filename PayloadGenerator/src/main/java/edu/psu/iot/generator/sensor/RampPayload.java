package edu.psu.iot.generator.sensor;

import edu.psu.iot.generator.interfaces.ISensor;

public class RampPayload extends Payload {

	public RampPayload(ISensor config) {
		super(config);
		// TODO Auto-generated constructor stub
		
	}
	
	public double calcValue(){
		logger.debug(">>calcValue()");
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
			logger.debug("<<calcValue(RampUp)");
			return getCurrentValue() + 1;
		}
		else
		{
			
			logger.info("currentValue Ramp down: {}", getCurrentValue());
			rampFlagDown = true;
			logger.debug("<<calcValue(RampDown)");
			return getCurrentValue() - 1;
		}
	}
	
}
