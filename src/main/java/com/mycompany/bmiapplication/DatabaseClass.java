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
import java.sql.SQLException;
import java.util.HashMap;

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
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bmicaldatabase", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    public HashMap getUserData(String userEmail) {
        HashMap<String, String> loginCres = new HashMap<String, String>();
        
        
        loginCres.put("userEmail", "ElvinEmail");
        
        loginCres.put("userPassword", "ElvinPassword");
        
        
        return loginCres;
    }
    
    public ResultSet getTrackData(String id) {
        try {
            ps = con.prepareStatement("Select * from Track where userId = ?");
            ps.setString(0, id);
            
            rs = ps.executeQuery();
        } catch (SQLException sQLException) {
        }
        return rs;
    }
    
    public 
}
