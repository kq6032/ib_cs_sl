package com.sl1_2.lab11;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Mar. 15, 2019
*/

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class lab11 extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Kevin's Lab 11");

        // Grid for aligning the text boxes and text
        GridPane grid = new GridPane();
        // Setting padding to make GUI more comfortable looking
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        Label fahLabel = new Label("Fahrenheit");
        Label celLabel = new Label("Celsius");
        Label kelLabel = new Label("Kelvin");

        TextField fahField = new TextField();
        TextField celField = new TextField();
        TextField kelField = new TextField();

        // Setting actions for "Fahrenheit" text field on "ENTER" key press
        fahField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    try {
                        float input = Float.parseFloat(fahField.getText());
                        fahField.setStyle("");
                        // Changing "Celsius" and "Kelvin" text fields into equivalent of value in "Fahrenheit" field.
                        celField.setText(Integer.toString(Math.round(input - 32f) * 5 / 9));
                        kelField.setText(Integer.toString(Math.round((input - 32f) * 5 / 9 + 273.15f)));
                    } catch (Exception e) {
                        fahField.setStyle("-fx-text-box-border: red; -fx-focus-color: red;");
                        celField.setText("");
                        kelField.setText("");
                    }
                }
            }
        });

        // Setting actions for "Celsius" text field on "ENTER" key press
        celField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    try {
                        float input = Float.parseFloat(celField.getText());
                        celField.setStyle("");
                        // Changing "Fahrenheit" and "Kelvin" text fields into equivalent of value in "Celsius" field.
                        fahField.setText(Integer.toString(Math.round(input * 9 / 5 + 32f)));
                        kelField.setText(Integer.toString(Math.round(input + 273.15f)));
                    } catch (Exception e) {
                        celField.setStyle("-fx-text-box-border: red; -fx-focus-color: red;");
                        fahField.setText("");
                        kelField.setText("");
                    }
                }
            }
        });

        // Setting actions for "Kelvin" text field on "ENTER" key press
        kelField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    try {
                        float input = Float.parseFloat(kelField.getText());
                        kelField.setStyle("");
                        // Changing "Fahrenheit" and "Celsius" text fields into equivalent of value in "Kelvin" field.
                        fahField.setText(Integer.toString(Math.round((input -273.15f) * 9 / 5 + 32f)));
                        celField.setText(Integer.toString(Math.round(input - 273.15f)));
                    } catch (Exception e) {
                        kelField.setStyle("-fx-text-box-border: red; -fx-focus-color: red;");
                        fahField.setText("");
                        celField.setText("");
                    }
                }
            }
        });

        // Putting the labels and text fields into the grid
        grid.add(fahLabel, 0, 0, 1, 1);
        grid.add(celLabel, 0, 1, 1, 1);
        grid.add(kelLabel, 0, 2, 1, 1);
        grid.add(fahField, 1, 0, 2, 1);
        grid.add(celField, 1, 1, 2, 1);
        grid.add(kelField, 1, 2, 2, 1);

        // Aligning the text of the labels to the right
        GridPane.setHalignment(fahLabel, HPos.RIGHT);
        GridPane.setHalignment(celLabel, HPos.RIGHT);
        GridPane.setHalignment(kelLabel, HPos.RIGHT);

        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
