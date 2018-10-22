package com.sl1_2.lab5;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Oct. 16, 2018
*/

import java.util.*;

public class lab5b {

    /**
     * basicIntInput
     * method for asking for a single integer input from user
     * @param message message to prompt user for response
     * @param in
     * @return
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
     * guessingGame
     * number guessing game between 1 and 1000
     * @param in
     */
    public static void guessingGame(Scanner in) {
        Random rand = new Random();

        int randomNum = rand.nextInt(1000);
        int guessCount = 0;
        int guess;
        boolean run = true;

        System.out.println("Guess a number between 0 and 1000 (not including 1000)!");

        //guessing game code, runs until the number is guessed
        while (run) {
            guess = basicIntInput("Your current " +
                    "number of guesses is " + guessCount + "\n", in);
            guessCount++;

            if (guess == randomNum) {
                System.out.println("That's the right number!");
                run = false;
            } else if (guess > randomNum) {
                System.out.println("Your guess is too high!");
            } else if (guess < randomNum) {
                System.out.println("Your guess is too low!");
            }
        }

        System.out.printf("\nYour total number of guesses is %d\n", guessCount);
    }

    /**
     * strCompare
     * compares two strings that are given as parameters
     * @param str1 first string to compare
     * @param str2 second string to compare
     * @return
     */
    public static int strCompare(String str1, String str2) {
        int numSame = 0;

        //looks for the amount of characters that are the same in the two strings
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) == str2.charAt(i)) {
                if ((str1.charAt(i) == 0) && (i == 0)) {
                    //do nothing
                } else {
                    numSame++;
                }
            }
        }

        return numSame;
    }

    /**
     * guessingGameChallenge
     * challenge version of number guessing game that tells correct digits in guess
     * @param in
     */
    public static void guessingGameChallenge(Scanner in) {
        Random rand = new Random();

        String randomNum;
        String guessString;
        int guessCount = 0;
        int guess;
        int digitsCorrect;
        boolean run = true;

        randomNum = Integer.toString(rand.nextInt(1000));

        while (randomNum.length() < 3) { randomNum = "0" + randomNum; }

        //main guessing game code
        while (run) {
            guess = basicIntInput("Your current number of guesses is " + guessCount + "\n", in);
            guessCount++;
            //turns guess into a string for easier comparing
            guessString = Integer.toString(guess);

            //prepends 0 to guess string until same length as target number to simplify comparing
            while (guessString.length() < 3) { guessString = "0" + guessString; }

            if (Objects.equals(guessString, randomNum)) {
                System.out.println("That's the right number!");
                run = false;
            }

            //compares the characters in the strings to each other to determine how many are correct
            digitsCorrect = strCompare(guessString, randomNum);
            System.out.printf("\nYou had %d digits correct in your guess.", digitsCorrect);
        }
        System.out.printf("\nYour total number of guesses is %d\n", guessCount);
    }

    /**
     * linedStairsGen
     * generates a stair shaped pattern formed out of numbers
     * @param in
     */
    public static void linedStairsGen(Scanner in) {
        int inputNum;
        int lineNum;
        int count = 1;

        //asking for number of lines to draw from user
        inputNum = basicIntInput("Please input a positive integer.", in);
        lineNum = inputNum;

        while (lineNum > 0) {
            for (int i = 0; i < count; i++) {
                System.out.print(lineNum + "    ");
            }
            count++;
            lineNum--;
            System.out.println();
        }
    }

    /**
     * drawRect
     * creates a rectangle out of asterisks using width and heights inputted by user
     * @param in
     */
    public static void drawRect(Scanner in) {
        int width;
        int height;

        System.out.println("Welcome to the rectangle drawer!\n");
        //asking for width and height of rectangle from the users
        width = basicIntInput("Please input desired width.", in);
        height = basicIntInput("Please input desired height.", in);

        //drawing the rectangle, heightLp and widthLp are used instead of i and j for clarification
        for (int heightLp = 1; heightLp <= height; heightLp++) {
            for (int widthLp = 1; widthLp <= width; widthLp++) {
                if (heightLp == 1 || heightLp == height || widthLp == 1 || widthLp == width)  {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    /**
     * factorialTable
     * generates a table of factorials up to number determined by user
     * @param in
     */
    public static void factorialTable(Scanner in) {
        int startNum;
        int factorial;

        System.out.println("Welcome to the factorial table generator!");
        //prompts user for number of desired lines
        startNum = basicIntInput("Please input desired number of factorials.", in);
        System.out.println("0! = 1");

        //this loop prints out the equation and calculates the factorial of the inputted number
        for (int i = 1; i <= startNum; i++) {
            factorial = 1;
            System.out.printf("%d! = ", i);
            for (int j = i; j >= 1; j--) {
                if (j == 1) {
                    break;
                } else {
                    System.out.printf("%d x ", j);
                }
                factorial *= j;
            }
            System.out.printf("1 = %d\n", factorial);
        }
    }

    /**
     * primeGen
     * generates the first 1000 prime numbers and prints them out
     * @param in
     */
    public static void primeGen(Scanner in) {
        int[] primeArray = new int[1000];
        int currentNum = 3;
        int i = 1;
        boolean isPrime;

        primeArray[0] = 2;

        //loop for testing primes
        while (i < primeArray.length) {
            isPrime = true;

            //tests the current number against the previously generated primes
            for (int j = 0; j < i; j++) {
                if (currentNum % primeArray[j] == 0) {
                    isPrime = false;
                }
            }

            //moves onto next array element if the number is prime
            if (isPrime) {
                primeArray[i] = currentNum;
                i++;
            } else {
                currentNum += 2;
                continue;
            }
        }

        //printing out the elements of the array
        for (int j : primeArray) {
            System.out.println(j);
        }
    }

    /**
     * main
     * asks user which program to run
     * @param args
     */
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int option;
        boolean run = true;

        //main loop asking which program to run
        while (run) {
            option = basicIntInput("Please input an option:\n1. Number Guessing Game" +
                    "\n2. Number Guessing Game (Challenge)\n3. Line Stairs Generator" +
                    "\n4. Draw a Rectangle\n5. Factorial Table Generator\n6. Prime Number Generator\n7. Exit", in);

            switch (option) {
                case 1: guessingGame(in);
                    break;
                case 2: guessingGameChallenge(in);
                    break;
                case 3: linedStairsGen(in);
                    break;
                case 4: drawRect(in);
                    break;
                case 5: factorialTable(in);
                    break;
                case 6: primeGen(in);
                    break;
                default: System.out.println("Goodbye!");
                    in.close();
                    run = false;
            }
        }
    }
}
