package edu.psu.iot.generator.interfaces;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.Executors;

import org.json.JSONObject;

import edu.psu.iot.generator.queue.PayloadQueue;
import edu.psu.iot.generator.sensor.Sensor;
import edu.psu.iot.generator.sensor.SensorConfig;

public interface ISensorService {
	
    public void initialize();
    
    public LinkedList<JSONObject> getQueue();
    
    public void clearQueue();
	
    public void createSensor(SensorConfig config);
    
    public Map<Integer,Sensor> getSensorList();
    
    public void startSensor(int id);
    
    public void stopSensor(int id);
    
    public void deleteSensor(int id);
}
