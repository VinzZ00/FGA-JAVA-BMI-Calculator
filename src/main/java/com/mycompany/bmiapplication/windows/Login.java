package com.mycompany.bmiapplication.windows;

import com.mycompany.bmiapplication.DatabaseClass;
import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Login {
    
    static DatabaseClass db;
    static Alert alert;
    public static void render(Stage primaryStage, DatabaseClass db) {
        Login.db = db;
        
        Label headerLabel = new Label("Welcome To Login Page");
        headerLabel.setFont(new Font("Sans Serif", 25));
        Button loginBut = new Button("Login");
        
        Hyperlink register = new Hyperlink("Register");

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

        HBox Footer = new HBox();
        Footer.setAlignment(Pos.CENTER);
        Footer.setSpacing(10);
        Footer.getChildren().add(loginBut);
        Footer.getChildren().add(register);
        
        
        BorderPane.setAlignment(Footer, Pos.BOTTOM_CENTER);
        BorderPane.setAlignment(form, Pos.CENTER);
        BorderPane.setAlignment(headerLabel, Pos.TOP_CENTER);
        
        
        BorderPane root = new BorderPane(form, headerLabel, null, Footer, null);


        root.setMargin(Footer, new Insets(10, 10, 10, 10));
        
        root.setPadding(new Insets(10, 0, 0, 10));
        
        register.setOnAction(e -> {
            new register(primaryStage, db);
        });
        
        loginBut.setOnAction((e) -> {
            if (emailField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR, "Please Fill out the form", ButtonType.OK);
                    alert.setWidth(200);
                    alert.setHeight(200);
                    alert.show();
            } else {
                HashMap<String, String> usercres = db.getUserData(emailField.getText());
                if (usercres.isEmpty()) {
                    alert = new Alert(Alert.AlertType.ERROR, "Your Email Hasn't been Registered yet", ButtonType.OK);
                    alert.setWidth(200);
                    alert.setHeight(200);
                    alert.show();
                } else {
                    if (usercres.get("userPassword").equals(passwordField.getText())) {
                        
                        home.render(primaryStage, usercres.get("userId"), db);
                    }
                }
            }
        });
        
        
        Scene loginScene = new Scene(root);
        primaryStage.setWidth(400);
        primaryStage.setHeight(300);
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
}
