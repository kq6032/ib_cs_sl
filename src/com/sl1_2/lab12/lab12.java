package com.sl1_2.lab12;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: May 2, 2019
*/

import javafx.application.Application;
import javafx.beans.value.*;
import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class lab12 extends Application {
    private static final MenuBar menu = new MenuBar();
    private static final GridPane grid = new GridPane();
    private static final VBox vbox = new VBox(menu, grid);
    private static final TableView table = new TableView();

    private static ArrayList<Student> students = new ArrayList<>();
    private static Student curStudent;
    private static String importFilePath;

    public static void main(String[] args) {
        Application.launch(args);
    }

    private static void updateValues() {
        table.getItems().removeAll();
        table.getItems().clear();
        table.getItems().addAll(students);
    }

    private static void showStudents() {
        ChoiceBox<String> cb = new ChoiceBox<String>(
                FXCollections.observableArrayList("ID", "First Name", "Last Name"));

        TableColumn idCol = new TableColumn("ID");
        idCol.setSortType(TableColumn.SortType.ASCENDING);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        table.getSortOrder().add(idCol);

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameCol.setSortType(TableColumn.SortType.ASCENDING);

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameCol.setSortType(TableColumn.SortType.ASCENDING);

        TableColumn genderCol = new TableColumn("Gender");
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn gradeCol = new TableColumn("Grade");
        gradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));

        table.getItems().addAll(students);

        table.getColumns().addAll(idCol, firstNameCol, lastNameCol, gradeCol, genderCol);
        table.getSortOrder().add(idCol);

        vbox.getChildren().addAll(table, new Label("Student List"), new Label("Sort By:"), cb);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (!newValue.equals(oldValue)) {
                    if (newValue.equals(0)) {
                        table.getColumns().clear();
                        table.getColumns().addAll(idCol, firstNameCol, lastNameCol, gradeCol, genderCol);
                        table.getSortOrder().add(idCol);
                    } else if (newValue.equals(1)) {
                        table.getColumns().clear();
                        table.getColumns().addAll(firstNameCol, lastNameCol, idCol, gradeCol, genderCol);
                        table.getSortOrder().add(firstNameCol);
                    } else {
                        table.getColumns().clear();
                        table.getColumns().addAll(lastNameCol, firstNameCol, idCol, gradeCol, genderCol);
                        table.getSortOrder().add(lastNameCol);
                    }
                }
            }
        });
    }

    private static void importExportStudents() {
        TextField importField = new TextField();
        TextField exportField = new TextField();
        Button updateImportedFile = new Button("Update imported file");
        Label errorImport = new Label("Could not import file.");
        Label errorExport = new Label("Could not export file.");
        Label successImport = new Label("Objects imported successfully");
        Label successExport = new Label("Objects exported successfully");

        errorImport.setStyle("-fx-text-fill: red;");
        errorExport.setStyle("-fx-text-fill: red;");
        successImport.setStyle("-fx-text-fill: green;");
        successExport.setStyle("-fx-text-fill: green;");

        grid.add(new Label("Import:"), 0, 0);
        grid.add(new Label("Location of students object file (absolute path):"), 0, 1);
        grid.add(importField, 1, 1);
        grid.add(new Label("Use format: /Path/to/my/students/file/FILENAME"), 0, 2);

        grid.add(new Label("Export:"), 0, 4);
        grid.add(new Label("Location to export student object file (absolute path):"), 0, 5);
        grid.add(exportField, 1, 5);
        grid.add(new Label("Use format: /Path/to/my/new/students/file/FILENAME"), 0, 6);

        grid.add(new Label("Update imported file:"), 0, 7);
        grid.add(updateImportedFile, 1, 7);

        importField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    try {
                        if (grid.getChildren().contains(errorImport)) {
                            grid.getChildren().remove(errorImport);
                        }

                        if (grid.getChildren().contains(successImport)) {
                            grid.getChildren().remove(successImport);
                        }

                        importFilePath = importField.getText();
                        FileInputStream fi = new FileInputStream(new File(importFilePath));
                        ObjectInputStream oi = new ObjectInputStream(fi);

                        Student std;

                        table.getItems().removeAll(students);
                        table.getItems().clear();
                        students.clear();

                        try {
                            while (true) {
                                std = (Student) oi.readObject();
                                students.add(std);
                            }
                        } catch (EOFException e) {
                            System.out.println("Reached end of object input file.");
                        }

                        oi.close();
                        fi.close();

                        table.getItems().addAll(students);

                        grid.add(successImport, 2, 1);
                    } catch (Exception e) {
                        if (!grid.getChildren().contains(errorImport)) {
                            grid.add(errorImport, 2, 1);
                        }

                        System.out.println("Error: " + e);
                    }
                }
            }
        });

        exportField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    try {
                        if (grid.getChildren().contains(errorExport)) {
                            grid.getChildren().remove(errorExport);
                        }

                        if (grid.getChildren().contains(successExport)) {
                            grid.getChildren().remove(successExport);
                        }

                        FileOutputStream f = new FileOutputStream(new File(exportField.getText()));
                        ObjectOutputStream o = new ObjectOutputStream(f);

                        // Writing the Students to the specified file
                        for (Student std : students) {
                            o.writeObject(std);
                        }

                        o.close();
                        f.close();

                        grid.add(successExport, 2, 5);
                    } catch (Exception e) {
                        if (!grid.getChildren().contains(errorExport)) {
                            grid.add(errorExport, 2, 5);
                        }

                        System.out.println("Error: " + e);
                    }
                }
            }
        });

        updateImportedFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (importFilePath != null) {
                    try {
                        FileOutputStream f = new FileOutputStream(importFilePath, false);
                        ObjectOutputStream o = new ObjectOutputStream(f);

                        // Writing the Students to the specified file
                        for (Student std : students) {
                            o.writeObject(std);
                        }

                        o.close();
                        f.close();

                        grid.add(successExport, 2, 7);
                    } catch (Exception e) {
                        if (!grid.getChildren().contains(errorExport)) {
                            grid.add(errorExport, 2, 7);
                        }

                        System.out.println("Error: " + e);
                    }
                    }
            }
        });
    }

    private static void showStats() {
        grid.add(new Label("Statistics:"), 0, 0);

        int[] grades = new int[4];
        int[] genders = new int[3];

        for (Student std : students) {
            if (std.getGrade() == 9) {
                grades[0]++;
            } else if (std.getGrade() == 10) {
                grades[1]++;
            } else if (std.getGrade() == 11) {
                grades[2]++;
            } else {
                grades[3]++;
            }

            if (std.getGender().equals("Male")) {
                genders[0]++;
            } else if (std.getGender().equals("Female")) {
                genders[1]++;
            } else {
                genders[2]++;
            }
        }

        grid.add(new Label("Gender Distributions:"), 0, 2);
        grid.add(new Label("Male:"), 0, 3);
        grid.add(new Label(Integer.toString(genders[0])), 1, 3);
        grid.add(new Label("Female:"), 0, 4);
        grid.add(new Label(Integer.toString(genders[1])), 1, 4);
        grid.add(new Label("Other:"), 0, 5);
        grid.add(new Label(Integer.toString(genders[2])), 1, 5);

        grid.add(new Label("Grade Distributions:"), 0, 7);
        grid.add(new Label("Freshmen:"), 0, 8);
        grid.add(new Label(Integer.toString(grades[0])), 1, 8);
        grid.add(new Label("Sophomores:"), 0, 9);
        grid.add(new Label(Integer.toString(grades[1])), 1, 9);
        grid.add(new Label("Juniors:"), 0, 10);
        grid.add(new Label(Integer.toString(grades[2])), 1, 10);
        grid.add(new Label("Seniors:"), 0, 11);
        grid.add(new Label(Integer.toString(grades[3])), 1, 11);
    }

    private static void addDeleteEditStudents() {
        TextField id = new TextField();
        TextField firstName = new TextField();
        TextField lastName = new TextField();
        TextField gender = new TextField();
        ChoiceBox<String> cb = new ChoiceBox<String>(
                FXCollections.observableArrayList("9", "10", "11", "12"));

        TextField searchId = new TextField();
        TextField searchLastName = new TextField();

        Label studentLabel = new Label();
        TextField updateId = new TextField();
        TextField updateFirstName = new TextField();
        TextField updateLastName = new TextField();
        TextField updateGender = new TextField();
        ChoiceBox<String> updateCb = new ChoiceBox<String>(
                FXCollections.observableArrayList("9", "10", "11", "12"));

        Label errorLabel = new Label("Invalid input parameters.");

        cb.getSelectionModel().selectFirst();
        errorLabel.setStyle("-fx-text-fill: red;");

        Button addStudent = new Button("Add Student");
        addStudent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    int newId = Integer.parseInt(id.getText());
                    int newGrade = Integer.parseInt(cb.getValue().toString());
                    String newFirstName = firstName.getText();
                    String newLastName = lastName.getText();
                    String newGender = gender.getText();

                    students.add(new Student(newId, newGrade, newLastName, newFirstName, newGender));
                    updateValues();

                    id.clear();
                    firstName.clear();
                    lastName.clear();
                    gender.clear();
                    cb.getSelectionModel().select(0);

                    if (grid.getChildren().contains(errorLabel)) {
                        grid.getChildren().remove(errorLabel);
                    }
                } catch (Exception e) {
                    System.out.println(e);

                    if (!grid.getChildren().contains(errorLabel)) {
                        grid.add(errorLabel, 1, 1);
                    }
                }
            }
        });

        Button searchStudent = new Button("Search Student List");
        searchStudent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (searchId.getText().equals("") && !searchLastName.getText().equals("")) {
                        String searchStringVal = searchLastName.getText();

                        for (Student std : students) {
                            if (std.getLastName().equals(searchStringVal)) {
                                curStudent = std;
                            }
                        }
                    } else if (!searchId.getText().equals("") && searchLastName.getText().equals("")) {
                        int searchVal = Integer.parseInt(searchId.getText());

                        for (Student std : students) {
                            if (std.getId() == searchVal) {
                                curStudent = std;
                            }
                        }
                    } else {
                        int searchIntVal = Integer.parseInt(searchId.getText());
                        String searchStringVal = searchLastName.getText();

                        for (Student std : students) {
                            if (std.getId() == searchIntVal && std.getLastName().equals(searchStringVal)) {
                                curStudent = std;
                            }
                        }
                    }

                    studentLabel.setText(curStudent.getLastName() + ", " + curStudent.getFirstName() + " "
                                         + curStudent.getId());

                    updateId.setText(Integer.toString(curStudent.getId()));
                    updateFirstName.setText(curStudent.getFirstName());
                    updateLastName.setText(curStudent.getLastName());
                    updateGender.setText(curStudent.getGender());
                    updateCb.getSelectionModel().select(Integer.toString(curStudent.getGrade()));

                    if (!grid.getChildren().contains(studentLabel)) {
                        grid.add(studentLabel, 1, 13);
                    }

                    if (grid.getChildren().contains(errorLabel)) {
                        grid.getChildren().remove(errorLabel);
                    }
                } catch (Exception e) {
                    System.out.println(e);

                    if (!grid.getChildren().contains(errorLabel)) {
                        grid.add(errorLabel, 5, 1);
                    }
                }
            }
        });

        Button clearCurStudent = new Button("Clear Current Student");
        clearCurStudent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (grid.getChildren().contains(studentLabel)) {
                    grid.getChildren().remove(studentLabel);
                    curStudent = null;
                }
            }
        });

        Button deleteStudent = new Button("Delete Student");
        deleteStudent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (curStudent != null) {
                    students.remove(curStudent);
                    updateValues();

                    updateId.clear();
                    updateFirstName.clear();
                    updateLastName.clear();
                    updateGender.clear();
                    updateCb.getSelectionModel().select(0);

                    if (grid.getChildren().contains(studentLabel)) {
                        grid.getChildren().remove(studentLabel);
                        curStudent = null;
                    }
                }
            }
        });

        Button updateStudent = new Button("Update Student");
        updateStudent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (curStudent != null) {
                    try {
                        int newId = Integer.parseInt(updateId.getText());
                        int newGrade = Integer.parseInt(updateCb.getValue().toString());
                        String newFirstName = updateFirstName.getText();
                        String newLastName = updateLastName.getText();
                        String newGender = updateGender.getText();

                        curStudent.setId(newId);
                        curStudent.setGrade(newGrade);
                        curStudent.setFirstName(newFirstName);
                        curStudent.setLastName(newLastName);
                        curStudent.setGender(newGender);
                        updateValues();

                        if (grid.getChildren().contains(errorLabel)) {
                            grid.getChildren().remove(errorLabel);
                        }
                    } catch (Exception e) {
                        System.out.println(e);

                        if (!grid.getChildren().contains(errorLabel)) {
                            grid.add(errorLabel, 5, 1);
                        }
                    }
                }
            }
        });

        grid.add(new Label("Add/Delete/Edit Students"), 0, 0);
        grid.add(new Label("Add Students:"), 0, 1);
        grid.add(new Label("ID:"), 0, 2);
        grid.add(id, 1, 2);
        grid.add(new Label("First Name:"), 0, 3);
        grid.add(firstName, 1, 3);
        grid.add(new Label("Last Name:"), 0, 4);
        grid.add(lastName, 1, 4);
        grid.add(new Label("Gender:"), 0, 5);
        grid.add(gender, 1, 5);
        grid.add(new Label("Grade:"), 0, 6);
        grid.add(cb, 1, 6);
        grid.add(addStudent, 0, 7);

        grid.add(new Label("Search Students (Delete/Edit):"), 4, 1);
        grid.add(new Label("ID:"), 4, 2);
        grid.add(searchId, 5, 2);
        grid.add(new Label("Last Name:"), 4, 3);
        grid.add(searchLastName, 5, 3);
        grid.add(searchStudent, 4, 4);
        grid.add(clearCurStudent, 5, 4);

        grid.add(new Label("Current Student:"), 4, 6);
        grid.add(deleteStudent, 4, 7);
        grid.add(new Label("ID:"), 5, 7);
        grid.add(updateId, 6, 7);
        grid.add(updateStudent, 4, 8);
        grid.add(new Label("First Name:"), 5, 8);
        grid.add(updateFirstName, 6, 8);
        grid.add(new Label("Last Name:"), 5, 9);
        grid.add(updateLastName, 6, 9);
        grid.add(new Label("Gender:"), 5, 10);
        grid.add(updateGender, 6, 10);
        grid.add(new Label("Grade:"), 5, 11);
        grid.add(updateCb, 6, 11);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Kevin's Lab 12 | Student Lab");

        Menu mainMenu = new Menu("Menu");

        MenuItem home = new MenuItem("Home");
        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                grid.getChildren().removeAll();
                grid.getChildren().clear();
            }
        });

        MenuItem addDeleteEditStudents = new MenuItem("Add/Delete/Edit Students");
        addDeleteEditStudents.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                grid.getChildren().removeAll();
                grid.getChildren().clear();
                addDeleteEditStudents();
            }
        });

        MenuItem dataOverview = new MenuItem("Data Overview");
        dataOverview.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                grid.getChildren().removeAll();
                grid.getChildren().clear();
                showStats();
            }
        });

        MenuItem impExpObj = new MenuItem("Import/Export Students");
        impExpObj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                grid.getChildren().removeAll();
                grid.getChildren().clear();
                importExportStudents();
            }
        });

        mainMenu.getItems().addAll(home, addDeleteEditStudents, dataOverview, impExpObj);
        menu.getMenus().addAll(mainMenu);
        grid.setHgap(10);
        grid.setVgap(10);
        vbox.setStyle("-fx-padding: 10;");
        grid.setStyle("-fx-padding: 10;");
        showStudents();

        Scene root = new Scene(vbox, 840, 900);

        primaryStage.setScene(root);
        primaryStage.show();
    }
}
