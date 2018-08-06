package com.goks.aws.S3;

import java.util.Date;
import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.goks.aws.util.Utility;

/**
 * @author Gokul
 *
 */
public class S3Read {
    
    
    /**
     * This method invokes the S3 read of bucket info
     * @param s3Client The S3Client to connect to AWS S3
     */
    public static void getS3Data(AmazonS3 s3Client) {
	       
        long grandTotal = 0;
        double TotalCost = 0;
        
        /*for(Region region:RegionUtils.getRegions()) {
        	System.out.println(region.getName());
    	}*/
        
        for(Bucket bucket: s3Client.listBuckets()) {
             
            ListObjectsV2Result result = s3Client.listObjectsV2(bucket.getName());
            List<S3ObjectSummary> objects = result.getObjectSummaries();
            
            Date oldDate = null;
            long bucketSize = 0;
            for(int i=0; i<objects.size(); i++) {
        	bucketSize =  bucketSize + objects.get(i).getSize();
        	Date date = objects.get(i).getLastModified();
        	if(oldDate==null || date.after(oldDate)) {
        	    oldDate = date;
        	}
        	
            }
            grandTotal = grandTotal + bucketSize;
            String bucketLocation = s3Client.getBucketLocation(bucket.getName());
            double cost = Utility.getCost(bucketSize,bucketLocation);
            TotalCost = TotalCost + cost;
            
            System.out.println( "*BUCKET NAME: " + bucket.getName());
            System.out.println( "\tBucket Location: " + bucketLocation);
            System.out.println("\tCreationDate: "+bucket.getCreationDate());
            System.out.println("\tLast Modified: "+oldDate);
            System.out.println("\tTotal Number of files: "+objects.size());
            System.out.println("\tBucket Size: " + Utility.getSize(bucketSize));
            System.out.println("\tEstimated Cost($): "+ cost);
        }
        
        System.out.println("Total Consumed Size: "+ Utility.getSize(grandTotal));
        System.out.println("Total Estimated Cost($): "+ TotalCost);
    }

}
