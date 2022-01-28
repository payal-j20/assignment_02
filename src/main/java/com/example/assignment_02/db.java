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
 * @author payal
 */
public class db {
    public Connection init() throws ClassNotFoundException, SQLException{
        
        String dbURL ="jdbc:postgresql://ec2-3-216-113-109.compute-1.amazonaws.com:5432/";
        String dbName = "dmt8qlgq6aj62";
        String dbUsername = "oopbehpbtxzziu";
        String dbPassword = "6ae6a26fd44588be28bf45a0f08a99ba2c4bdb6280d9c4bf0e9b88d995f4ab42";
        DriverManager.registerDriver(new org.postgresql.Driver());
        Connection con = DriverManager.getConnection(dbURL + dbName,dbUsername,dbPassword);
            
        return con;
    }
}
