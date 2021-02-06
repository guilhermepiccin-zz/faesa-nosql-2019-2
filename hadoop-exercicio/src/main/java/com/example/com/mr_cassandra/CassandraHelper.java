/*
 *  Author: Manu Mukerji <next2manu@gmail.com>
 * 
 */

package com.example.com.mr_cassandra;

import org.slf4j.LoggerFactory;

import com.datastax.driver.core.*;
import com.datastax.driver.core.exceptions.NoHostAvailableException;
import com.datastax.driver.core.exceptions.QueryExecutionException;

public class CassandraHelper {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CassandraHelper.class);
    private Cluster cluster;
    private Session session;
    private String query = "INSERT INTO faesamod05keyspace.livro (chave) VALUES(?)"; 
    private PreparedStatement preparedStatement;

    public Session getSession()  {
         LOG.info("Starting getSession()");
        if (this.session == null && (this.cluster == null || this.cluster.isClosed())) {
            LOG.info("Cluster not started or closed");
        } else if (this.session.isClosed()) {
            LOG.info("session is closed. Creating a session");
            this.session = this.cluster.connect();
        }

        return this.session;
    }

    public void createConnection()  {

        this.cluster = Cluster.builder()
                .addContactPoint("faesamod05cassandra.cassandra.cosmos.azure.com")
                .withPort(10350)
                .withCredentials("faesamod05cassandra", "mDfZXB0PTPY1SJn9JbAd26sPcpOXlg8dlFcy1yMGwjNlJcsWIhDgxgAwUyFSt8fmqN87kysoG83S6fLVmfeHzA==")
                .withSSL()
                .build();     

        this.session = cluster.connect("faesamod05keyspace");
        
        this.prepareQueries();
    }

    public void closeConnection() {
        cluster.close();
    }

    private void prepareQueries()  {
        LOG.info("Starting prepareQueries()");
        this.preparedStatement = this.session.prepare(this.query);
    }

    public void addKey(String key) {
        Session session = this.getSession();
        
        if(key.length()>0) {
            try {
                session.execute(this.preparedStatement.bind(key) );
                //session.executeAsync(this.preparedStatement.bind(key));
            } catch (NoHostAvailableException e) {
                System.out.printf("No host in the %s cluster can be contacted to execute the query.\n", 
                        session.getCluster());
                Session.State st = session.getState();
                for ( Host host : st.getConnectedHosts() ) {
                    System.out.println("In flight queries::"+st.getInFlightQueries(host));
                    System.out.println("open connections::"+st.getOpenConnections(host));
                }

            } catch (QueryExecutionException e) {
                System.out.println("An exception was thrown by Cassandra because it cannot " +
                        "successfully execute the query with the specified consistency level.");
            }  catch (IllegalStateException e) {
                System.out.println("The BoundStatement is not ready.");
            }
        }
    }

}