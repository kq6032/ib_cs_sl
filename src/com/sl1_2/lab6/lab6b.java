package com.sl1_2.lab6;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Nov. 6, 2018
*/

import java.text.DecimalFormat;
import java.util.Scanner;

public class lab6b {

    //Types of expected input for intInput() and doubleInput()
    public enum InputCase {
        NONZERO,
        POSITIVE,
        ANY
    }

    /**
     * intInput()
     * Takes integer input from the user
     * @param in Scanner for input
     * @param message prompt to ask user for input
     * @param inCase type of input expected, whether non-zero, positive, or any integer
     * @return return the integer input from the user
     */
    public static int intInput(Scanner in, String message, InputCase inCase) {
        int input = 0;
        boolean run = true;

        while (run) {
            System.out.println(message);

            while (!in.hasNextInt()) {
                System.out.println("Invalid input");
                in.next();
            }

            input = in.nextInt();

            //Determining if the input is as expected
            if (inCase == InputCase.NONZERO && input != 0) {
                run = false;
            } else if (inCase == InputCase.POSITIVE && input > 0) {
                run = false;
            } else if (inCase == InputCase.ANY) {
                run = false;
            } else {
                //In case of invalid input
                System.out.println("Invalid input");
            }
        }

        return input;
    }

    /**
     * doubleInput()
     * Takes double input from the user
     * @param in Scanner for input
     * @param message prompt to ask user for input
     * @param inCase type of input expected, whether non-zero, positive, or any integer
     * @return return the integer input from the user
     */
    public static double doubleInput(Scanner in, String message, InputCase inCase) {
        double input = 0;
        boolean run = true;

        while (run) {
            System.out.println(message);

            while (!in.hasNextDouble()) {
                System.out.println("Invalid input");
                in.next();
            }

            input = in.nextDouble();

            //Determining if the input is as expected
            if (inCase == InputCase.NONZERO && input != 0) {
                run = false;
            } else if (inCase == InputCase.POSITIVE && input > 0) {
                run = false;
            } else if (inCase == InputCase.ANY) {
                run = false;
            } else {
                //In case of invalid input
                System.out.println("Invalid input");
            }

        }

        return input;
    }

    /**
     * sum7to70k()
     * Sums together all integers from 7 to 70000
     */
    public static void sum7to70k() {
        long sum = 0;

        for (int i = 7; i <= 70000; i++) {
            sum += i;
        }

        System.out.println(sum);
    }

    /**
     * multTableGen()
     * Generates a multiplication table to the dimensions determined by the user
     * @param in Scanner for getting input
     */
    public static void multTableGen(Scanner in) {
        int width, height;

        width = intInput(in, "Input the width of the table", InputCase. POSITIVE);
        height = intInput(in, "Input the height of the table", InputCase.POSITIVE);

        int[][] multTable = new int[height][width];

        //Loop through each row
        for (int i = 0; i < multTable.length; i++) {
            //Loop through each element in the row
            for (int j = 0; j < multTable[i].length; j++) {

                if (i == 0) {
                    //If it is the first row of the table, number the columns
                    multTable[i][j] = j + 1;
                } else if (j == 0) {
                    //If it is the first column, number the rows
                    multTable[i][j] = i + 1;
                } else {
                    //Else set the element to the product of the values at the end of the column and row
                    multTable[i][j] = multTable[i][0] * multTable[0][j];
                }

                /*
                The "%-6d" formatter tells the integer to limit itself to create a string of 6 spaces
                Then, the integer replaces the spaces starting from the left-most digit
                Example: "50" would be justified to "50   "
                */
                System.out.printf("%-6d", multTable[i][j]);
            }

            System.out.println();
        }
    }

    /**
     * fibonacciNumbers()
     * Generates the first 2500 numbers in the fibonacci sequence and prompts user for input
     * @param in Scanner for getting input
     */
    public static void fibonacciNumbers(Scanner in) {
        int input;
        long[] fibArray = new long[2500];
        boolean run = true;

        fibArray[0] = 1;
        fibArray[1] = 1;

        //An element in the fibonacci sequence is determined by the sum of the two preceding numbers in the sequence
        for (int i = 2; i < fibArray.length; i++) {
            fibArray[i] = fibArray[i - 1] + fibArray[i - 2];
        }

        while (run) {
            input = intInput(in, "\nInput an integer for nth number of fibonacci sequence." +
                             "\nInput a negative number or 0 to quit.", InputCase.ANY);

            if (input <= 0) {
                run = false;
            } else if (input >= 2500) {
                System.out.println("Please enter a smaller number.");
            } else {
                System.out.println(fibArray[input - 1]);
            }
        }
    }

    /**
     * interestCalc()
     * Calculates the final balance after 30 years using initial balance and yearly interest
     * @param in Scanner for getting input
     */
    public static void interestCalc(Scanner in) {
        //Format for rounding numbers to the hundredths place
        DecimalFormat df = new DecimalFormat("#####.##");

        double balance;
        double interestRate;

        balance = doubleInput(in, "Input the initial balance", InputCase.POSITIVE);
        interestRate = 1 + (doubleInput(in, "Input the interest rate in percent", InputCase.POSITIVE)) / 100;

        for (int i = 1; i <= 30; i++) {
            balance *= interestRate;
            System.out.printf("Year " + i + ": $" + df.format(balance) + "\n");
        }
    }

