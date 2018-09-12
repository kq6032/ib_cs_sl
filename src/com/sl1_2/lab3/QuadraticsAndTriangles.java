package com.sl1_2.lab3;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Sep. 6, 2018
*/

import java.util.Scanner;
import java.text.DecimalFormat;

public class QuadraticsAndTriangles {

    public static void quadratic() {

        double coeff1 = 0;
        double coeff2 = 0;
        double constant = 0;
        double root1 = 0;
        double root2 = 0;
        double typeTest = 0;
        String i = "i";

        DecimalFormat df = new DecimalFormat("#####.###");

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the quadratic root calculator!");
        System.out.println("Ax^2 + Bx + C = 0");

        while (true) {
            try {
                System.out.println("Please input the coefficient of the x^2 term (A):");
                coeff1 = input.nextDouble();
                if (coeff1 == 0) {
                    System.out.println("0 is not a valid input, as that would make the equation linear. Please try again.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
                input.next();
            }
        }

        while (true) {
            try {
                System.out.println("Please input the coefficient of the x term (B)");
                coeff2 = input.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
                input.next();
            }
        }

        while (true) {
            try {
                System.out.println("Please input the constant term (C)");
                constant = input.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
                input.next();
            }
        }

        typeTest = Math.pow(coeff2, 2) - (4 * coeff1 * constant);
        if (typeTest < 0) {
            root1 = (-coeff2) / (2 * coeff1);
            root2 = (Math.sqrt(-1 * (Math.pow(coeff2, 2)
                    - (4 * coeff1 * constant)))) / (2* coeff1);
            System.out.println("The roots are imaginary.");
            System.out.println(df.format(root1) + " + " + df.format(root2) + i + ", " + df.format(root1) + " - " + df.format(root2) + i + "\n");
        } else {
            root1 = ((-coeff2 + Math.sqrt(Math.pow(coeff2, 2)
                    - (4 * coeff1 * constant))) / (2 * coeff1));
            root2 = ((-coeff2 - Math.sqrt(Math.pow(coeff2, 2)
                    - (4 * coeff1 * constant))) / (2 * coeff1));
            System.out.println("The roots are real.");
            if (root1 == root2) {
                System.out.println(root1 + "\n");
            } else {
                System.out.println(df.format(root1) + ", " + df.format(root2) + "\n");
            }
        }
    }

    public static void triangle() {

        double sideA = 0;
        double sideB = 0;
        double sideC = 0;
        double angleA = 0;
        double angleB = 0;
        double angleC = 90;
        int option = 0;

        Scanner input = new Scanner(System.in);

        DecimalFormat df = new DecimalFormat("#####.###");

        System.out.println("Welcome to triangle side calculator!");

        System.out.println("   B          ");
        System.out.println("   |\\        ");
        System.out.println("   | \\       ");
        System.out.println(" a |  \\ c    ");
        System.out.println("   |_  \\     ");
        System.out.println("   |_|__\\    ");
        System.out.println("  C   b   A\n ");

        while (true) {
            try {
                System.out.println("Known Sides (Enter Option Number):\n1. a & b\n2. a & c\n3. b & c");
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

        angleA = (Math.asin(sideA / sideC)) / Math.PI * 180;
        angleB = (Math.asin(sideB / sideC)) / Math.PI * 180;

        System.out.println("Side a: " + df.format(sideA) + "\nSide b: " + df.format(sideB) + "\nSide c: " + df.format(sideC));
        System.out.println("Angle A: " + df.format(angleA) + "\nAngle B " + df.format(angleB) + "\nAngle C: " + df.format(angleC) + "\n");
    }

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

    public static void main(String Args[]) {

        int option = 0;

        Scanner input = new Scanner(System.in);

        while (true) {

            boolean loopBool = true;
            System.out.println("Options:");
            System.out.println("1. Quadratic Root Finder");
            System.out.println("2. Triangle Side Finder");
            System.out.println("3. Quit");
            System.out.println("Please input option number:");

            while (loopBool) {
                if (input.hasNextInt()) {
                    loopBool = false;
                }

                option = input.nextInt();

                if (option != 1 && option != 2 && option != 3) {
                    System.out.println("Please try again. That isn't an option.");
                    continue;
                }
            }

            if (option == 1) {
                quadratic();
            } else if (option == 2) {
                triangle();
            } else {
                System.out.println("Bye!");
                break;
            }
        }
    }
}
