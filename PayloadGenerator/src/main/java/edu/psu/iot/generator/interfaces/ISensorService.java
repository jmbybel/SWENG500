package edu.psu.iot.generator.interfaces;


import java.util.Map;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.json.JSONObject;
import edu.psu.iot.generator.queue.PayloadQueue;
import edu.psu.iot.generator.sensor.Payload;
import edu.psu.iot.generator.sensor.Sensor;

public interface ISensorService {
	
    public void initialize();
    
    public CircularFifoQueue<JSONObject> getQueue();
    
    public void clearQueue();
	
    public void createSensor(ISensor config);
    
    public Map<Integer,Payload> getSensorList();
    
    public void startSensor(int id);
    
    public void stopSensor(int id);
    
    public void deleteSensor(int id);
    
    public JSONObject getLastQueueItem();
    
}
