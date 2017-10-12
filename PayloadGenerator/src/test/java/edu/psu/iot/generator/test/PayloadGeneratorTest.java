package edu.psu.iot.generator.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.junit.Before;

import edu.psu.iot.generator.impl.Sensor;
import edu.psu.iot.generator.impl.SensorConfig;
import edu.psu.iot.generator.impl.SensorService;
import edu.psu.iot.generator.impl.SensorType;
import edu.psu.iot.generator.impl.Util;
import junit.framework.TestCase;
/*
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.SystemOutRule;
*/



public class PayloadGeneratorTest extends TestCase{
	
	/*
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
	*/
	
	public void testCreateSensorService()
	{
		SensorService ss = new SensorService();
		SensorConfig config = new SensorConfig();
		config.setName("Test Create SS");
		config.setId(1);
		config.setRandomInterval(true);
		ss.createSensor(config);
		assertEquals((ss.sensorList).size(), 1);
		//assertEquals("{\"name\":\"Test\",\"id\":1,\"value\":0}", outContent.toString());
	}
	
	public void testStopSensorService()
	{
		SensorService ss = new SensorService();
		SensorConfig config = new SensorConfig();
		config.setId(1);
		config.setName("Test Stop SS");
		config.setRandomInterval(true);
		ss.createSensor(config);
		ss.stopSensor(1);
		assertEquals((ss.sensorList.get(1)).isEnable(), false);
	}
	
	public void testStartSensorService()
	{
		SensorService ss = new SensorService();
		SensorConfig config = new SensorConfig();
		config.setName("Test Start SS");
		config.setId(1);
		config.setRandomInterval(true);
		ss.createSensor(config);
		ss.stopSensor(1);
		ss.startSensor(1);
		assertEquals((ss.sensorList.get(1)).isEnable(), true);
	}
	
	public void testDeleteSensorService()
	{
		SensorService ss = new SensorService();
		SensorConfig config = new SensorConfig();
		config.setName("Test Delete SS");
		config.setId(1);
		config.setRandomInterval(true);
		ss.createSensor(config);
		ss.deleteSensor(1);
		assertEquals((ss.sensorList).size(), 0);
	}
	
	public void testSensorStartAndRun()
	{
		SensorConfig config = new SensorConfig();
		config.setId(1);
		config.setName("Test Sensor Start and Run");
		config.setRandomInterval(true);
		Sensor sensor = new Sensor(config);
		sensor.start();
		assertEquals(sensor.isEnable(), true);
		assertTrue(sensor.getInterval() != 1000);
		//assertEquals("{\"name\":\"Test\",\"id\":1,\"value\":0}", systemOutRule.getLog());
	}
	
	public void testSensorStartAndStop()
	{
		SensorConfig config = new SensorConfig();
		config.setId(1);
		config.setName("Test Sensor Start and Stop");
		config.setRandomInterval(true);
		Sensor sensor = new Sensor(config);
		sensor.start();
		assertEquals(sensor.isEnable(), true);
		sensor.stop();
		assertEquals(sensor.isEnable(), false);
	}
	
	public void testSensorConstructor()
	{
		SensorConfig config = new SensorConfig();
		Sensor sensor = new Sensor(config);
		assertEquals(sensor.getName(), "name");
		assertEquals(sensor.getId(), -1);
		assertEquals(sensor.getCurrentValue(), 0.0);
		assertEquals(sensor.getMax(), 100.0);
		assertEquals(sensor.getMin(), 0.0);
		assertEquals(sensor.getDuration(), 0);
		assertEquals(sensor.getInterval(), 1000);
		assertEquals(sensor.getType(), SensorType.RANDOM);
		assertEquals(sensor.getSinInterval(), 10);
		assertEquals(sensor.getMinInterval(), 1000);
		assertEquals(sensor.getMaxInterval(), 5000);
		assertEquals(sensor.isRandomInterval(), false);
		assertEquals(sensor.isEnable(), true);
	}
	
	public void testGetRandomLong()
	{
		Long test = Util.getRandomLong(10,20);
		assertTrue(test <= 20);
		assertTrue(test >= 10);
	}
	
