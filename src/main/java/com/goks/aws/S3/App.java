package com.goks.aws.S3;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * @author Gokul
 *
 */
public class App {    
    /**
     * Main method for Entry
     * @param args Runtime Args to be passed
     */
    public static void main(final String[] args ) {       
        System.out.println("Your Amazon S3 buckets are:");

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion("eu-central-1").enableForceGlobalBucketAccess()
                .withCredentials(new ProfileCredentialsProvider())
                .build();
        S3Read.getS3Data(s3Client);
    }
    
}