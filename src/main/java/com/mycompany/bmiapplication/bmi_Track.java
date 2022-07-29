/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bmiapplication;

import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class bmi_Track {
    
    private int userid;
    private String dateCreated;
    private double height, weight;

    public bmi_Track(int userid, String dateCreated, double height, double weight) {
        this.userid = userid;
        this.dateCreated = dateCreated;
        this.height = height;
        this.weight = weight;
    }

    public int getUserid() {
        return userid;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    
}
