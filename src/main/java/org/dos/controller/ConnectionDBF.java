package org.dos.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Diogo on 15/07/2017.
 */
public class ConnectionDBF {
    private static Connection connection = null;
    private static Connection connection1 = null;
    
    public ConnectionDBF() throws SQLException, ClassNotFoundException {
        Class.forName("org.firebirdsql.jdbc.FBDriver");
        //para rodar no local
        connection = DriverManager.getConnection(
                "jdbc:firebirdsql:localhost/3050:C:/Users/Diogo/Desktop/psa/SIENGE.FDB",
                "sysdba",
                "masterkey");
        
        /*connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dados", "root",
                "mysql");*/
    }

    public static Connection getConnection() throws SQLException {

        if (connection == null) {
            try {
                new ConnectionDBF();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectionDBF.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }

        return connection;
    }
}
