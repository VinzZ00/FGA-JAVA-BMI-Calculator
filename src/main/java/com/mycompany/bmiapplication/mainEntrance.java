/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bmiapplication;

import javafx.application.Application;
import static javafx.application.Application.launch;
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
public class mainEntrance extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Label headerLabel = new Label("Welcome To Login Page");
        headerLabel.setFont(new Font("Sans Serif", 25));
        Button loginBut = new Button("Login");
        
        GridPane form = new GridPane();
        form.setAlignment(Pos.CENTER);
        form.setHgap(40);
        form.setVgap(20);
        Label emailLabel = new Label("Email");
        TextField emailField = new TextField();
        
        Label passwordLabel = new Label("Password");
        PasswordField passwordField = new PasswordField();
        
        form.add(emailLabel, 0, 0);
        form.add(emailField, 1, 0);
        form.add(passwordLabel, 0, 1);
        form.add(passwordField, 1, 1);
        
        BorderPane.setAlignment(loginBut,Pos.BOTTOM_CENTER);
        BorderPane.setAlignment(form,Pos.CENTER);
        BorderPane.setAlignment(headerLabel,Pos.TOP_CENTER);
        
        BorderPane root = new BorderPane(form, headerLabel, null, loginBut, null);
        
        
        root.setMargin(loginBut, new Insets(10,10,10,10));
        root.setPadding(new Insets(10, 0, 0, 10));
        
        
        loginBut.setOnAction((e) -> {
            
        });
        
        
        Scene loginScene = new Scene(root);

        primaryStage.setWidth(400);
        primaryStage.setHeight(300);
        primaryStage.setTitle("Login Page");    
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
 
    
}
