package edu.psu.iot.generator.interfaces;

import java.util.Map;

import edu.psu.iot.generator.sensor.Sensor;
import edu.psu.iot.generator.sensor.SensorConfig;

public interface ISensorService {
	
    public void createSensor(SensorConfig config);
    
    public Map<Integer,Sensor> getSensorList();
    
    public void startSensor(int id);
    
    public void stopSensor(int id);
    
    public void deleteSensor(int id);
}
