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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author user
 */
public class DatabaseClass {

    Connection con;
    ResultSet rs;
    PreparedStatement ps;
    
    String today = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            .format(LocalDateTime.now());
    
    
    public DatabaseClass() 
    {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:bmicaldatabase.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    public HashMap getUserData(String userEmail) {
        HashMap<String, String> loginCres = null;
        try {
            loginCres = new HashMap<String, String>();
            
            ps = con.prepareStatement("Select userId, userEmail, userPassword from users where userEmail = ?");
            ps.setString(1, userEmail);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                loginCres.put("userId", String.valueOf(rs.getString(1)));
                loginCres.put("userEmail", String.valueOf(rs.getObject(2)));
                loginCres.put("userPassword", String.valueOf(rs.getObject(3)));
                
                System.out.println(String.valueOf(rs.getString(3)));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return loginCres;
    }
    
    public ResultSet getTrackData(String id) {
        try {
            ps = con.prepareStatement("Select * from bmi_track where userId = ?");
            ps.setString(1, id);
            
            rs = ps.executeQuery();
        } catch (SQLException sQLException) {
            System.out.println("INI dari GETDATATRACK");
        }
        return rs;
    }
    
    public void registerUserData(String userName, String email, String password) {
        try {
            ps = con.prepareStatement("insert into users (username, useremail, userpassword) values (?,?,?);");
            ps.setString(1, userName);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.execute();
            
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION, "your User has been registered", ButtonType.OK);
            alert.setHeight(100);
            alert.setWidth(200);
            alert.show();
            
        } catch (SQLException ex) {
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR, "your registration has failed", ButtonType.OK);
            alert.setHeight(100);
            alert.setWidth(200);
            alert.show();
        }
    }
    
    public void addTrack(String id, double height, double weight) {
        try {
            ps = con.prepareStatement("insert into bmi_track (userId, dateCreated, height, weight) values (?, ?, ?, ?);");
            ps.setString(1, id);
            ps.setString(2, today);
            ps.setDouble(3, height);
            ps.setDouble(4, height);
        } catch (Exception e) {
            System.out.println("Di add track pass mau nguerry ");
        }
    }
    
    
}
