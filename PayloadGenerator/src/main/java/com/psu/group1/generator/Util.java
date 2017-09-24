package com.psu.group1.generator;

public class Util {
	public static long getRandomLong(long min, long max) {
	    long leftLimit = min;
	    long rightLimit =max;
	    return (leftLimit + (long) (Math.random() * (rightLimit - leftLimit)));
	}

}
