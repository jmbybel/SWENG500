package edu.psu.iot.object.intf;

import com.google.gson.Gson;

public class JsonObject {

	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
