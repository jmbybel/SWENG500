package edu.psu.iot.generator.interfaces;

import edu.psu.iot.generator.sensor.SensorType;

public interface ISensorConfig {
	
	public String getName();

	public void setName(String name);

	public int getId();

	public void setId(int id);

	public double getInitialValue();

	public void setInitialValue(double initialValue);

	public double getMax();

	public void setMax(double max);

	public double getMin();

	public void setMin(double min);

	public long getDuration();

	public void setDuration(long duration);

	public long getInterval();

	public void setInterval(long interval);

	public SensorType getType();

	public void setType(SensorType type);

	public int getSinInterval();

	public void setSinInterval(int sinInterval);

	public long getMinInterval();

	public void setMinInterval(long minInterval);

	public long getMaxInterval();

	public void setMaxInterval(long maxInterval);

	public boolean isRandomInterval();

	public void setRandomInterval(boolean randomInterval);
}
