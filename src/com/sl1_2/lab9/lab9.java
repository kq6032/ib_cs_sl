package com.sl1_2.lab9;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Mar. 1, 2019
*/

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class lab9 extends Application {
    private boolean isOn = true;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 300, 300);

        primaryStage.setTitle("Not Hello World");

        Button javaBtn = new Button();
        javaBtn.setLayoutX(60);
        javaBtn.setLayoutY(60);
        javaBtn.setText("Java");
        javaBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Make Java Great Again!");
            }
        });
        root.getChildren().add(javaBtn);

        Button onOffBtn = new Button();
        onOffBtn.setLayoutX(60);
        onOffBtn.setLayoutY(120);
        onOffBtn.setText("ON");
        onOffBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                isOn = !isOn;

                if (isOn) {
                    onOffBtn.setText("ON");
                } else {
                    onOffBtn.setText("OFF");
                }
            }
        });
        root.getChildren().add(onOffBtn);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
