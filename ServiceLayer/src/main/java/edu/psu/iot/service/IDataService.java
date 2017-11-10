package edu.psu.iot.service;

import edu.psu.iot.generator.sensor.PayloadType;

public interface IDataService {
	//sensorSchema:
	
	/*
	 *
	 * 
	 {
		 name:String,
		 id:number,
		 initialValue: number,
		 max:number,
		 min:number,
		 duration:number, 
		 interval: number,
		 type: String, (one of several enum values)
		 sinInterval: number,
		 minInterval: number,
		 maxInterval: number,
		 isRandomInterval: boolean,
		 randomInterval: number
	 }

	void setRandomInterval(boolean randomInterval);
	 * 
	 * 
	 */
	
	
	String getNumberOfRunningSensors();
	/*
	 * return: {count:number}
	 */
		
	boolean setDestinationIP(String urlEndpoint);
	/*
	 * input: {urlEnpoint:String}
	 * Example: "http://18.216.43.18:8081/contentListener"
	 */
	
	String getAllSensors();
	/*
	 * return: {sensor:[sensorSchema, sensorSchema...]}
	 * 
	 * Need to add the sensors schema.
	 */
	
	String getSensor(String id);
	/*
	 * input: {id:number}
	 */
		
	boolean startSensor(String id);
	/*
	 * input: {id:number}
	 */
	
	boolean pauseSensor(String id);
	/*
	 * input: {id:number}
	 */
	
	boolean createSensor(String sensorSchema);
	/*
	 * input: sensorSchema
	 */
	
	boolean updateSensor(String sensorSchema);
	/*
	 * input: sensorSchema
	 */
	
	boolean deleteSensor(String id);
	/*
	 * input: {id:number}
	 */
	
	boolean batchStart(String ids);
	/*
	 * input: {id:[number, number, number...]}
	 */
	
	boolean batchStop(String ids);
	/*
	 * input: {id:[number,number,number...]}
	 */
	
	String batchQuery(String id);
	/*
	 * input: {id:number}
	 */
}