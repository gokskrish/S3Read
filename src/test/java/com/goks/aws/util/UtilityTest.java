package com.goks.aws.util;


import junit.framework.TestCase;

public class UtilityTest extends TestCase {

    public void testGetSize1() {
	String expected = "1.0000 KB";
	String actual = Utility.getSize(1024);
	assertEquals(expected, actual);
    }
    
    public void testGetSize2() {
	String expected = "1.0000 MB";
	String actual = Utility.getSize(1024*1024);
	assertEquals(expected, actual);
    }
    
    public void testGetSize3() {
	String expected = "1.0000 GB";
	String actual = Utility.getSize(1024*1024*1024);
	assertEquals(expected, actual);
    }

    public void testGetCost1() {
	double expected = 0.024;
	double actual = Utility.getCost(100 , "eu-west-1");
	assertEquals(expected, actual);
    }
    
    public void testGetCost2() {
	double expected = 0.026;
	double actual = Utility.getCost(100 , "us-west-1");
	assertEquals(expected, actual);
    }
    public void testGetCost3() {
	double expected = 0.025;
	double actual = Utility.getCost(100 , "ap-northeast-2");
	assertEquals(expected, actual);
    }
}
