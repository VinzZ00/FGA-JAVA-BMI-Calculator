/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bmiapplication.windows;

import com.mycompany.bmiapplication.DatabaseClass;
import com.mycompany.bmiapplication.bmi_Track;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import java.lang.reflect.Field;
import java.sql.ResultSet;import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Stack;
import java.util.Vector;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.sound.midi.Soundbank;

/**
 *
 * @author user
 */
public class home {
    
    
    
    public static void render(Stage primStage, String id) {
        ResultSet dataTable = new DatabaseClass().getTrackData(id);
        
        Vector<bmi_Track> BMI_Datas = new Vector<bmi_Track>();
//        BMI_Datas.add(new bmi_Track(1, "2021-12-05", 3.0D, 3.0D));
        try {
            while (dataTable.next()) {
                BMI_Datas.add(new bmi_Track(dataTable.getInt(1), dataTable.getString(2), dataTable.getDouble(3), dataTable.getDouble(4)));
            }
        } catch (SQLException ex) {
            System.out.println("Erorr di populate vector untuk mengisi table");
        }
        
        if (!BMI_Datas.isEmpty()) {
            
            TableView tableView = new TableView();
            Field[] bmiField = BMI_Datas.get(0).getClass().getDeclaredFields();
            
            
                for (Field field : bmiField) {
                    String value = field.getName();
                    TableColumn<bmi_Track, String> userIdColumn = new TableColumn<>(value);
                    userIdColumn.setCellValueFactory(new PropertyValueFactory<>(value));

                
                }
                for (bmi_Track bmi : BMI_Datas) {
                    tableView.getItems().add(bmi);
                }
        }
        
        
                
    }
}
