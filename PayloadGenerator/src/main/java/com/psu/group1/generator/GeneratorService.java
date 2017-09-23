package com.psu.group1.generator;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;





/**
 * Generator Service
 *
 */
public class GeneratorService 
{
	private static final Logger logger = LogManager.getLogger();
	
    public static void main(String[] args)
    {
		logger.debug(">>main()");	
    	
		Payload payload = new Payload();
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);
        ses.scheduleAtFixedRate(payload, 0, 5, TimeUnit.SECONDS);
        
        logger.debug("<<main()");
    }
}
