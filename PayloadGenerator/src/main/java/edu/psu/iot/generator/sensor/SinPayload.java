package edu.psu.iot.generator.sensor;

import edu.psu.iot.generator.interfaces.ISensor;

public class SinPayload extends Payload {

	public SinPayload(ISensor config) {
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
