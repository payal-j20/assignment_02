/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.assignment_02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author shiv
 */
public class db {
    public Connection init() throws ClassNotFoundException, SQLException{
        
        String dbDriver = "org.apache.derby.jdbc.ClientDriver";
        String dbURL = "jdbc:derby://localhost:1527/";
        String dbName = "g";
        String dbUsername = "g";
        String dbPassword = "g";
        
        Class.forName(dbDriver);
        Connection con = DriverManager.getConnection(dbURL + dbName,dbUsername,dbPassword);
               
        return con;
    }
}