    /**
     * realisticInterestCalc()
     * Calculates the amount of money after 30 years of monthly deposits and interest using an initial balance
     * Also finds the amount of money if there was no monthly deposit
     * @param in Scanner for getting input
     */
    public static void realisticInterestCalc(Scanner in) {
        //Format for rounding numbers to the hundredths place
        DecimalFormat df = new DecimalFormat("#####.##");

        double balance;
        double monthlyDeposit;
        double noDepBalance;
        double interestRate;

        balance = doubleInput(in, "Input the initial balance", InputCase.POSITIVE);
        noDepBalance = balance;
        monthlyDeposit = doubleInput(in, "Input your monthly deposit", InputCase.POSITIVE);
        interestRate = 1 + (doubleInput(in, "Input the monthly interest rate", InputCase.POSITIVE)) / 100;

        for (int i = 1; i <= 30 * 12; i++) {
            noDepBalance *= interestRate;
            balance *= interestRate;
            balance += monthlyDeposit;
        }

        System.out.println("Your final balance was $" + df.format(balance));
        System.out.println("Your final balance would be $" + df.format(noDepBalance) + " if no deposits were made.");
    } //Challenge: if you compounded the interest continuously by infinitely small increments, the result would be "e"

    /**
     * picturePattern()
     * Generates a picture pattern based on the dimensions inputted by the user
     * @param in Scanner for getting input from the user
     */
    public static void picturePattern(Scanner in) {
        int width, height;
        
        width = intInput(in, "Input the width of the pattern", InputCase.POSITIVE);
        height = intInput(in, "Input the height of the pattern", InputCase.POSITIVE);

        //Drawing each line of the pattern
        for (int i = 1; i <= height; i++) {

            //Drawing each individual character of the pattern in the line
            for (int j = 1; j <= width; j++) {
                if (i % 2 != 0) {//If it is an uneven line, only print out asterisks
                    System.out.print("*");
                } else {//Otherwise, print out alternating asterisks and zeros
                    if (j % 2 == 0) {
                        System.out.print("0");
                    } else {
                        System.out.print("*");
                    }
                }
            }
            System.out.println();
        }
    }

    /**
     * pictureChallenge()
     * Draws a diamond or X shape with height specified by user
     * @param in Scanner for getting user input
     */
    public static void pictureChallenge(Scanner in) {
        int option;
        int height = 0;
        //spacing is how many spaces should go before the asterisk in each line
        int spacing = 0;
        //How much to decrease/increase the spacing before
        int lineIncrement = 1;
        boolean valid = false;

        option = intInput(in, "1. Draw Diamond\n2. Draw X", InputCase.POSITIVE);

        //Ensures that the number is even
        while (!valid) {
            height = intInput(in, "Input the height of the pattern (non-even).", InputCase.POSITIVE);
            if (height % 2 == 0) {
                System.out.println("Input an uneven number");
            } else {
                valid = true;
            }
        }

        if (option == 1) {
            //spacing is height / 2 rounded down
            spacing = height / 2;
            //Spaces are incremented by -1 instead
            lineIncrement = -1;
        }

        //Printing out each line of the pattern
        for (int offset = 0; offset < height; offset++) {

            for (int i = 0; i < height; i++) {
                if ((i == spacing) || (i == height - spacing - 1)) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }

            if (offset < height / 2) {
                //Left spacing increases for X and decreases for diamond before the center line
                spacing += lineIncrement;
            } else {
                //Left spacing decreases for X and increases for diamond from the center line downwards
                spacing -= lineIncrement;
            }

            //Print out new line
            System.out.println();
        }
    }

    /**
     * calcPi()
     * Uses random points and generates an estimate of pi
     * @param in Scanner to get input
     */
    public static void calcPi(Scanner in) {
        double xPos, yPos;
        double dartsThrown;
        double dartsInCircle = 0;

        dartsThrown = doubleInput(in, "Input the number of darts to throw." +
                                  "", InputCase.POSITIVE);

        //Testing if the points fall into the circle
        for (int i = 0; i < dartsThrown; i++) {
            //Generating random points with x and y values between 0 and 1
            xPos = Math.random();
            yPos = Math.random();

            //Testing if the points fall within the quarter circle
            if (Math.sqrt(Math.pow(xPos, 2) + Math.pow(yPos, 2)) < 1) {
                dartsInCircle++;
            }
        }

        //Ratio of darts in circle to the darts thrown and multiplied by four due to testing area being quarter circle
        System.out.println("The estimate of pi is: " + dartsInCircle / dartsThrown * 4);
    }

    /**
     * main()
     * Main method of the program
     * Asks for which program to run
     * @param args
     */
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int option;
        boolean run = true;

        System.out.println("Welcome to lab 6b!");

        while (run) {
            option = intInput(in, "\nPlease choose an option:\n1. Sum from 7 to 70000" +
                            "\n2. Multiplication Table Generator\n3. Fibonacci Number Generator" +
                            "\n4. Interest Rate Calculator\n5. Realistic Interest Rate Calculator" +
                            "\n6. Picture Pattern\n7. Challenge Patterns\n8. Calculating pi\n9. Quit",
                            InputCase.POSITIVE);

            //Which program to run
            if (option == 1) {
                sum7to70k();
            } else if (option == 2) {
                multTableGen(in);
            } else if (option == 3) {
                fibonacciNumbers(in);
            } else if (option == 4) {
                interestCalc(in);
            } else if (option == 5) {
                realisticInterestCalc(in);
            } else if (option == 6) {
                picturePattern(in);
            } else if (option == 7) {
                pictureChallenge(in);
            } else if (option == 8) {
                calcPi(in);
            } else if (option == 9) {
                in.close();
                System.out.println("Goodbye!");
                run = false;
            } else {
                System.out.println("Invalid input.");
            }
        }
    }
}
