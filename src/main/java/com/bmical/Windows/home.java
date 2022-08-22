/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bmical.Windows;

import com.bmical.Application.DatabaseClass;
import com.bmical.Application.bmi_Track;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;


/**
 *
 * @author user
 */
public class home {
    
    static Label headerLabel, heightLabel, weightLabel, dateLabel;
    static TextField heightField, weightField, dateField;
    static Button submit, update, delete;
    static HBox footer;
    static HBox inputForm;
    
    static DatabaseClass db;
    
    public static void render(Stage primaryStage, String id, DatabaseClass db) {
        home.db = db;
        
        VBox root = new VBox();
        root.setSpacing(10);
        
        // Header
        headerLabel = new Label("Your Stat");
        headerLabel.setStyle("-fx-font: normal bold 100px 'serif';");
        headerLabel.setAlignment(Pos.TOP_CENTER);
        
        // input Form Footer
        
        dateLabel = new Label("Date"); 
        heightLabel = new Label("Height");
        weightLabel = new Label("Weight");
        
        dateField = new TextField(DateTimeFormatter
        		.ofPattern("yyyy-MM-dd")
        		.format(LocalDateTime.now()));
        dateField.setEditable(false);
        heightField = new TextField();
        weightField = new TextField();
        
        submit = new Button("ADD");
        update = new Button("UPDATE");
        update.setDisable(true);
        delete = new Button("REMOVE");
        delete.setDisable(true);
        
        inputForm = new HBox();
        inputForm.setSpacing(20);
        
        inputForm.getChildren().addAll(dateLabel, dateField, heightLabel, heightField, weightLabel, weightField, submit, update, delete);
        
        
        
        
//        insertNodes(root, id);
        root.getChildren().add(0, headerLabel);
        // Body
        root.getChildren().add(1, populatetable(id, home.createTable()));
        root.getChildren().add(2, inputForm);
        root.setMargin(headerLabel, new Insets(0,10,10,10));
        root.setAlignment(Pos.CENTER);
        root.setMargin(headerLabel, new Insets(10,10,10,10));
        root.setMargin(root.getChildren().get(1), new Insets(10,10,10,10));
        root.setMargin(inputForm, new Insets(10,10,10,10));
        
        
        
        submit.setOnAction((ActionEvent event) -> {
            if (weightField.getText().isEmpty() || heightField.getText().isEmpty()) { 
                new Alert(Alert.AlertType.ERROR, "Please Fill the height and weight field", ButtonType.OK);
            } else {
                    db.addTrack(id, Double.valueOf(heightField.getText()), Double.valueOf(weightField.getText()));
            }
           root.getChildren().remove(1);
           root.getChildren().add(1, populatetable(id, createTable()));
        });
        
        update.setOnAction((e) -> {
        	db.updateBMITrack(Double.valueOf(heightField.getText()), Double.valueOf(weightField.getText()), dateField.getText());
        	root.getChildren().remove(1);
            root.getChildren().add(1, populatetable(id, createTable()));
            submit.setDisable(false);
            update.setDisable(true);
            delete.setDisable(true);
        });
        
        delete.setOnAction((e) -> {
        	db.deleteBMITrack(dateField.getText());
        	root.getChildren().remove(1);
            root.getChildren().add(1, populatetable(id, createTable()));
            submit.setDisable(false);
            update.setDisable(true);
            delete.setDisable(true);
        });
        
//        long lastRefreshTime = 0;
        Scene homescene = new Scene(root);
        /*
        jangan di hapus ini mungkin berguna
        */
//        Rectangle2D screenSize = Screen.getPrimary()
//                .getVisualBounds(); 
        primaryStage.setMinHeight(800);
        primaryStage.setMinWidth(1600);
        primaryStage.setResizable(false);
        /*
        jangan dihapus juga ini mungkin berguna
        */
//        primaryStage.setX(screenSize.getMinX());
//        primaryStage.setY(screenSize.getMinY());
//        primaryStage.setWidth(screenSize.getWidth());
//        primaryStage.setHeight(screenSize.getHeight());
        primaryStage.setWidth(1600);
        primaryStage.setHeight(800);
        primaryStage.setTitle("Register Page");
        primaryStage.setScene(homescene);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
    
    public static TableView populatetable(String id, TableView tableView) { //Method buat create table;
        
        ResultSet dataTable = db.getTrackData(id);
        
        
        ArrayList<bmi_Track> BMI_Datas = new ArrayList<bmi_Track>();
//        BMI_Datas.add(new bmi_Track(1, "2021-12-05", 3.0D, 3.0D));
//          System.out.println(id);
        
        try {
                while (dataTable.next()) {
                    BMI_Datas.add(new bmi_Track(dataTable.getInt(1), dataTable.getString(2), dataTable.getDouble(3), dataTable.getDouble(4)));
                }
        } catch (SQLException ex) {
            System.out.println("Erorr di populate vector untuk mengisi table");
        }
        System.out.println("Banyak Rows : " + BMI_Datas.size());
//        if (!BMI_Datas.isEmpty()) {
//                for (bmi_Track bmi : BMI_Datas) {
                    tableView.setItems(FXCollections.observableArrayList(BMI_Datas));
//                }
//        } 
        
          tableView.setOnMouseClicked(e -> {
        	  bmi_Track selectedItem = (bmi_Track) tableView.getSelectionModel().getSelectedItem();
        	  dateField.setText(selectedItem.getDateCreated());
        	  weightField.setText(String.valueOf(selectedItem.getWeight()));
        	  heightField.setText(String.valueOf(selectedItem.getHeight()));
        	  update.setDisable(false);
        	  delete.setDisable(false);
        	  submit.setDisable(true);
          });
                    
        return tableView;
    } 
    
    public static TableView createTable() {
        TableView tableView;
        tableView = new TableView();
            Field[] bmiField = new bmi_Track().getClass().getDeclaredFields();            
                for (Field field : bmiField) {
                    String value = field.getName();
                    TableColumn<bmi_Track, String> column = new TableColumn<>(value);
                    column.setCellValueFactory(new PropertyValueFactory<>(value));
                    column.setPrefWidth(44);
                    tableView.getColumns().add(column);
                }
            tableView.getColumns().forEach( c -> {
                ((TableColumn<bmi_Track, String>) c ).prefWidthProperty().bind(tableView.widthProperty().multiply(1/(double)5));
            });
        return tableView;
    }
    
//    public static void autoResizeColumns( TableView<bmi_Track> table )
//{
//    //Set the right policy
//    table.setColumnResizePolicy( TableView.UNCONSTRAINED_RESIZE_POLICY);
//    table.getColumns().stream().forEach( (column) ->
//    {
//        //Minimal width = columnheader
//        Text t = new Text(column.getText());
//        double max = t.getLayoutBounds().getWidth();
////        System.out.println("ini di atas" + max);
//        for ( int i = 0; i < table.getItems().size(); i++ )
//        {
//            
//            //cell must not be empty
//            if ( column.getCellData( i ) != null )
//            {
//                t = new Text( column.getCellData( i ).toString() );
//                double calcwidth = t.getLayoutBounds().getWidth();
//                //remember new max-width
//                if ( calcwidth > max )
//                {
//                    max = calcwidth;
//                }
//            }
//            System.out.println("width di tambah " + max);
//        }
//        //set the new max-widht with some extra space
//        column.setpre( max + 10.0d );
//    } );
//}
}
   