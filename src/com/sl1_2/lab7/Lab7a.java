package com.sl1_2.lab7;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Nov. 6, 2018
*/

import java.io.*;
import java.util.Scanner;
import com.sl1_2.lab7.Student;

/**
 * Main class
 */
public class Lab7a {

    private static final int STUDENT_LIST_SIZE = 128;

    /**
     * Reads in from the "classlist" file to get the students
     * @param studentList empty list of students to fill
     * @return studentCount - the number of students found in the file
     */
    private static int readFile(Student[] studentList) {
        //Data read in from the file
        int data;
        String dataString = new String();
        byte[] inputBuffer = new byte[16384];
        int currentIndex = 0;

        //Array of strings separated by
        String[] splitDataStrings;

        //Student information
        int studentid;
        int grade;
        String lastName;
        String firstName;
        String gender;

        int studentCount = 0;

        //Reading in from the file
        try (DataInputStream input = new DataInputStream(new FileInputStream(
                "C:\\Users\\kevin\\Desktop\\ib_cs_sl\\src\\com\\sl1_2\\lab7\\classlist.txt"))) {
            data = input.read(inputBuffer);

            //Checking if the input file is valid
            if (data != -1) {
                for (int i = 0; i < data; i++) {
                    dataString += (char) inputBuffer[i];
                }
            }

            //Splitting the string read in into separate parts by any whitespace
            splitDataStrings = dataString.split("[,\\s]+");

            while (currentIndex < splitDataStrings.length) {
                studentid = Integer.parseInt(splitDataStrings[currentIndex]);
                grade = Integer.parseInt(splitDataStrings[currentIndex + 1]);
                lastName = splitDataStrings[currentIndex + 2];
                firstName = splitDataStrings[currentIndex + 3];
                gender = splitDataStrings[currentIndex + 4];

                studentList[studentCount] = new Student(studentid, grade, lastName, firstName, gender);

                studentCount++;
                currentIndex += 5;
            }
        } catch (IOException e) {
            System.out.printf("\nRead error: %s", e.getMessage());
        }

        return studentCount;
    }

    /**
     * Prints out student information from the student list
     * @param studentList list of students
     * @param studentCount number of students
     */
    private static void printStudentInfo(Student[] studentList, int studentCount) {
        System.out.println("Number of students: " + studentCount);

        for (int i = 0; i < studentCount; i++) {
            System.out.printf("%d %-2d %16s %16s %s\n", studentList[i].getId(), studentList[i].getGrade(),
                              studentList[i].getLastName(), studentList[i].getFirstName(), studentList[i].getGender());
        }
    }

    /**
     * Tallies totals for each grade, gender, and first character of the last name
     * @param studentList list of students
     * @param studentCount number of students
     */
    private static void studentInfoTally(Student[] studentList, int studentCount) {
        //Grades of students: 0 - Freshmen, 1 - Sophomores, 2 - Juniors, 3 - Seniors
        int[] grades = new int[4];
        //Genders of students: 0 - Male, 1 - Female
        int[] gender = new int[2];
        //First character of last names: 0 - A, 1 - B, ... 24 - Y, 25 - Z
        int[] letters = new int[26];

        int lastNameChar;

        int i = 0;
        while (i < studentCount) {
            //Count the grade of the student
            if (studentList[i].getGrade() == 9) {
                grades[0]++;
            } else if (studentList[i].getGrade() == 10) {
                grades[1]++;
            } else if (studentList[i].getGrade() == 11) {
                grades[2]++;
            } else if (studentList[i].getGrade() == 12) {
                grades[3]++;
            }

            if (studentList[i].getGender().equals("Male")) {
                gender[0]++;
            } else if (studentList[i].getGender().equals("Female")) {
                gender[1]++;
            }

            lastNameChar = (int) studentList[i].getLastName().charAt(0);

            letters[lastNameChar - 65]++;

            i++;
        }

        System.out.println("Freshmen: " + grades[0] + "\nSophomores: " + grades[1] + "\nJuniors: " + grades[2] +
                "\nSeniors: " + grades[3] + "\n\nMale: " + gender[0] + "\nFemale: " + gender[1] + "\n");

        for (int j = 0; j < letters.length; j++) {
            if (letters[j] != 0) {
                lastNameChar = 65 + j;
                System.out.println((char) lastNameChar + ": " + letters[j]);
            }
        }
    }

    /**
     * Bubble sorts students from the student list by last name or student id and writes to a new file
     * @param studentList list of students
     * @param studentCount number of students counted
     * @param sortByName whether to sort the students by name; false - sort by student id
     */
    private static void sortStudents(Student[] studentList, int studentCount, boolean sortByName) {
        Student tempStudent;
        String fileName = "";
        int compare1, compare2, i, j;

        //Bubble sort the students based on last names or student id
        for (j = 0; j < studentCount - 1; j++) {
            for (i = 0; i < studentCount - 1; i++) {
                //Choose what values to compare
                if (sortByName) {
                    compare1  = (int) studentList[i].getLastName().charAt(0);
                    compare2 = (int) studentList[i + 1].getLastName().charAt(0);
                    fileName = "className.txt";
                } else {
                    compare1 = studentList[i].getId();
                    compare2 = studentList[i + 1].getId();
                    fileName = "classId.txt";
                }

                //Compare the values and swap if needed
                if (compare1 > compare2) {
                    tempStudent = studentList[i];
                    studentList[i] = studentList[i + 1];
                    studentList[i + 1] = tempStudent;

                }
            }
        }

        //Writing the sorted class list into a file
        try (FileWriter fw = new FileWriter(fileName)) {
            for (i = 0; i < studentCount; i++) {
                fw.write(studentList[i].getId() + " " + studentList[i].getGrade() + " " +
                        studentList[i].getLastName() + " " + studentList[i].getFirstName() + " " +
                        studentList[i].getGender() + "\n");
            }
        } catch (IOException e) {
            System.out.printf("\nWrite error: %s", e.getMessage());
        }

        printStudentInfo(studentList, studentCount);
    }

    /**
     * Main method of the program
     * @param args
     */
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        Student studentList[] = new Student[STUDENT_LIST_SIZE];
        int studentCount;
        int input = 0;
        boolean run = true;
        boolean validInput;

        //Read in file for student information and count students
        studentCount = readFile(studentList);

        //Asking user which program to run
        while (run) {
            validInput = false;

            System.out.println("\n1. Get tally of student information\n2. Sort students by last name" +
                    "\n3. Sort students by id number\n4. Quit");

            while (!validInput) {
                while (!in.hasNextInt()) {
                    System.out.println("Invalid input");
                    in.next();
                }

                input = in.nextInt();

                validInput = true;

                if (input != 1 && input != 2 && input != 3 && input != 4) {
                    System.out.println("Input a valid integer");
                    validInput = false;
                }
            }

            if (input == 1) {
                studentInfoTally(studentList, studentCount);
            } else if (input == 2) {
                sortStudents(studentList, studentCount, true);
            } else if (input == 3) {
                sortStudents(studentList, studentCount, false);
            } else {
                run = false;
            }
        }
    }
}
