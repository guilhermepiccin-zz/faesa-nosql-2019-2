/*
 *  Author: Manu Mukerji <next2manu@gmail.com>
 * 
 */

package com.example.com.mr_cassandra;

import com.example.com.mr_cassandra.CassandraHelper;

public class CassandraTester {
    
    public static void main(String[] args) throws InterruptedException {
        
        System.out.println("starting things...");
        CassandraHelper client = new CassandraHelper();
        
        //Create the connection
        client.createConnection();
        
        System.out.println("starting writes");
        
        //Add test value
        client.addKey("test1234");
        
        //Close the connection
        client.closeConnection();
        
        System.out.println("Write Complete");
    }
}