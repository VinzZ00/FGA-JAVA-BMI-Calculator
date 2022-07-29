/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bmiapplication.windows;

import com.mycompany.bmiapplication.DatabaseClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class register {

    static Label HeaderRegister, userName, userEmail, userPassword, userPasswordConfirm;
    static TextField userNameField, userEmailField;
    static PasswordField userPasswordField, userPasswordConfirmField;
    static Button registerBut;
    static Hyperlink loginHyperlink;
    static DatabaseClass db;
    static GridPane regform;
    static Alert alert;

    public register(Stage primaryStage, DatabaseClass db) {
        register(db, primaryStage);
    }
    
    
    
    public static void register(DatabaseClass db, Stage primaryStage) {
        register.db = db;
        //HEADER REGISTER
        HeaderRegister = new Label("Register");
        HeaderRegister.setFont(new Font("Sans Serif",25));
        
        
        //FORM REGISTER
        userName = new Label("User Name");
        userEmail = new Label("Email");
        userPassword = new Label("Password");
        userPasswordConfirm = new Label("Confirm Password");
        
        userNameField = new TextField();
        userEmailField = new TextField();
        userPasswordField = new PasswordField();
        userPasswordConfirmField = new PasswordField();
        
        
        regform = new GridPane();
        regform.setAlignment(Pos.CENTER);
        regform.setHgap(20);
        regform.setVgap(10);
        regform.add(userName, 0, 0);
        regform.add(userEmail, 0, 1);
        regform.add(userPassword, 0, 2);
        regform.add(userPasswordConfirm, 0, 3);
        
        regform.add(userNameField, 1, 0);
        regform.add(userEmailField, 1, 1);
        regform.add(userPasswordField, 1, 2);
        regform.add(userPasswordConfirmField, 1, 3);
        
        //FOOTER REGISTER
        registerBut = new Button("Register");
        registerBut.setOnAction(e -> {
            if (userNameField.getText().isEmpty() || userEmailField.getText().isEmpty() || userPasswordField.getText().isEmpty() || userPasswordConfirmField.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR, "Please fill out the form", ButtonType.OK);
                    alert.setHeight(100);
                    alert.setWidth(200);
                    alert.show();
            } else {
                if (userPasswordField.getText().equals(userPasswordConfirmField.getText())) {
                    db.registerUserData(userNameField.getText(), userEmailField.getText(), userPasswordField.getText());
                } else {
                    alert = new Alert(AlertType.ERROR, "The password dont match", ButtonType.OK);
                    alert.setHeight(100);
                    alert.setWidth(200);
                    alert.show();
                }
            }
        });
        loginHyperlink = new Hyperlink("Login");
        loginHyperlink.setOnAction(e -> {
            Login login = new Login();
            login.render(primaryStage, db);
        });
        
        VBox footer = new VBox();
        footer.setSpacing(10);
        footer.getChildren().addAll(registerBut, loginHyperlink);
        footer.setAlignment(Pos.CENTER);
        
        
        
        BorderPane root = new BorderPane(regform, HeaderRegister, null, footer, null);
        root.setAlignment(HeaderRegister, Pos.TOP_CENTER);
        root.setAlignment(regform, Pos.CENTER);
        root.setAlignment(footer , Pos.BOTTOM_CENTER);
        
        root.setMargin(footer, new Insets(30,0,10,0));
        root.setMargin(HeaderRegister, new Insets(10, 0, 10, 0));
        
        Scene loginScene = new Scene(root);

        primaryStage.centerOnScreen();
        primaryStage.setWidth(400);
        primaryStage.setHeight(400);
        primaryStage.setTitle("Register Page");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
    
}
