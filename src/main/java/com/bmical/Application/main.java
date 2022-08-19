/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bmical.Application;

import com.bmical.Windows.Login;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class main extends Application {
    
    static DatabaseClass db = new DatabaseClass();
    
    
    public static void main(String[] args) {
        launch(args);
    }

   
    public void start(Stage primaryStage) {
        Login.render(primaryStage, db);
    }
 
    
}
