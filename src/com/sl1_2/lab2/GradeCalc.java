package com.sl1_2.lab2;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Aug. 29, 2018
*/
import java.util.Scanner;

class GradeCalc {
    public static void main(String args[]) {

        float grade = 0;

        Scanner input = new Scanner(System.in);

        System.out.println("Please input a grade to change to a letter");
        grade = input.nextFloat();

        if (grade >= 90) {
            System.out.println("The grade is an A");
        } else if (grade < 90 && grade >= 80) {
            System.out.println("The grade is a B");
        } else if (grade < 80 && grade >= 70) {
            System.out.println("The grade is a C");
        } else if (grade < 70 && grade >= 60) {
            System.out.println("The grade is a D");
        } else {
            System.out.println("The grade is an F");
        }
    }
}
