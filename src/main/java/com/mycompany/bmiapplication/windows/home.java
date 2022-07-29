/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bmiapplication.windows;

import com.mycompany.bmiapplication.DatabaseClass;
import com.mycompany.bmiapplication.bmi_Track;
import java.lang.reflect.Field;
import java.sql.ResultSet;import java.sql.SQLException;
import java.util.Vector;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.StageStyle;
import javafx.scene.text.Text;

/**
 *
 * @author user
 */
public class home {
    
    static Label headerLabel, heightLabel, weightLabel;
    static TextField heightField, weightField;
    static Button submit;
    static ScrollPane sp;
    static HBox footer;
    
    
    public static void render(Stage primaryStage, String id) {
//        createtable();
        
        VBox root = new VBox();
        root.setSpacing(10);
        
        headerLabel = new Label("Your Stat");
        headerLabel.setStyle("-fx-font: normal bold 100px 'serif';");
        headerLabel.setAlignment(Pos.TOP_CENTER);
        root.setMargin(headerLabel, new Insets(0,10,10,10));
        root.setAlignment(Pos.CENTER);
        
        heightLabel = new Label("Height");
        weightLabel = new Label("Weight");
        
        heightField = new TextField();
        weightField = new TextField();
        
        submit = new Button("ADD");
        
        HBox inputForm = new HBox();
        inputForm.setSpacing(20);
        
        inputForm.getChildren().addAll(heightLabel, heightField, weightLabel, weightField, submit);
        
        root.getChildren().add(headerLabel);
        root.setMargin(headerLabel, new Insets(10,10,10,10));
        
        root.getChildren().add(populatetable(id, home.createTable()));
        root.setMargin(root.getChildren().get(1), new Insets(10,10,10,10));
        
        root.getChildren().add(inputForm);
        root.setMargin(inputForm, new Insets(10,10,10,10));
        
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
        
        ResultSet dataTable = new DatabaseClass().getTrackData(id);
        
        Vector<bmi_Track> BMI_Datas = new Vector<bmi_Track>();
//        BMI_Datas.add(new bmi_Track(1, "2021-12-05", 3.0D, 3.0D));
//          System.out.println(id);
        
        try {
                while (dataTable.next()) {
                    System.out.println("sini masuk");
                    BMI_Datas.add(new bmi_Track(dataTable.getInt(1), dataTable.getString(2), dataTable.getDouble(3), dataTable.getDouble(4)));
                }
                System.out.println(BMI_Datas.get(0).getDateCreated());
        } catch (SQLException ex) {
            System.out.println("Erorr di populate vector untuk mengisi table");
        }
        
        if (!BMI_Datas.isEmpty()) {
                for (bmi_Track bmi : BMI_Datas) {
                    tableView.getItems().add(bmi);
                }
        } 
        
        
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
                TableColumn<bmi_Track, String> column = new TableColumn<>("BMI Scale");
                column.setCellValueFactory(new PropertyValueFactory<>("BMI"));
                
                tableView.getColumns().add(column);
                
            tableView.getColumns().forEach( c -> {
                ((TableColumn<bmi_Track, String>) c ).prefWidthProperty().bind(tableView.widthProperty().multiply(1/(double)6));
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
   