	public void testSensorDurations() throws InterruptedException
	{
		SensorService ss = new SensorService();
		SensorConfig config = new SensorConfig();
		config.setName("Test Duration");
		config.setId(1);
		config.setType(SensorType.BINARY);
		config.setDuration(5000);
		ss.createSensor(config);
		
		TimeUnit.SECONDS.sleep(5);
		
		int zeroCount = 0;
		int oneHundredCount = 0;
		
		File file = new File("logFile.txt");
		
		try {
		    @SuppressWarnings("resource")
			Scanner scanner = new Scanner(file);
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		   
	            if(line.contains("{\"name\":\"Test Duration\",\"id\":1,\"value\":0}")) {
	            	zeroCount++;
	            }
	            
	            if(line.contains("{\"name\":\"Test Duration\",\"id\":1,\"value\":100}")){
	            	oneHundredCount++;
	            }
		    }
		} catch(FileNotFoundException e) { 
		    System.err.println("Output file not found");
		}
		
		if(zeroCount == 3 && oneHundredCount == 2)
		{
			assertTrue(true);
		}
		else
		{
			assertTrue(false);
		}
	
	}
	
	//tests log file works and multiple sensor types work as well
	public void testDifferentSensorsOutputToLogFile()
	{
		SensorService ss = new SensorService();
		
		//Create a Random Sensor config
		SensorConfig randomConfig = new SensorConfig();
		randomConfig.setName("Random Sensor");
		randomConfig.setId(1);
		randomConfig.setInitialValue(72);
		randomConfig.setMax(90);
		randomConfig.setMin(60);
		randomConfig.setType(SensorType.RANDOM);
		randomConfig.setRandomInterval(true);
		ss.createSensor(randomConfig);
		
		//Create a Binary Sensor config
		SensorConfig binaryConfig = new SensorConfig();
		binaryConfig.setName("Binary Sensor");
		binaryConfig.setId(2);
		binaryConfig.setInitialValue(1);
		binaryConfig.setMax(2);
		binaryConfig.setMin(1);
		binaryConfig.setType(SensorType.BINARY);
		ss.createSensor(binaryConfig);
		
		//Create a Ramp Sensor config
		SensorConfig rampConfig = new SensorConfig();
		rampConfig.setName("Ramp Sensor");
		rampConfig.setId(3);
		rampConfig.setInitialValue(5);
		rampConfig.setMax(10);
		rampConfig.setMin(1);
		rampConfig.setType(SensorType.RAMP);
		rampConfig.setRandomInterval(true);
		ss.createSensor(rampConfig);
		
		//Create a Sin Sensor config
		SensorConfig sinConfig = new SensorConfig();
		sinConfig.setName("Sin Sensor");
		sinConfig.setId(4);
		sinConfig.setInitialValue(0);
		sinConfig.setMax(0);
		sinConfig.setMin(0);
		sinConfig.setType(SensorType.SIN);
		sinConfig.setRandomInterval(true);
		ss.createSensor(sinConfig);
		
		
		File file = new File("logFile.txt");
		boolean BinFlag = false;
		boolean RanFlag = false;
		boolean RamFlag = false;
		boolean SinFlag = false;
		
		try {
		    @SuppressWarnings("resource")
			Scanner scanner = new Scanner(file);
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		   
	            if(line.contains("{\"name\":\"Binary Sensor\",\"id\":2,\"value\":1}")) {
	            	BinFlag = true;
	            }
	            
	            if(line.contains("{\"name\":\"Random Sensor\",\"id\":1,\"value\":72}")){
	            	RanFlag = true;
	            }

        		if(line.contains("{\"name\":\"Ramp Sensor\",\"id\":3,\"value\":5}")){
        			RamFlag = true;
        		}
        		
    			if(line.contains("{\"name\":\"Sin Sensor\",\"id\":4,\"value\":0}")){
    				SinFlag = true;
    			}
		    }
		} catch(FileNotFoundException e) { 
		    System.err.println("Output file not found");
		}
		
		if(BinFlag && SinFlag && RanFlag && RamFlag)
		{
			assertTrue(true);
		}
		else
		{
			assertTrue(false);
		}
	}
	
	/*
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
	*/
}
