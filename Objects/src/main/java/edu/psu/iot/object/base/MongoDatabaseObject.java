package edu.psu.iot.object.base;

import com.google.gson.Gson;

public abstract class MongoDatabaseObject {

	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	public abstract String getId();
}