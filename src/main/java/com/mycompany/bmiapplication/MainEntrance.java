package com.mycompany.bmiapplication;

import com.mycompany.bmiapplication.windows.Login;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainEntrance extends Application {

    static DatabaseClass db = new DatabaseClass();


    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) {
        Login.render(primaryStage, db);
    }
 
    
}
