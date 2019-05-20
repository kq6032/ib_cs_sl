package com.sl1_2.lab13;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: May 20, 2019
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.*;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.Arrays;

public class lab13 extends Application {
    // Objects for the JavaFX Window and formatting
    private static final MenuBar menu = new MenuBar();
    private static final GridPane grid = new GridPane();
    private static final VBox vbox = new VBox(menu, grid);

    /*
    Sequences of bars for the different digits and sides of UPC and ISBN codes.
    1 = black bar, 0 = white bar
     */
    private static final int[] endBar = {1, 0, 1};
    private static final int[] midBar = {0, 1, 0, 1, 0};

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

    private static final int[][] leftCodesEvenParity = {
            {0, 1, 0, 0, 1, 1, 1},
            {0, 1, 1, 0, 0, 1, 1},
            {0, 0, 1, 1, 0, 1, 1},
            {0, 1, 0, 0, 0, 0, 1},
            {0, 0, 1, 1, 1, 0, 1},
            {0, 1, 1, 1, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 1},
            {0, 0, 1, 0, 0, 0, 1},
            {0, 0, 0, 1, 0, 0, 1},
            {0, 0, 1, 0, 1, 1, 1}
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

    // Order the numbers in UPC code are summed together to find check digit
    private static final int[] upcOrder = {3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1};

    /**
     * Launching the JavaFX window
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * Draws bars for the code
     * @param root Where to draw the bars
     * @param bar Which set of bars to print
     * @param xPos X-position to start drawing the bars
     * @param height The desired height of the bars
     * @param fill The desired color of fill of the bars
     * @return The final x-position of the bars.
     */
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

    /**
     * Draws the text for the code
     * @param root Where to draw the text
     * @param digits The digits of the code to print out
     * @param isIsbn Whether the code is ISBN
     */
    private static void drawText(Group root, int[] digits, boolean isIsbn) {
        String codeDigits = Arrays.toString(digits).replaceAll("\\[|\\]|,|", "");
        Text code = new Text();

        // If ISBN, the first number/system digit is added to the string as it is not used in drawing the code
        if (isIsbn) {
            String isbn = "9 " + codeDigits;

            code.setText(isbn);
        } else {
            code.setText(codeDigits);
        }

        // If ISBN, start drawing the text at 50 as the code is longer, centering it.
        code.setX(isIsbn ? 50 : 58);
        code.setY(250);
        code.setFont(Font.font(20));
        root.getChildren().add(code);
    }

    /**
     * Draw the barcode as a whole
     * @param digits The digits of the code to draw
     * @param root Where to draw the code/barcode
     * @param fill The color of the fill of the barcode
     * @param isIsbn Whether the code is ISBN
     */
    private static void drawCode(int[] digits, Group root, Color fill, boolean isIsbn) {
        int xPos = 10;

        for (int i = 0; i < digits.length; i++) {
            // Drawing the guard bars for the front and the center
            if (i == 0) {
                xPos = drawBars(root, endBar, xPos, 200, fill);
            } else if (i == 6) {
                xPos = drawBars(root, midBar, xPos, 200, fill);
            }

            // Drawing the code bars
            if (i <= 5) {
                if (isIsbn) {
                    // As the system digit is '9', the second, third, and fifth digits of the left side
                    // of ISBN use the even parity code.
                    if (i == 1 || i == 2 || i == 4) {
                        xPos = drawBars(root, leftCodesEvenParity[digits[i]], xPos, 175, fill);
                    } else {
                        xPos = drawBars(root, leftCodes[digits[i]], xPos, 175, fill);
                    }
                } else {
                    xPos = drawBars(root, leftCodes[digits[i]], xPos, 175, fill);
                }
            } else {
                xPos = drawBars(root, rightCodes[digits[i]], xPos, 175, fill);
            }

            // Draw the guard bars for the back
            if (i == digits.length - 1) {
                xPos = drawBars(root, endBar, xPos, 200, fill);
            }
        }

        drawText(root, digits, isIsbn);
    }

    /**
     * Displaying the code in a new window
     * @param digits The digits of the code to display
     * @param isIsbn Whether the code is ISBN
     */
    private static void displayCode(int[] digits, boolean isIsbn) {
        Stage upcStage = new Stage();
        Group root = new Group();
        VBox vbox = new VBox(root);
        ChoiceBox<String> cbColor = new ChoiceBox<String>(
                FXCollections.observableArrayList("Black", "Blue", "Red", "Green", "Purple"));
        Button saveImage =  new Button("Save Barcode");

        // ChoiceBox for color
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

                    // Redrawing the code with the new color
                    drawCode(digits, root, newColor, isIsbn);
                }
            }
        });

        // Save image button
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

        // Draws the code in the new Stage
        drawCode(digits, root, Color.BLACK, isIsbn);
        cbColor.getSelectionModel().selectFirst();
        vbox.setStyle("-fx-padding: 10;");
        vbox.setSpacing(10);
        vbox.getChildren().add(cbColor);
        vbox.getChildren().add(saveImage);
        upcStage.setScene(new Scene(vbox, 305, 340));
        upcStage.setTitle("Barcode");
        upcStage.show();
    }

    /**
     * Scene for the UPC code
     * @param isFull Whether the desired code to be inputted should be 11 or 12 (full) digits
     */
    private static void upcScene(boolean isFull) {
        Label errInp = new Label("UPC code could not be parsed. Check length and digits.");
        TextField input = new TextField();

        // When the user presses 'enter' check the code and display it
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

                            // Summing together the digits of the code to check/calculate the code
                            for (int i = 0; i < upcCode.length(); i++) {
                                sum += upcOrder[i] * Character.getNumericValue(upcCode.charAt(i));
                                digits[i] = Character.getNumericValue(upcCode.charAt(i));
                            }

                            if (isFull) { // Checking if the inputted code is valid
                                if (sum % 10 != 0) {
                                    throw new ArithmeticException("Invalid UPC code!");
                                }
                            } else { // Calculating the check digit
                                digits[11] = 10 - sum % 10;

                                input.setText(upcCode + Integer.toString(digits[11]));
                            }

                            displayCode(digits, false);
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

    /**
     * Scene for the ISBN code
     * @param isFull Whether the desired code to be inputted should be 9 or 10 (full) digits
     */
    private static void isbnScene(boolean isFull) {
        Label errInp = new Label("10-Digit ISBN code could not be parsed. Check length and digits.");
        TextField input = new TextField();

        // When the user presses 'enter' check the code and display it
        input.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    String isbnCode = input.getText();

                    if (isbnCode.length() == 9 || (isFull && isbnCode.length() == 10)) {
                        try {
                            if (grid.getChildren().contains(errInp)) {
                                grid.getChildren().remove(errInp);
                            }

                            // Used to confirm that the inputted ISBN code only consists of numeric characters
                            long confirmValid = Long.parseLong(isbnCode);
                            int sum = 0;
                            int[] digits = new int[10];

                            // Finding sum of the digits inputted
                            for (int i = 0; i < isbnCode.length(); i++) {
                                sum += (10 - i) * Character.getNumericValue(isbnCode.charAt(i));
                                digits[i] = Character.getNumericValue(isbnCode.charAt(i));
                            }

                            if (isFull) { // Verifying if the entered full code is valid
                                if (sum % 11 != 0) {
                                    throw new ArithmeticException("Invalid ISBN code!");
                                }
                            } else { // Finding the check digit and completing the ISBN code
                                digits[9] = (11 - sum % 11) % 11;

                                input.setText(isbnCode + Integer.toString(digits[9]));
                            }

                            int[] actualDigits = new int[12];
                            // The digits '7' and '8' are appended and the check-digit is recalculated to get ISBN-13
                            actualDigits[0] = 7;
                            actualDigits[1] = 8;
                            sum = 9 + 3 * actualDigits[0] + actualDigits[1];

                            for (int i = 0; i < digits.length; i++) {
                                actualDigits[i + 2] = digits[i];

                                // If the digit is in an odd position, then the digit is multiplied by 3
                                if ((i + 2) < (actualDigits.length - 1)) {
                                    sum += (i % 2 == 0) ? (actualDigits[i + 2] * 3) : (actualDigits[i + 2]);
                                }
                            }

                            // New check digit
                            actualDigits[11] = (10 - sum % 10) % 10;

                            displayCode(actualDigits, true);
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
        grid.add(new Label(isFull ? "Enter Full 10-Digit ISBN Code:" : "Enter 9 of 10-Digit ISBN Code:"),
                0, 0);
        grid.add(input, 0, 1);
    }

    /**
     * The main body of the program to print ISBN and UPC codes
     * @param primaryStage The main window of the program
     */
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(vbox, 540, 270);
        Menu file = new Menu("Menu");
        MenuItem upc11Digit = new MenuItem("Enter 11-digit UPC Code");
        MenuItem upcFull = new MenuItem("Enter Full UPC Code");
        MenuItem isbn9Of10Digit = new MenuItem("Enter 9 of 10-digit ISBN Code");
        MenuItem isbn10DigitFull = new MenuItem("Enter Full 10-digit ISBN Code");

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

        isbn9Of10Digit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                grid.getChildren().clear();
                isbnScene(false);
            }
        });

        isbn10DigitFull.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                grid.getChildren().clear();
                isbnScene(true);
            }
        });

        file.getItems().addAll(upc11Digit, upcFull, isbn9Of10Digit, isbn10DigitFull);
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
