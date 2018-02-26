/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverManagerConnectionPool {

    private static List<Connection> freeDbConnections;

    static {
        freeDbConnections = new LinkedList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found!");
        }
    }

    private static Connection createDBConnection() throws SQLException {
        Connection newConnection = null;
        String db = "biblioteca";
        //CAMBIATELO A SECONDA DELLE VOSTRE IMPOSTAZIONI PERÒ DOPO 
        //ESCLUDETELO DAL COMMIT
        String username = "root";
        String password = "root";

        newConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, username, password);
        newConnection.setAutoCommit(false);
        return newConnection;
    }

    public static synchronized Connection getConnection() throws SQLException {
        Connection connection;
        if (!freeDbConnections.isEmpty()) {
            connection = (Connection) freeDbConnections.get(0);
            DriverManagerConnectionPool.freeDbConnections.remove(0);
            try {
                if (connection.isClosed()) {
                    connection = DriverManagerConnectionPool.getConnection();
                }
            } catch (SQLException e) {
                connection = DriverManagerConnectionPool.getConnection();
            }       
        } else {
            connection = DriverManagerConnectionPool.createDBConnection();           
        }
        
        
        return connection;
    }

    public static synchronized void releaseConnection(Connection connection) {
        DriverManagerConnectionPool.freeDbConnections.add(connection);        
    }

}
