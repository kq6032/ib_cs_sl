package com.sl1_2.lab7;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Nov. 6, 2018
*/

import java.io.*;

/**
 *
 */
class Student {
    int studentId;
    int grade;
    String lastName;
    String firstName;
    String gender;

    /**
     * Constructor for the student class
     * @param studentId student ID number
     * @param grade current grade of the student
     * @param lastName last name of the student
     * @param firstName first name of the student
     * @param gender gender of the student
     */
    public Student(int studentId, int grade, String lastName, String firstName, String gender) {
        this.studentId = studentId;
        this.grade = grade;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
    }
}

/**
 *
 */
public class Lab7 {

    private static final int STUDENT_LISt_SIZE = 128;

    /**
     *
     * @param studentList
     */
    private static void printStudentInfo(Student[] studentList, int studentCount) {
        int i = 0;

        System.out.println("Number of students: " + studentList.length);

        while (i < studentCount) {
            System.out.printf("%d %-2d %16s %16s %s\n", studentList[i].studentId, studentList[i].grade,
                              studentList[i].firstName, studentList[i].lastName, studentList[i].gender);
            i++;
        }
    }

    public static void main(String args[]) {
        //Data read in from the file
        int data;
        String dataString = new String();
        byte[] inputBuffer = new byte[16384];
        int currentIndex = 0;

        //Array of strings separated by
        String[] splitDataStrings;

        //List of different students
        Student studentList[] = new Student[STUDENT_LISt_SIZE];

        //Student information
        int studentid;
        int grade;
        String lastName;
        String firstName;
        String gender;

        int studentCount = 0;

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

        printStudentInfo(studentList, studentCount);
    }
}
