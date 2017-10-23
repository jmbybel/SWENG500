package edu.psu.iot.generator.interfaces;

import edu.psu.iot.generator.sensor.PayloadType;

public interface ISensor {

	String getName();

	void setName(String name);

	int getId();

	void setId(int id);

	double getInitialValue();

	void setInitialValue(double initialValue);

	double getMax();

	void setMax(double max);

	double getMin();

	void setMin(double min);

	long getDuration();

	void setDuration(long duration);

	long getInterval();

	void setInterval(long interval);

	PayloadType getType();

	void setType(PayloadType type);

	int getSinInterval();

	void setSinInterval(int sinInterval);

	long getMinInterval();

	void setMinInterval(long minInterval);

	long getMaxInterval();

	void setMaxInterval(long maxInterval);

	String getUrlEndpoint();

	void setUrlEndpoint(String url);

	boolean isRandomInterval();

	void setRandomInterval(boolean randomInterval);

}