package org.dos.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDB {
    private static Connection connection = null;

    public ConnectionDB() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        //para rodar no local
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dados", "root",
                "Mysql@usp2018");
    }

    public static Connection getConnection() throws SQLException {

        if (connection == null) {
            try {
                new ConnectionDB();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectionDB.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }

        return connection;
    }
}
