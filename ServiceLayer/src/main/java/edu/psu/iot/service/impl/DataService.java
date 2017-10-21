package edu.psu.iot.service.impl;

import java.util.List;

import edu.psu.iot.database.DatabaseRepository;
import edu.psu.iot.database.mongodb.MongoRepository;
import edu.psu.iot.generator.interfaces.ISensorService;
import edu.psu.iot.generator.sensor.SensorService;
import edu.psu.iot.object.Payload;
import edu.psu.iot.object.Sensor;
import edu.psu.iot.service.IDataService;

public class DataService implements IDataService {
	
	private DatabaseRepository repository = new MongoRepository();
	private ISensorService sensorService = new SensorService();
	
	public List<Sensor> getAllSensors() {
		return repository.getAllSensors();
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
	 * @see edu.psu.iot.service.impl.IDataService#getAllPayloadResponsesBySensor(java.lang.String)
	 */
	@Override
	public List<Payload> getAllPayloadsBySensor(String sensorId) {
		return repository.getAllPayloadsBySensorId(sensorId);
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