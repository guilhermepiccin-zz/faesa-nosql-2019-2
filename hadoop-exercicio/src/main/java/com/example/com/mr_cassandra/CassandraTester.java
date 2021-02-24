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
        // client.addKey("abcd4r534to340i");
        // client.addKey("adofjkdogjiro3t");
        // client.addKey("ofkwopektq4o");
        // client.addKey("soeifdjaoeirgjoeri");

        client.addPalavraContador("asdokasd", 3);
        
        //Close the connection
        client.closeConnection();
        
        System.out.println("Write Complete");
    }
}