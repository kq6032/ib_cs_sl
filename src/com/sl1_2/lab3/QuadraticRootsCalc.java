package com.sl1_2.lab3;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Aug. 29, 2018
*/

import java.util.Scanner;
import java.text.DecimalFormat;

class QuadraticRootsCalc {
    public static void main(String Args[]) {

        /*
         Ax^2 + Bx + C = 0
         coeff1 = A
         coeff2 = B
         const = C
        */
        double coeff1 = 0;
        double coeff2 = 0;
        double constant = 0;
        double root1 = 0;
        double root2 = 0;
        double typeTest = 0;
        String i = "i";

        DecimalFormat df = new DecimalFormat("####.###");

        System.out.println("Ax^2 + Bx + C = 0");

        Scanner input = new Scanner(System.in);
        System.out.println("Please input the coefficient of the x^2 term (A):");
        coeff1 = input.nextDouble();

        System.out.println("Please input the coefficient of the x term (B)");
        coeff2 = input.nextDouble();

        System.out.println("Please input the constant term (C)");
        constant = input.nextDouble();

        typeTest = Math.pow(coeff2, 2) - (4 * coeff1 * constant);
        if (typeTest < 0) {
            root1 = (-coeff2) / (2 * coeff1);
            root2 = (Math.sqrt(-1 * (Math.pow(coeff2, 2)
                        - (4 * coeff1 * constant)))) / (2* coeff1);
            System.out.println("The roots are imaginary.");
            System.out.println(df.format(root1) + " + " + df.format(root2) + i + ", " + df.format(root1) + " - " + df.format(root2) + i);
        } else {
            root1 = ((-coeff2 + Math.sqrt(Math.pow(coeff2, 2)
                        - (4 * coeff1 * constant))) / (2 * coeff1));
            root2 = ((-coeff2 - Math.sqrt(Math.pow(coeff2, 2)
                        - (4 * coeff1 * constant))) / (2 * coeff1));
            System.out.println("The roots are real.");
            if (root1 == root2) {
                System.out.println(root1);
            } else {
            System.out.println(df.format(root1) + ", " + df.format(root2));
            }
        }
    }
}
