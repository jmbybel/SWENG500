package edu.psu.iot.database.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import edu.psu.iot.database.IDatabase;
import edu.psu.iot.generator.interfaces.ISensor;


public class Database implements IDatabase {
	
	MongoClient mongoClient;
	String databaseName = "iot";
	MongoDatabase database = mongoClient.getDatabase(databaseName);
	
	public Database() {
		this.mongoClient = new MongoClient();
	}
	
	public Database(String host, int port) {
		this.mongoClient = new MongoClient(host, port);
	}
	
	public Database(String inputConnectionString) {
		 MongoClientURI connectionString = new MongoClientURI(inputConnectionString);
		 mongoClient = new MongoClient(connectionString);
	}

	@Override
	public String getAllSensors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSensor(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createSensor(ISensor sensor) {
		MongoCollection<Document> collection = database.getCollection("sensors");
		
		
		collection.insertOne(arg0);
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
	public String batchQuery(String batchQuery) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	
		
}