package edu.psu.iot.generator.sensor;

import edu.psu.iot.generator.interfaces.ISensor;

public class RandomPayload extends Payload {

	public RandomPayload(ISensor config) {
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
