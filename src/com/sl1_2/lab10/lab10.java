package com.sl1_2.lab10;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Mar. 7, 2019
*/

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class lab10 extends Application {
    private static int colorCounter = 0;
    private static int scaleCounter = 0;

    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * Set the colors of all shapes to a specified color
     * @param clr The color to set all the shapes to
     */
    public static void setColors(Color clr, Circle cl, Line ln, Rectangle fillRect, Rectangle empRect, Ellipse el, Text tx) {
        cl.setStroke(clr);
        ln.setStroke(clr);
        fillRect.setStroke(clr);
        fillRect.setFill(clr);
        empRect.setStroke(clr);
        el.setStroke(clr);
        el.setFill(clr);
        tx.setStroke(clr);
    }

    /**
     * Set the scale of all shapes to a specific amount
     * This is also used to set the initial position of all the shapes
     * @param scale The scale to set all the shapes to
     */
    public static void setScale(float scale, Circle cl, Line ln, Rectangle fillRect, Rectangle empRect, Ellipse el, Text tx) {
        cl.setCenterX(200f * scale);
        cl.setCenterY(200 * scale);
        cl.setRadius(100 * scale);

        ln.setStartX(200f * scale);
        ln.setStartY(200f * scale);
        ln.setEndX(20f * scale);
        ln.setEndY(20f * scale);

        fillRect.setX(100f * scale);
        fillRect.setY(310f * scale);
        fillRect.setHeight(20f * scale);
        fillRect.setWidth(250f * scale);

        empRect.setX(25f * scale);
        empRect.setY(200f * scale);
        empRect.setWidth(40f * scale);
        empRect.setHeight(150f * scale);

        el.setCenterX(45f * scale);
        el.setCenterY(40f * scale);
        el.setRadiusX(20f * scale);
        el.setRadiusY(15f * scale);

        tx.setFont(new Font(20 * scale));
        tx.setX(70f * scale);
        tx.setY(55f * scale);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 400, 400);

        primaryStage.setTitle("Kevin's Lab 10");

        // Initializing the shapes and adding them to the window
        Circle cl = new Circle();
        cl.setFill(Color.WHITE);
        root.getChildren().add(cl);

        Line ln = new Line();
        root.getChildren().add(ln);

        Rectangle fillRec = new Rectangle();
        root.getChildren().add(fillRec);

        Rectangle empRect = new Rectangle();
        empRect.setFill(Color.WHITE);
        root.getChildren().add(empRect);

        Ellipse el = new Ellipse();
        root.getChildren().add(el);

        Text tx = new Text("This is drawn on the canvas.");
        tx.setFont(new Font(20));
        root.getChildren().add(tx);

        // Setting initial positions, scale, and shape of all the shapes
        setScale(1f, cl, ln, fillRec, empRect, el, tx);
        setColors(Color.BLACK, cl, ln, fillRec, empRect, el, tx);

        // The button for changing the color
        Button colorBtn = new Button();
        colorBtn.setLayoutX(115);
        colorBtn.setLayoutY(350);
        colorBtn.setText("Change Color");
        colorBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // A counter is incremented to change the color of the shapes
                colorCounter++;

                if (colorCounter == 1) {
                    setColors(Color.RED, cl, ln, fillRec, empRect, el, tx);
                } else if (colorCounter == 2) {
                    setColors(Color.BLUE, cl, ln, fillRec, empRect, el, tx);
                } else if (colorCounter == 3) {
                    setColors(Color.GREEN, cl, ln, fillRec, empRect, el, tx);
                } else {
                    // Once the counter reaches 4, it resets back to 0 and changes to default color
                    colorCounter = 0;
                    setColors(Color.BLACK, cl, ln, fillRec, empRect, el, tx);
                }
            }
        });
        root.getChildren().add(colorBtn);

        // The button for changing the scale
        Button scaleBtn = new Button();
        scaleBtn.setLayoutX(215);
        scaleBtn.setLayoutY(350);
        scaleBtn.setText("Change Scale");
        scaleBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // A counter is incremented to change the scale of the shapes
                scaleCounter++;

                if (scaleCounter == 1) {
                    setScale(0.25f, cl, ln, fillRec, empRect, el, tx);
                } else if (scaleCounter == 2) {
                    setScale(0.5f, cl, ln, fillRec, empRect, el, tx);
                } else {
                    // Once the counter reaches 3, it resets back to 0 and changes to default shape
                    scaleCounter = 0;
                    setScale(1f, cl, ln, fillRec, empRect, el, tx);
                }
            }
        });
        root.getChildren().add(scaleBtn);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
