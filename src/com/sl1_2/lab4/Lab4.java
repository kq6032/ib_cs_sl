package com.sl1_2.lab4;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Oct. 18, 2018
*/

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Lab4 {

    /**
     * basicIntInput
     * method for asking for a single integer input from user
     * @param message message to prompt user for response
     * @param in
     * @return the integer input of the user
     */
    public static int basicIntInput(String message, Scanner in) {
        int input = 0;
        boolean run = true;

        while (run) {
            System.out.println(message);

            //ensures that the input is valid
            while (!in.hasNextInt()) {
                System.out.println("That input is invalid. Input something else.");
                in.next();
            }

            input = in.nextInt();

            //if the input is a valid positive integer, the loop stops
            if (input >= 0) {
                run = false;
            }
        }

        return input;
    }

    /**
     * basicDoubleInput
     * method for asking for a single double input from user
     * @param message message to prompt user for response
     * @param in
     * @return the double input of the user
     */
    public static double basicDoubleInput(String message, Scanner in) {
        double input = 0;
        boolean run = true;

        while (run) {
            System.out.println(message);

            //ensures that the input is valid
            while (!in.hasNextDouble()) {
                System.out.println("That input is invalid. Input something else.");
                in.next();
            }

            input = in.nextDouble();

            //if the input is a valid positive integer, the loop stops
            if (input >= 1 || input < 1) {
                run = false;
            }
        }

        return input;
    }

    /**
     * sumToN
     * adds together numbers preceding the inputted number
     * @param in
     */
    public static void sumToN(Scanner in) {
        int number;
        int sum;
        boolean run = true;

        while (run) {
            sum = 0;
            //asks user for input for the number they want
            number = basicIntInput("Please input a positive integer (natural number) to find the sum of the " +
                                    "numbers preceding it. Input 0 to quit.", in);

            if (number <= 0) {
                run = false;
            } else {

                //prints out numbers being added and adds the numbers to the sum
                while (number > 0) {
                    if (number > 1) {
                        System.out.print(number + " + ");
                    } else {
                        System.out.print(number + " = ");
                    }
                    sum += number;
                    number -= 1;
                }

                System.out.print(sum + "\n");
            }
        }
    }

    /**
     * gradeLetterCalc
     * a method for determining the letter grade of a provided score in the form of an integer where
     * 0 = A, 1 = B, 2 = C, 3 = D, 4 = F
     * @param gradeCount array of grades recorded in grading program
     * @param score the score decide the letter grade of
     * @param includeInCount whether to include the letter grade in the array of all grades
     * @return returns an integer between 0 and 4 (inclusive), which correspond with grade letters in the gradeNames
     *         array in the gradingProgram method
     */
    public static int gradeLetterCalc(int[] gradeCount, double score, boolean includeInCount) {
        int number;

        //upper bounds aren't needed as the prior if statements already are effectively upper bounds
        if (score >= 90) {
            number = 0;
        } else if (score >= 80) {
            number = 1;
        } else if (score >= 70) {
            number = 2;
        } else if (score >= 60) {
            number = 3;
        } else {
            number = 4;
        }

        if (includeInCount) {
            gradeCount[number]++;
        }

        return number;
    }

    /**
     * gradingProgram
     * asks user for grades and calculates scores, number of each letter grade,
     * @param in
     */
    public static void gradingProgram(Scanner in) {
        //allows for formatting of doubles,
        DecimalFormat df = new DecimalFormat("#####.###");

        //gradeNames and gradCount arrays are for recording the number of each grade
        String[] gradeNames = new String[]{"A", "B", "C", "D", "F"};
        int[] gradeCount = new int[5];
        double totalScore, currentScore;
        double scoreCount = 0;
        double sum = 0;
        boolean run = true;

        totalScore = basicDoubleInput("Please input the total number of points in the test.", in);

        while (run) {
            currentScore = basicDoubleInput("Please input a score. Input a negative number to quit.", in);

            if (currentScore < 0) {
                run = false;
            } else {
                sum += currentScore;
                scoreCount++;
                gradeLetterCalc(gradeCount, currentScore / totalScore * 100, true);
            }
        }

        System.out.println("\nThe average score of the class was " + df.format(sum / scoreCount) + " points" +
            "\nThe average percentage of the class was " + df.format(sum / scoreCount / totalScore * 100) +
            "% and the grade is " + gradeNames[gradeLetterCalc(gradeCount, sum / scoreCount, false)]
            + ".\nAmount per Grade:");

        for (int i = 0; i < 5; i++) {
            System.out.println(gradeNames[i] + ": " + gradeCount[i]);
        }
    }

    /**
     * reciprocalAdder
     * adds together the reciprocals of ten or less numbers provided by user
     * @param in
     */
    public static void reciprocalAdder(Scanner in) {
        double currentNumber;
        double sum = 0;
        //Stuff for the challenge
        double multiple = 1;
        double numerator = 0;
        double[] numbers = new double[10];
        //used to keep track of number of numbers entered into numbers array
        int numberAmt = 0;
        int i = 1;
        boolean run = true;

        //loop asks for user input and finds sum of added reciprocals
        while (run) {
            if (i == 10) { run = false; }

            currentNumber = basicDoubleInput("Current sum: " + sum + "\nCurrent number: " + i +
                    "\nPlease input a number. Its reciprocal will be added to the running sum. " +
                    "Input 0 to quit.", in);

            if (currentNumber == 0) { run = false; } else {
                sum += 1 / currentNumber;
                multiple *= currentNumber;
                numbers[i - 1] = currentNumber;
                numberAmt++;
                i++;
            }
        }

        for (int j = 0; j < numberAmt - 1; j++) {
            numerator += multiple / numbers[j];
        }

        System.out.println("Final Sum: " + sum + "\nFinal Sum (Fraction): " + numerator + " / " + multiple + "\n");
    }

    /**
     * lcm_gcd
     * finds the least common multiple and greatest common denominator of two inputted integers
     * @param in
     */
    public static void lcm_gcd(Scanner in) {
        //the numbers inputted by the user.
        int num1, num2;
        //the numbers where the lcm and gcd will be stored
        int lcm, gcd;
        boolean run = true;

        //gets input from the user
        num1 = Math.abs(basicIntInput("Please input the first number.", in));
        num2 = Math.abs(basicIntInput("Please input the second number.", in));

        if (num1 != num2) {
            //lcm starts off as the smaller number
            lcm = (num1 > num2) ? num2 : num1;

            //tests whether the number is divisible by both
            while (run) {
                if (lcm % num1 == 0 && lcm % num2 == 0) { run = false; } else { lcm++; }
            }

            //gcd is calculated using a special equation that uses the lcm
            gcd = (num1 * num2) / lcm;


        } else {
            lcm = num1;
            gcd = num1;
        }

        System.out.println("The least common multiple is " + lcm + "\nThe greatest common denominator is " + gcd);
    }

    /**
     * main
     * main method which runs the other programs
     * @param args
     */
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int option = 0;
        boolean run = true;

        while (run) {
            option = basicIntInput("Options:\n1. Sum of preceding numbers of number\n2. Grading Program" +
                    "\n3. Reciprocal Adder\n4. Least Common Multiple / Greatest Common Divisor\n5. Quit" +
                    "\nPlease input option number:", in);

            switch (option) {
                case 1: sumToN(in);
                    break;
                case 2: gradingProgram(in);
                    break;
                case 3: reciprocalAdder(in);
                    break;
                case 4: lcm_gcd(in);
                    break;
                default: in.close();
                    System.out.println("Goodbye!");
                    run = false;
            }
        }
    }
}
