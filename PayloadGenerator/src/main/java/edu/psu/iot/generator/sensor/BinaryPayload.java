package edu.psu.iot.generator.sensor;

import edu.psu.iot.generator.interfaces.ISensor;

public class BinaryPayload extends Payload {

	public BinaryPayload(ISensor config) {
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

