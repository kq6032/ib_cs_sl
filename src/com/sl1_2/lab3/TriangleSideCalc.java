package com.sl1_2.lab3;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Sep. 2, 2018
*/

import java.text.DecimalFormat;
import java.util.Scanner;

public class TriangleSideCalc {

    public static double getSideVal (Scanner input, String sideName) {

        double side = 0;
        while (true) {
            try {
                System.out.println("Please input the value of side " + sideName);
                side = input.nextDouble();
                if (side <= 0) {
                    System.out.println("You can't have a side shorter than 0 or less units. Please try again.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
                input.next();
            }
        }

        return side;
    }

    public static void main(String args[]) {

        double sideA = 0;
        double sideB = 0;
        double sideC = 0;
        int option = 0;

        Scanner input = new Scanner(System.in);

        DecimalFormat df = new DecimalFormat("#####.###");

        System.out.println("Welcome to triangle side calculator!");

        System.out.println("   |\\      ");
        System.out.println("   | \\     ");
        System.out.println(" A |  \\ C  ");
        System.out.println("   |   \\   ");
        System.out.println("   |____\\  ");
        System.out.println("      B  \n ");

        while (true) {
            try {
                System.out.println("Known Sides (Enter Option Number):\n1. A & B\n2. A & C\n3. B & C");
                option = input.nextInt();
                if (option != 1 && option != 2 && option!= 3) {
                    System.out.println("That option doesn't exist. Please try again.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
                input.next();
            }
        }

        if (option == 1) {
            sideA = getSideVal(input, "A");
            sideB = getSideVal(input, "B");
            sideC = Math.sqrt(Math.pow(sideA, 2) + Math.pow(sideB, 2));
        } else if (option == 2) {
            while (true) {
                sideA = getSideVal(input, "A");
                sideC = getSideVal(input, "C");
                if (sideA >= sideC) {
                    System.out.println("Side A can't be longer than side C. Please try again.");
                    continue;
                }
                break;
            }
            sideB = Math.sqrt(Math.pow(sideC, 2) - Math.pow(sideA, 2));
        } else {
            while (true) {
                sideB = getSideVal(input, "B");
                sideC = getSideVal(input, "C");
                if (sideB >= sideC) {
                    System.out.println("Side B can't be longer than side C. Please try again.");
                    continue;
                }
                break;
            }
            sideA = Math.sqrt(Math.pow(sideC, 2) - Math.pow(sideB, 2));
        }

        System.out.println("Side A: " + df.format(sideA) + "\nSide B: " + df.format(sideB) + "\nSide C: " + df.format(sideC));

    }
}
