package edu.psu.iot.service;

import edu.psu.iot.generator.sensor.PayloadType;

public interface IDataService {
	//sensorSchema:
	
	/*
	 *
	 * 
	 {
		 name:String,
		 "_id":number,
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
	
	String getDestinationIP();
	
	String getAllSensors();
	/*
	 * return: {[sensorSchema, sensorSchema...]}
	 * 
	 */
	
	String getSensor(String id);
	/*
	 * input: {"_id":number}
	 */
		
	boolean startSensor(String id);
	/*
	 * input: {"_id":number}
	 */
	
	boolean pauseSensor(String id);
	/*
	 * input: {"_id":number}
	 */
	
	String createSensor(String sensorSchema);
	/*
	 * input: sensorSchema
	 */
	
	String updateSensor(String sensorSchema);
	/*
	 * input: sensorSchema
	 */
	
	boolean deleteSensor(String id);
	
	boolean deleteAll(); // Clears the whole database!
	
	//Pruned out of backlog at Sprint 3.
	
	/*
	 * input: {"_id":number}
	 */
	/*
	boolean batchStart(String ids);
	/*
	 * input: {"_id":[number, number, number...]}
	 */
	/*
	boolean batchStop(String ids);
	/*
	 * input: {"_id":[number,number,number...]}
	 */
	/*
	String batchQuery(String id);
	/*
	 * input: {"_id":number}
	 */
	
	
}