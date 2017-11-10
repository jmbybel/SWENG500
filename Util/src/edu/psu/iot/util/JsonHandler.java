package edu.psu.iot.util;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.bson.Document;
import org.bson.types.ObjectId;

import edu.psu.iot.generator.interfaces.ISensor;
import edu.psu.iot.generator.sensor.PayloadType;
import edu.psu.iot.generator.sensor.Sensor;

public class JsonHandler {
	
	private static ObjectMapper objectMapper = new ObjectMapper();

	public JsonHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public static Document documentFromSensor(ISensor sensor) {
		Document document = new Document("_id", sensor.getId())
				.append("duration", sensor.getDuration())
				.append("initialValue", sensor.getInitialValue())
				.append("interval", sensor.getInterval())
				.append("max", sensor.getMax())
				.append("maxInterval", sensor.getMinInterval())
				.append("min", sensor.getMin())
				.append("minInterval", sensor.getMinInterval())
				.append("name", sensor.getName())
				.append("randomInterval", sensor.isRandomInterval())
				.append("sinInterval", sensor.getSinInterval())
				.append("type", sensor.getType().toString());
		
		return document;
		
	}
	
	public static String buildSingleInt(String key, int value) {
		ObjectNode node = objectMapper.createObjectNode();
		node.put(key,value);
		String json = "";
		try {
			json =  objectMapper.writeValueAsString(node);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;		
	}
	
	public static JsonNode getJsonNodeFromString(String jsonString) {
		JsonNode json = null;
		try {
			json = objectMapper.readTree(jsonString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	public static int getIdFromJson(String jsonString) {
		int id = -1;
		try {
			JsonNode idJson = objectMapper.readTree(jsonString);
			JsonNode value = idJson.findValue("id");
			id = value.asInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public static ISensor getSensorFromJson(String jsonString) {
		
		ISensor sensor = null;
		try {
			JsonNode sensorJson = objectMapper.readTree(jsonString);
			sensor = new Sensor();
			sensor.setId(sensorJson.findValue("id").asInt());
			sensor.setDuration(sensorJson.findValue("duration").asLong());
			sensor.setInitialValue(sensorJson.findValue("initialValue").asDouble());
			sensor.setInterval(sensorJson.findValue("interval").asLong());
			sensor.setMax(sensorJson.findValue("max").asDouble());
			sensor.setMaxInterval(sensorJson.findValue("maxInterval").asLong());
			sensor.setMin(sensorJson.findValue("min").asDouble());
			sensor.setMinInterval(sensorJson.findValue("minInterval").asLong());
			sensor.setName(sensorJson.findValue("name").asText());
			sensor.setRandomInterval(sensorJson.findValue("randomInterval").asBoolean());
			sensor.setSinInterval(sensorJson.findValue("sinInterval").asInt());
			sensor.setType(PayloadType.valueOf(sensorJson.findValue("name").asText()));
		} catch (Exception e) {
			e.printStackTrace();
			sensor = null;
		}
		return sensor;
	}
	
	public static ArrayList<Integer> getListIdsFromJson(String jsonString) {
		
		ArrayList<Integer> array = new ArrayList<Integer>();
		try {
			JsonNode ids = objectMapper.readTree(jsonString).get("id");
			int i = 0;
			for(JsonNode node: ids) {
				array.add(Integer.valueOf(node.asInt()));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array;
	}
}