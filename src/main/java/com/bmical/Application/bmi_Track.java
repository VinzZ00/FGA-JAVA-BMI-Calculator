/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bmical.Application;

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
    private double bmiScale;

    public void setBmiScale(double bmiScale) {
        this.bmiScale = bmiScale;
    }

    
    public double getBmiScale() {
        return bmiScale;
    }

    public bmi_Track() {}

    public bmi_Track(int userid, String dateCreated, double height, double weight) {
        this.userid = userid;
        this.dateCreated = dateCreated;
        this.height = height;
        this.weight = weight;
        this.bmiScale = BMICalculator(height, weight);
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
    
    public double BMICalculator(double tall, double weight) {
        double tallSquare = Math.pow((tall/100), 2);
        return weight/tallSquare;
    }
    
    
}
