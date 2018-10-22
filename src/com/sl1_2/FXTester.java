package com.sl1_2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FXTester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 500, 400);
        Button btn1 = new Button();
        Button btn2 = new Button();


        btn1.setText("Hello World!");
        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        btn2.setText("Not Hello World!");
//        btn2.setAlignment(250, 30);
        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Not Hello World!");
            }
        });

        root.getChildren().add(btn1);
        root.getChildren().add(btn2);

        primaryStage.setTitle("FX Tester");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
