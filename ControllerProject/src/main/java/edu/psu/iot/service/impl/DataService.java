package edu.psu.iot.service.impl;

import java.io.File;
import java.util.List;
import java.util.UUID;

import edu.psu.iot.database.DatabaseRepository;
import edu.psu.iot.database.mongodb.MongoRepository;
import edu.psu.iot.object.Device;
import edu.psu.iot.object.Payload;
import edu.psu.iot.object.ResponseData;
import edu.psu.iot.object.Sensor;
import edu.psu.iot.service.IDataService;

public class DataService implements IDataService {
	
	private DatabaseRepository repository = new MongoRepository();

	
	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#getDataGeneratorAsDownloadbleFile(edu.psu.iot.object.Device)
	 */
	@Override
	public File getDataGeneratorAsDownloadbleFile(Device theDeviceToConvert) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#createDataGeneratorFromUploadedFile(java.io.File)
	 */
	@Override
	public Device createDataGeneratorFromUploadedFile(File incomingFile) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#updateDataGeneratorInputFields(edu.psu.iot.object.Device)
	 */
	@Override
	public void updateDataGeneratorInputFields(Device theDeviceToAlter) {
		// TODO Auto-generated method stub
		
	}

	
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
		giveIdsToSensors(original);
		return repository.createDevice(original);
	}
	
	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#insertUpdateDevice(edu.psu.iot.object.Device)
	 */
	@Override
	public Device insertUpdateDevice(Device device) {
		giveIdsToSensors(device);
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
/*
	
	public DeviceCluster getClusterByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean deleteCluster(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public DeviceCluster cloneCluster(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public DeviceCluster insertUpdateCluster(DeviceCluster cluster) {
		// TODO Auto-generated method stub
		return null;
	}
*/
	
	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#getSensorById(java.lang.String)
	 */
	@Override
	@Deprecated
	public Sensor getSensorById(String id) {
		return repository.readSensorById(id);
	}

	//TODO needs a rework
	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#deleteSensor(java.lang.String)
	 */
	@Override
	@Deprecated
	public boolean deleteSensor(String id) {
		return repository.deleteSensor(id);
	}

	//TODO needs a rework
	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#cloneSensor(edu.psu.iot.object.Sensor)
	 */
	@Override
	@Deprecated
	public Sensor cloneSensor(Sensor original) {
		original.setId(null);
		return repository.createSensor(original);
	}


	/* (non-Javadoc)
	 * @see edu.psu.iot.service.impl.IDataService#insertUpdateSensor(edu.psu.iot.object.Sensor)
	 */
	@Override
	@Deprecated
	public Sensor insertUpdateSensor(Sensor sensor) {
		if (sensor.getId() == null) {
			sensor = repository.createSensor(sensor);
		} else {
			sensor = repository.updateSensor(sensor);
		}
		return sensor;
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

	
	//have Java generate IDs for the sensors we're inserting with the device.
	private void giveIdsToSensors(Device theDevice) {
		if (!theDevice.getSensors().isEmpty()) {
			for (Sensor aSensor: theDevice.getSensors()) {
				if (aSensor.getId() == null) {//make a new UUID for that sensor before persisting.
					aSensor.setId(UUID.randomUUID().toString());
				}
			}
		}
	}


	public DatabaseRepository getRepository() {
		return repository;
	}


	public void setRepository(DatabaseRepository repository) {
		this.repository = repository;
	}
	
	
}