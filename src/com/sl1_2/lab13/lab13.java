package com.sl1_2.lab13;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: May 2, 2019
*/

import javafx.application.Application;
import javafx.beans.value.*;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.*;

import javax.imageio.ImageIO;
import java.io.*;

public class lab13 extends Application {
    private static final MenuBar menu = new MenuBar();
    private static final GridPane grid = new GridPane();
    private static final VBox vbox = new VBox(menu, grid);

    private static final int[] endBar = {1, 0, 1};
    private static final int[] midBar = {0, 1, 0, 1, 0};
    private static final int[] upcOrder = {3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1};

    private static final int[][] leftCodes = {
            {0, 0, 0, 1, 1, 0, 1}, // 0
            {0, 0, 1, 1, 0, 0, 1}, // 1
            {0, 0, 1, 0, 0, 1, 1}, // 2
            {0, 1, 1, 1, 1, 0, 1}, // 3
            {0, 1, 0, 0, 0, 1, 1}, // 4
            {0, 1, 1, 0, 0, 0, 1}, // 5
            {0, 1, 0, 1, 1, 1, 1}, // 6
            {0, 1, 1, 1, 0, 1, 1}, // 7
            {0, 1, 1, 0, 1, 1, 1}, // 8
            {0, 0, 0, 1, 0, 1, 1}  // 9
    };

    private static final int[][] rightCodes = {
            {1, 1, 1, 0, 0, 1, 0}, // 0
            {1, 1, 0, 0, 1, 1, 0}, // 1
            {1, 1, 0, 1, 1, 0, 0}, // 2
            {1, 0, 0, 0, 0, 1, 0}, // 3
            {1, 0, 1, 1, 1, 0, 0}, // 4
            {1, 0, 0, 1, 1, 1, 0}, // 5
            {1, 0, 1, 0, 0, 0, 0}, // 6
            {1, 0, 0, 0, 1, 0, 0}, // 7
            {1, 0, 0, 1, 0, 0, 0}, // 8
            {1, 1, 1, 0, 1, 0, 0}  // 9
    };

    public static void main(String[] args) {
        Application.launch(args);
    }

    private static int drawBars(Group root, int[] bar, int xPos, int height, Color fill) {
        for (int i = 0; i < bar.length; i++) {
            Rectangle rect = new Rectangle(xPos, 25, 3, height);

            if (bar[i] == 0) {
                rect.setFill(Color.WHITE);
            } else {
                rect.setFill(fill);
            }

            xPos += 3;
            root.getChildren().add(rect);
        }

        return xPos;
    }

    private static void drawUpc(int[] digits, Group root, Color fill) {
        int xPos = 10;

        for (int i = 0; i < digits.length; i++) {
            if (i == 0) {
                xPos = drawBars(root, endBar, xPos, 200, fill);
            } else if (i == 6) {
                xPos = drawBars(root, midBar, xPos, 200, fill);
            }

            if (i <= 5) {
                xPos = drawBars(root, leftCodes[digits[i]], xPos, 175, fill);
            } else {
                xPos = drawBars(root, rightCodes[digits[i]], xPos, 175, fill);
            }

            if (i == 11) {
                xPos = drawBars(root, endBar, xPos, 200, fill);
            }
        }
    }

    private static void displayUpc(int[] digits) {
        Stage upcStage = new Stage();
        Group root = new Group();
        VBox vbox = new VBox(root);
        ChoiceBox<String> cbColor = new ChoiceBox<String>(
                FXCollections.observableArrayList("Black", "Blue", "Red", "Green", "Purple"));
        Button saveImage =  new Button("Save Barcode");

        cbColor.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (!newValue.equals(oldValue)) {
                    Color newColor;
                    root.getChildren().clear();

                    if (newValue.equals(0)) {
                        newColor = Color.BLACK;
                    } else if (newValue.equals(1)) {
                        newColor = Color.BLUE;
                    } else if (newValue.equals(2)) {
                        newColor = Color.RED;
                    } else if (newValue.equals(3)) {
                        newColor = Color.GREEN;
                    } else {
                        newColor = Color.PURPLE;
                    }

                    drawUpc(digits, root, newColor);
                }
            }
        });

        saveImage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    WritableImage image = root.snapshot(new SnapshotParameters(), null);
                    FileChooser fileChooser = new FileChooser();
                    File imageFile = fileChooser.showSaveDialog(upcStage);
                    ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", imageFile);
                } catch (IOException e) {

                }
            }
        });

        drawUpc(digits, root, Color.BLACK);
        cbColor.getSelectionModel().selectFirst();
        vbox.setStyle("-fx-padding: 10;");
        vbox.setSpacing(10);
        vbox.getChildren().add(cbColor);
        vbox.getChildren().add(saveImage);
        upcStage.setScene(new Scene(vbox, 305, 300));
        upcStage.setTitle("UPC Code");
        upcStage.show();
    }

    private static void upcScene(boolean isFull) {
        Label errInp = new Label("UPC code could not be parsed. Check length and digits.");
        TextField input = new TextField();

        input.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    String upcCode = input.getText();

                    if (upcCode.length() == 11 || (isFull && upcCode.length() == 12)) {
                        try {
                            if (grid.getChildren().contains(errInp)) {
                                grid.getChildren().remove(errInp);
                            }
                            // Used to confirm that the inputted UPC code only consists of numeric characters
                            long confirmValid = Long.parseLong(upcCode);
                            int sum = 0;
                            int[] digits = new int[12];

                            for (int i = 0; i < upcCode.length(); i++) {
                                sum += upcOrder[i] * Character.getNumericValue(upcCode.charAt(i));
                                digits[i] = Character.getNumericValue(upcCode.charAt(i));
                            }

                            if (isFull) {
                                if (sum % 10 != 0) {
                                    throw new ArithmeticException("Invalid UPC code!");
                                }
                            } else {
                                digits[11] = 10 - sum % 10;
                            }

                            displayUpc(digits);
                        } catch (Exception e) {
                            if (!grid.getChildren().contains(errInp)) {
                                grid.add(errInp, 1, 0);
                            }
                        }
                    } else {
                        if (!grid.getChildren().contains(errInp)) {
                            grid.add(errInp, 1, 0);
                        }
                    }
                }
            }
        });

        errInp.setStyle("-fx-text-fill: red");
        grid.add(new Label(isFull ? "Enter Full UPC Code:" : "Enter 11-digit UPC Code:"), 0, 0);
        grid.add(input, 0, 1);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(vbox, 540, 270);
        Menu file = new Menu("Menu");
        MenuItem upc11Digit = new MenuItem("Enter 11-digit UPC Code");
        MenuItem upcFull = new MenuItem("Enter Full UPC Code");

        upc11Digit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                grid.getChildren().clear();
                upcScene(false);
            }
        });

        upcFull.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                grid.getChildren().clear();
                upcScene(true);
            }
        });

        file.getItems().addAll(upc11Digit, upcFull);
        menu.getMenus().add(file);
        vbox.setStyle("-fx-padding: 10;");
        grid.setStyle("-fx-padding: 10;");
        grid.setHgap(15);
        grid.setVgap(15);
        primaryStage.setTitle("Kevin Qiu | Lab 13: Barcodes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
