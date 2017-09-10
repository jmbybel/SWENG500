package edu.psu.iot.object;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class SensorTest {

	private Sensor objectUnderTest = new Sensor();

	@Before
	public void setUp() {
		populateObject();
	}

	//verify set and get inputFields work as intended
	@Test
	public void test_setAndGetInputFields() {
		populateObject();
		Map<String, String> sampleInputData = new HashMap<>();
		sampleInputData.put("new field", "new value");
		objectUnderTest.setInputFields(sampleInputData);
		assertEquals(objectUnderTest.getInputFields(), sampleInputData);
	}

	//verify set and get DataPushMinimumMilliseconds works as intended
	@Test
	public void test_setAndGetDataPushMinimumMilliseconds() {
		objectUnderTest.setDataPushMinimumMilliseconds(400L);
		long test = new Long(400L);
		long objTest = new Long(objectUnderTest.getDataPushMinimumMilliseconds());
		assertEquals(objTest, test);
	}
	
	//verify set and get DataPushMaximumMilliseconds works as intended
	@Test
	public void test_setAndGetDataPushMaximumMilliseconds() {
		objectUnderTest.setDataPushMaximumMilliseconds(2000L);
		long test = new Long(2000L);
		long objTest = new Long(objectUnderTest.getDataPushMaximumMilliseconds());
		assertEquals(objTest, test);
	}
	
	private void populateObject() {
		objectUnderTest.setId(123L);
		objectUnderTest.setDataPushMaximumMilliseconds(1000L);
		objectUnderTest.setDataPushMinimumMilliseconds(1001L);
		objectUnderTest.setInputFields(new HashMap<>());
	}

	//TODO more work going to be needed for a full test!
	@Test
	public void jsonObjectFlattenAndExpand() {
		String jsonObject = objectUnderTest.toJson();
		Gson gson =new Gson();
		Sensor deviceFromJson = gson.fromJson(jsonObject, Sensor.class);
		assertEquals(deviceFromJson.getId(), objectUnderTest.getId());
	}
	
}
