package com.psu.group1.generator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class PayloadGeneratorTest extends TestCase{
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	//if need to test output content (TODO:timing not working atm for JUnit)
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	//also can be used to test output, not working atm either
	@Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

	public void testCreateSensorService()
	{
		SensorService ss = new SensorService();
		ss.createSensor("Test", 1, 0, 1, 0, 0, 1000, SensorType.BINARY, 0, 1000, 5000, true);
		assertEquals((ss.sensorList).size(), 1);
		//assertEquals("{\"name\":\"Test\",\"id\":1,\"value\":0}", outContent.toString());
	}
	
	public void testStopSensorService()
	{
		SensorService ss = new SensorService();
		ss.createSensor("Test", 1, 0, 1, 0, 0, 1000, SensorType.BINARY, 0, 1000, 5000, true);
		ss.stopSensor(1);
		assertEquals((ss.sensorList.get(1)).enable, false);
	}
	
	public void testStartSensorService()
	{
		SensorService ss = new SensorService();
		ss.createSensor("Test", 1, 0, 1, 0, 0, 1000, SensorType.BINARY, 0, 1000, 5000, true);
		ss.stopSensor(1);
		ss.startSensor(1);
		assertEquals((ss.sensorList.get(1)).enable, true);
	}
	
	public void testDeleteSensorService()
	{
		SensorService ss = new SensorService();
		ss.createSensor("Test", 1, 0, 1, 0, 0, 1000, SensorType.BINARY, 0, 1000, 5000, true);
		ss.deleteSensor(1);
		assertEquals((ss.sensorList).size(), 0);
	}
	
	public void testSensorStartAndRun()
	{
		Sensor sensor = new Sensor("Test", 1, 0, 1, 0, 0, 1000, SensorType.BINARY, 0, 1000, 5000, true);
		sensor.start();
		assertEquals(sensor.enable, true);
		assertTrue(sensor.interval != 1000);
		//assertEquals("{\"name\":\"Test\",\"id\":1,\"value\":0}", systemOutRule.getLog());
	}
	
	public void testSensorStartAndStop()
	{
		Sensor sensor = new Sensor("Test", 1, 0, 1, 0, 0, 1000, SensorType.BINARY, 0, 1000, 5000, true);
		sensor.start();
		assertEquals(sensor.enable, true);
		sensor.stop();
		assertEquals(sensor.enable, false);
	}
	
	public void testSensorConstructor()
	{
		Sensor sensor = new Sensor("Test", 1, 0, 1, 0, 0, 1000, SensorType.BINARY, 0, 1000, 5000, true);
		assertEquals(sensor.name, "Test");
		assertEquals(sensor.id, 1);
		assertEquals(sensor.currentValue, 0.0);
		assertEquals(sensor.max, 1.0);
		assertEquals(sensor.min, 0.0);
		assertEquals(sensor.duration, 0);
		assertEquals(sensor.interval, 1000);
		assertEquals(sensor.type, SensorType.BINARY);
		assertEquals(sensor.minInterval, 1000);
		assertEquals(sensor.maxInterval, 5000);
		assertEquals(sensor.randomInterval, true);
		assertEquals(sensor.enable, true);
	}
	
	public void testGetRandomLong()
	{
		Long test = Util.getRandomLong(10,20);
		assertTrue(test <= 20);
		assertTrue(test >= 10);
	}
	
	public void testDifferentSensorsOutputToMockEndpointFile()
	{
		SensorService ss = new SensorService();
		ss.createSensor("Random Sensor", 1, 72, 90, 60, 0, 1000, SensorType.RANDOM, 0, 1000, 5000, true);
		ss.createSensor("Binary Sensor", 2, 1, 2, 1, 0, 1000, SensorType.BINARY, 0, 1000, 5000, false);
		ss.createSensor("Ramp Sensor", 3, 5, 10, 1, 0, 1000, SensorType.RAMP, 0, 1000, 5000, true);
		ss.createSensor("Sin Sensor", 4, 0, 0, 0, 0, 1000, SensorType.SIN, 10, 1000, 5000, true);
		File file = new File("mockoutput.txt");

		try {
		    @SuppressWarnings("resource")
			Scanner scanner = new Scanner(file);

		    int lineNum = 0;
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        lineNum++;
		        if(lineNum < 5) { 
		            if(line.contains("{\"name\":\"Binary Sensor\",\"id\":2,\"value\":1}")) {
		            	assertTrue(true);
		            }
		            else{
		            	if(line.contains("{\"name\":\"Random Sensor\",\"id\":1,\"value\":72}")){
		            		assertTrue(true);
		            	}
		            	else{
		            		if(line.contains("{\"name\":\"Ramp Sensor\",\"id\":3,\"value\":5}")){
		            			assertTrue(true);
		            		}
		            		else{
		            			if(line.contains("{\"name\":\"Sin Sensor\",\"id\":4,\"value\":0}")){
		            				assertTrue(true);
		            			}
		            			else{
		            				assertTrue(false);
		            			}
		            		}
		            	}
		            }
		            		
		        }
		        if(lineNum == 5) { 
		            break;
		        }
		    }
		} catch(FileNotFoundException e) { 
		    System.err.println("Output file not found");
		}
	}
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
}
