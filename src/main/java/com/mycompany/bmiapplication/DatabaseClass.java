/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bmiapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author user
 */
public class DatabaseClass {

    Connection con;
    ResultSet rs;
    PreparedStatement ps;
    
    public DatabaseClass() 
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BMICalDB", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
}
