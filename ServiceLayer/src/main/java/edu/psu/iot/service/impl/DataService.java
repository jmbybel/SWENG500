package edu.psu.iot.service.impl;

import java.util.List;

import edu.psu.iot.database.DatabaseRepository;
import edu.psu.iot.database.mongodb.MongoRepository;
import edu.psu.iot.generator.interfaces.ISensorService;
import edu.psu.iot.generator.sensor.SensorService;
import edu.psu.iot.object.Device;
import edu.psu.iot.object.Payload;
import edu.psu.iot.object.ResponseData;
import edu.psu.iot.object.Sensor;
import edu.psu.iot.service.IDataService;

public class DataService implements IDataService {
	
	private DatabaseRepository repository = new MongoRepository();
	private ISensorService sensorService = new SensorService();
	
	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#getDeviceById(java.lang.String)
	 */
	@Override
	public Device getDeviceById(String id) {
		return repository.readDeviceById(id);
	}

	
	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#deleteDevice(java.lang.String)
	 */
	@Override
	public boolean deleteDevice(String id) {
		return repository.deleteDevice(id);
	}

	
	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#cloneDevice(edu.psu.iot.object.Device)
	 */
	@Override
	public Device cloneDevice(Device original) {
		original.setId(null);
		//sensors will need to be given new IDs too.
		for (Sensor s: original.getSensors()) {
			s.setId(null);
		}
		return repository.createDevice(original);
	}
	
	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#insertUpdateDevice(edu.psu.iot.object.Device)
	 */
	@Override
	public Device insertUpdateDevice(Device device) {
		if (device.getId() == null) {
			device = repository.createDevice(device);
		} else {
			device = repository.updateDevice(device);
		}
		return device;
	}
	
	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#getAllDevices()
	 */
	@Override
	public List<Device> getAllDevices() {
		return repository.getAllDevices();
	}
	
	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#getSensorById(java.lang.String)
	 */
	@Override
	public Sensor getSensorById(String id) {
		return repository.readSensorById(id);
	}

	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#deleteSensor(java.lang.String)
	 */
	@Override
	public boolean deleteSensor(String id) {
		
		//TODO uncomment the sensorService call
//		sensorService.deleteSensor(id);
		return repository.deleteSensor(id);
	}

	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#cloneSensor(edu.psu.iot.object.Sensor)
	 */
	@Override
	public Sensor cloneSensor(Sensor original) {
		original.setId(null);
		
		//TODO
//		sensorService.createSensor(some method in SensorService to copy an existing sensor?);
		return repository.createSensor(original);
	}


	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#insertUpdateSensor(edu.psu.iot.object.Sensor)
	 */
	@Override
	public Sensor insertUpdateSensor(Sensor sensor) {
		if (sensor.getId() == null) {
			sensor = repository.createSensor(sensor);
		} else {
			sensor = repository.updateSensor(sensor);
		}
		return sensor;
	}
	
	/**
	 * a direct call to the Sensor Service to add it to the list of active sensors for test data generation.
	 * @param sensorId
	 * @return
	 */
	public Boolean startSensor(String sensorId) {
		//TODO
//		return sensorService.startSensor(id);
		return null;
	}
	
	/**
	 * a direct call to the Sensor Service to remove it from the list of active sensors for test data generation.
	 * @param sensorId
	 * @return
	 */
	public Boolean stopSensor(String sensorId) {
		//TODO
//		return sensorService.stopSensor(id);
		return null;
	}
	
	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#getPayloadById(java.lang.String)
	 */
	@Override
	public Payload getPayloadById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#deletePayload(java.lang.String)
	 */
	@Override
	public boolean deletePayload(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#insertPayload(edu.psu.iot.object.Payload)
	 */
	@Override
	public Payload insertPayload(Payload sensor) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#getResponseDataById(java.lang.String)
	 */
	@Override
	public ResponseData getResponseDataById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#deleteResponseData(java.lang.String)
	 */
	@Override
	public boolean deleteResponseData(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	
	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#insertResponseData(edu.psu.iot.object.ResponseData)
	 */
	@Override
	public ResponseData insertResponseData(ResponseData sensor) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#getAllPayloadResponsesBySensor(java.lang.String)
	 */
	@Override
	public List<ResponseData> getAllPayloadResponsesBySensor(String sensorId) {
		return repository.getAllPayloadResponsesBySensorId(sensorId);
	}

	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#getDatabaseAccess()
	 */
	public DatabaseRepository getRepository() {
		return repository;
	}

	public void setRepository(DatabaseRepository repository) {
		this.repository = repository;
	}
	
	
}