package edu.psu.iot.generator.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import edu.psu.iot.generator.interfaces.*;
import edu.psu.iot.generator.interfaces.ISensor;
import edu.psu.iot.generator.sensor.*;
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
	SensorService ss =new SensorService();
	@Before
	public void setUp() {
		ss.initialize();
	}
	
	public void testCreateSensorService()
	{
		Sensor config = new Sensor();
		config.setName("Test Create SS");
		config.setId(1);
		config.setRandomInterval(true);
		ss.createSensor(config);
		assertEquals((ss.getSensorList()).size(), 1);
		//assertEquals("{\"name\":\"Test\",\"id\":1,\"value\":0}", outContent.toString());
	}
	
	public void testStopSensorService()
	{
		Sensor config = new Sensor();
		config.setId(1);
		config.setName("Test Stop SS");
		config.setRandomInterval(true);
		ss.createSensor(config);
		ss.stopSensor(1);
		assertEquals(ss.getSensorList().get(1).isEnable(), false);
	}
	
	public void testStartSensorService()
	{
		Sensor config = new Sensor();
		config.setName("Test Start SS");
		config.setId(1);
		//config.setRandomInterval(true);
		ss.createSensor(config);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ss.stopSensor(1);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ss.startSensor(1);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals((ss.getSensorList().get(1)).isEnable(), true);
	}
	
	public void testDeleteSensorService()
	{
		Sensor config = new Sensor();
		config.setName("Test Delete SS");
		config.setId(1);
		config.setRandomInterval(true);
		ss.createSensor(config);
		ss.deleteSensor(1);
		assertEquals((ss.getSensorList()).size(), 0);
	}
	
	public void testSensorStartAndRun()
	{
		try{
			Sensor config = new Sensor();
			config.setId(1);
			config.setName("Test Sensor Start and Run");
			config.setRandomInterval(true);
			Payload sensor = PayloadFactory.createSensor(config);
			sensor.start();
			assertEquals(sensor.isEnable(), true);
			assertTrue(sensor.getInterval() != 1000);
			//assertEquals("{\"name\":\"Test\",\"id\":1,\"value\":0}", systemOutRule.getLog());
		} catch (PayloadTypeInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testSensorStartAndStop()
	{
		try {
			Sensor config = new Sensor();
			config.setId(1);
			config.setName("Test Sensor Start and Stop");
			config.setInterval(1000);
			Payload sensor;
		
			sensor = PayloadFactory.createSensor(config);
			sensor.start();
			assertEquals(sensor.isEnable(), true);
			System.out.println("Something...");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Something...");
			sensor.stop();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			assertEquals(sensor.isEnable(), false);
		} catch (PayloadTypeInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testSensorConstructor()
	{
		Sensor config = new Sensor();
		Payload sensor;
		try {
			sensor = PayloadFactory.createSensor(config);
			assertEquals(sensor.getName(), "name");
			assertEquals(sensor.getId(), -1);
			assertEquals(sensor.getCurrentValue(), 0.0);
			assertEquals(sensor.getMax(), 100.0);
			assertEquals(sensor.getMin(), 0.0);
			assertEquals(sensor.getDuration(), 0);
			assertEquals(sensor.getInterval(), 1000);
			assertEquals(sensor.getType(), PayloadType.RANDOM);
			assertEquals(sensor.getSinInterval(), 10);
			assertEquals(sensor.getMinInterval(), 1000);
			assertEquals(sensor.getMaxInterval(), 5000);
			assertEquals(sensor.isRandomInterval(), false);
			assertEquals(sensor.isEnable(), true);
		} catch (PayloadTypeInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testGetRandomLong()
	{
		Long test = Util.getRandomLong(10,20);
		assertTrue(test <= 20);
		assertTrue(test >= 10);
	}
	
	public void testSensorDurations() throws InterruptedException
	{
		
		File file = new File("logFile.txt");
		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			writer.print("");
			writer.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		Sensor config = new Sensor();
		config.setName("Test Duration");
		config.setId(1);
		config.setType(PayloadType.BINARY);
		config.setDuration(5000);
		ss.createSensor(config);
		
		TimeUnit.SECONDS.sleep(5);
		
		int zeroCount = 0;
		int oneHundredCount = 0;
		
		try {
		    @SuppressWarnings("resource")
			Scanner scanner = new Scanner(file);
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        System.out.println("Looping through file.");
	            if(line.contains("{\"name\":\"Test Duration\",\"id\":1,\"value\":0")) {
	            	zeroCount++;
	            }
	            
	            if(line.contains("{\"name\":\"Test Duration\",\"id\":1,\"value\":100")){
	            	oneHundredCount++;
	            }
		    }
		} catch(FileNotFoundException e) { 
		    System.err.println("Output file not found");
		}
		System.out.println(zeroCount);
		System.out.println(oneHundredCount);
		assertTrue(zeroCount == 3 && oneHundredCount == 2);
	}
	
	//tests log file works and multiple sensor types work as well
	public void testDifferentSensorsOutputToLogFile()
	{
		
		File file = new File("logFile.txt");
		 
		try {
			PrintWriter writer = new PrintWriter(file);
			writer.print("");
			writer.close();
			System.out.println("I cleared the file");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		//Create a Random Sensor config
		Sensor randomConfig = new Sensor();
		randomConfig.setName("Random Sensor");
		randomConfig.setId(1);
		randomConfig.setInitialValue(72);
		randomConfig.setMax(90);
		randomConfig.setMin(60);
		randomConfig.setType(PayloadType.RANDOM);
		randomConfig.setRandomInterval(true);
		ss.createSensor(randomConfig);
		
		//Create a Binary Sensor config
		Sensor binaryConfig = new Sensor();
		binaryConfig.setName("Binary Sensor");
		binaryConfig.setId(2);
		binaryConfig.setInitialValue(1);
		binaryConfig.setMax(2);
		binaryConfig.setMin(1);
		binaryConfig.setType(PayloadType.BINARY);
		ss.createSensor(binaryConfig);
		
		//Create a Ramp Sensor config
		Sensor rampConfig = new Sensor();
		rampConfig.setName("Ramp Sensor");
		rampConfig.setId(3);
		rampConfig.setInitialValue(5);
		rampConfig.setMax(10);
		rampConfig.setMin(1);
		rampConfig.setType(PayloadType.RAMP);
		rampConfig.setRandomInterval(true);
		ss.createSensor(rampConfig);
		
		//Create a Sin Sensor config
		Sensor sinConfig = new Sensor();
		sinConfig.setName("Sin Sensor");
		sinConfig.setId(4);
		sinConfig.setInitialValue(0);
		sinConfig.setMax(0);
		sinConfig.setMin(0);
		sinConfig.setType(PayloadType.SIN);
		sinConfig.setRandomInterval(true);
		ss.createSensor(sinConfig);
		
		boolean BinFlag = false;
		boolean RanFlag = false;
		boolean RamFlag = false;
		boolean SinFlag = false;
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			//File file1 = new File("logFile.txt");
		    @SuppressWarnings("resource")
			Scanner scanner = new Scanner(file);
		    
		    int lines = 0;
		    
		    System.out.println("Why am I not entering this while loop?");
		    while (scanner.hasNextLine()) {
		    	
		    	lines++;
		    	System.out.println(lines);
		    	
		        String line = scanner.nextLine();
		   
	            if(line.contains("{\"name\":\"Binary Sensor\",\"id\":2,\"value\":1")) {
	            	BinFlag = true;
	            	System.out.println("BinFlag true");
	            }
	            
	            if(line.contains("{\"name\":\"Random Sensor\",\"id\":1,\"value\":72")){
	            	RanFlag = true;
	            	System.out.println("RanFlag true");
	            }

        		if(line.contains("{\"name\":\"Ramp Sensor\",\"id\":3,\"value\":5")){
        			RamFlag = true;
        			System.out.println("RamFlag true");
        		}
        		
    			if(line.contains("{\"name\":\"Sin Sensor\",\"id\":4,\"value\":0")){
    				SinFlag = true;
    				System.out.println("SinFlag true");
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
	
	public void testQueue(){
		
		try {
			Sensor config = new Sensor();
			config.setInterval(1000);
			config.setType(PayloadType.RANDOM);
			config.setDuration(5000);
			config.setId(10);
			config.setName("Queue Test");
			
			
			ss.createSensor(config);
			
			Thread.sleep(5000);
			
			assertTrue(!(ss.getSensorList().isEmpty()));
			assertTrue(ss.getQueue().size() == 5);
			
			while(!ss.getQueue().isEmpty()){
				System.out.println(ss.getQueue().poll().toString());
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public void testSensorServiceInitialize(){
		
		try {
			Sensor config = new Sensor();
			config.setInterval(1000);
			config.setType(PayloadType.RANDOM);
			config.setDuration(5000);
			config.setId(10);
			config.setName("Initialize Test");
			
			
			
			assertTrue(ss.getSensorList().isEmpty());
			assertTrue(ss.getQueue().isEmpty());
			
			ss.createSensor(config);
			
			Thread.sleep(5000);
			
			assertTrue(!(ss.getSensorList().isEmpty()));
			System.out.println("Queue Size is" + ss.getQueue().size());
			assertTrue(!ss.getQueue().isEmpty());
			
			ss.initialize();
			
			assertTrue(ss.getSensorList().isEmpty());
			assertTrue(ss.getQueue().isEmpty());	
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testSensorServiceStop() {
		
		try {
			
			assertTrue(ss.getQueue().isEmpty());
			assertTrue(ss.getSensorList().isEmpty());
			Sensor sensor = new Sensor();
			ss.createSensor(sensor);
			
			Thread.sleep(5000);
			
			assertTrue(!ss.getQueue().isEmpty());
			assertTrue(!ss.getSensorList().isEmpty());
			
			System.out.println("The Queue size is: " + ss.getQueue().size());
			
			ss.initialize();
			
			System.out.println("The Queue size is: " + ss.getQueue().size());
			
			assertTrue(ss.getQueue().isEmpty());
			assertTrue(ss.getSensorList().isEmpty());
			sensor = new Sensor();
			ss.createSensor(sensor);
			
			Thread.sleep(5000);
			
			assertTrue(!ss.getQueue().isEmpty());
			assertTrue(!ss.getSensorList().isEmpty());
			
			System.out.println("The Queue size is: " + ss.getQueue().size());
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
