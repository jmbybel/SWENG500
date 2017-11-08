package edu.psu.iot.service;

import java.util.List;

import edu.psu.iot.database.DatabaseRepository;
import edu.psu.iot.object.Payload;
import edu.psu.iot.object.Sensor;

public interface IDataServiceStub extends IDataService  {
	String getNumberOfRunningSensors();
}