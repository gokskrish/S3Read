package com.goks.aws.util;

import java.text.DecimalFormat;

/**
 * @author Gokul
 *
 */
public class Utility {
    final static DecimalFormat form = new DecimalFormat("0.0000");
    
    /**
     * This method taken in Bytes and converts 
     * them to KB/MB/GB as applicable.
     * @param sizeInBytes Initial size in Bytes
     * @return size in KB/MB/GB
     */
    public static String getSize(long sizeInBytes) {
	double bytes = sizeInBytes;
	String size = null;
	if(bytes < 1024) {
	    size = bytes+" B";
	} else if(bytes < (1024*1024)) {
	    double kb = bytes/1024;
	    size = form.format(kb) + " KB";
	} else if(bytes < (1024*1024*1024)) {
	    double mb = bytes/(1024*1024);
	    size = form.format(mb) + " MB";
	} else {
	    double gb = bytes/(1024*1024*1024);
	    size = form.format(gb) + " GB";
	}
	return size;
    }
    
    /**
     * This method takes in size and location and 
     * calculates the cost of S3 Storage based 
     * different on regions
     * @param sizeInBytes Initial Size in bytes
     * @param bucketLocation AWS Region
     * @return cost calculated
     */
    public static double getCost(double sizeInBytes, String bucketLocation) {
	double totalPrice = 0;
	double price = 0;
	double sizeInGB = sizeInBytes/(1024*1024*1024);
	
	if(sizeInBytes != 0 && sizeInGB<1) {
	    sizeInGB = 1;
	}
	
	if(bucketLocation.equalsIgnoreCase("ap-northeast-1") ||
		bucketLocation.equalsIgnoreCase("ap-northeast-2") || 
		bucketLocation.equalsIgnoreCase("ap-south-1") || 
		bucketLocation.equalsIgnoreCase("ap-southeast-1") ||
		bucketLocation.equalsIgnoreCase("ap-southeast-2") ||
		bucketLocation.equalsIgnoreCase("ca-central-1")) {
	    price = 0.025;
	} else if(bucketLocation.equalsIgnoreCase("eu-central-1")) {
	    price = 0.0245;
	} else if(bucketLocation.equalsIgnoreCase("eu-west-1") ||
		bucketLocation.equalsIgnoreCase("eu-west-2") ||
		bucketLocation.equalsIgnoreCase("eu-west-3")) {
	    price = 0.024;
	} else if(bucketLocation.equalsIgnoreCase("sa-east-1")) {
	    price = 0.0405;
	} else if(bucketLocation.equalsIgnoreCase("us-east-1") ||
		bucketLocation.equalsIgnoreCase("us-east-2") ||
		bucketLocation.equalsIgnoreCase("us-west-2")) {
	    price = 0.023;
	} else if(bucketLocation.equalsIgnoreCase("us-west-1")) {
	    price = 0.026;
	} else {
	    price = 0.023;
	}
	totalPrice = sizeInGB * price;
	
	return Double.parseDouble(form.format(totalPrice));
    }
}


