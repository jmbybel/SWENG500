package edu.psu.iot.generator.interfaces;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.Executors;

import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.json.JSONObject;

import edu.psu.iot.generator.queue.PayloadQueue;
import edu.psu.iot.generator.sensor.Payload;
import edu.psu.iot.generator.sensor.Sensor;

public interface ISensorService {
	
    public void initialize();
    
    public CircularFifoQueue<JSONObject> getQueue();
    
    public void clearQueue();
	
    public void createSensor(Sensor config);
    
    public Map<Integer,Payload> getSensorList();
    
    public void startSensor(int id);
    
    public void stopSensor(int id);
    
    public void deleteSensor(int id);
    
    public JSONObject getLastQueueItem();
}
