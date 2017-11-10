package edu.psu.iot.service.impl;



import edu.psu.iot.service.IDataService;


public class DataServiceStub implements IDataService {


	
	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#getSensorById(java.lang.String)
	 */
	@Override
	public String getNumberOfRunningSensors() {
		return "14";
	}

	@Override
	public boolean setDestinationIP(String urlEndpoint) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getAllSensors() {
		// TODO Auto-generated method stub
		return "[]";
	}

	@Override
	public String getSensor(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean startSensor(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pauseSensor(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createSensor(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateSensor(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteSensor(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean batchStart(String ids) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean batchStop(String ids) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String batchQuery(String batchQuery) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}