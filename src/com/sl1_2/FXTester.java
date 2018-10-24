package com.sl1_2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXTester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button btn1 = new Button();
        btn1.setText("Hello World!");
        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        Button btn2 = new Button();
        btn2.setText("Not Hello World!");
        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Not Hello World!");
            }
        });

        Text text1 = new Text("POGGERS");
        Text text2 = new Text("WAW");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(text1, 0, 0);
        grid.add(text2, 0, 1);
        grid.add(btn1, 1, 0);
        grid.add(btn2, 1, 1);

        Scene scene = new Scene(grid, 500, 400);
        primaryStage.setTitle("FX Tester");
        primaryStage.setScene(scene);
        primaryStage.show();
   }
}
