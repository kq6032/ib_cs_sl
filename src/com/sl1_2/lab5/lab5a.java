package com.sl1_2.lab5;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Oct. 20, 2018
*/


import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class lab5a {

    /**
     * ASCIIReturn()
     * receives user input through System.in.read() and casts the resultant integer into a character
     * @throws IOException
     */
    public static void ASCIIReturn() throws IOException {
        int charValue;
        char character;

        System.out.println("Please enter a single character.");

        charValue = System.in.read();
        character = (char) charValue;

        System.out.println("The character was " + character + " and its value is " + charValue);
    }

    /**
     * ASCIIRange()
     * finds characters between ASCII values of two user entered characters
     * @throws IOException
     */
    public static void ASCIIRange() throws IOException {
        int char1Val, char2Val;

        System.out.println("Please input the first character.");
        char1Val = System.in.read();
        //flushing the character buffer since enter has a char value
        System.in.read();
        System.out.println("Pleas input the second character.");
        char2Val = System.in.read();

        //finds the entered character of lower value and prints out characters
        //in between until reaching the character of higher ASCII value
        for (int i = ((char1Val > char2Val) ? (char2Val) : (char1Val));
             i < ((char1Val > char2Val) ? (char1Val + 1) : (char2Val + 1)); i++) {
            System.out.println((char) i + " " + i);
        }
    }

    /**
     * copyNextInt()
     * a (supposed) copy of Scanner.nextInt() method using System.in.read()
     * uses assumption that the user is only entering a six digit number
     * @throws IOException
     */
    public static void copyNextInt() throws IOException {
        char[] charArray = new char[6];
        int input;
        int i = 0;

        System.out.println("Welcome to a copy of Scanner.nextInt()");
        System.out.println("Please input a six digit number.");

        //loop for getting numbers
        while (i < 7) {
            input = System.in.read();

            //if the character is a space or an enter, it leaves the loop
            if (input == 13 || input == 10) {
                i = 7;
            } else {
                charArray[i] = (char) input;
                i++;
            }
        }

        System.out.println("Your number was ");

        for (int j = 0; j < charArray.length; j++) {
            System.out.print(charArray[j]);
        }
    }

    /**
     * getNumFromArray()
     * takes digits of a number from an array and the length of the number to turn it into a integer of decimal form
     * @param inArray array containing a number separated by individual digits in each element
     * @param numLength length of the supposed number in the array
     * @return
     */
    public static int getNumFromArray(int[] inArray, int numLength) {
        int number = 0;
        int mult = 1;

        //loop starts accessing values in the array starting at index numLenght - 1
        for (int i = numLength - 1; i >= 0; i--) {
            //the value from the array is multiplied by mult so that the number is correct
            number += inArray[i] * mult;
            mult *= 10;
        }

        return number;
    }

    /**
     * numberAdder()
     * adds together two inputted numbers using System.in.read() and ASCII digit values
     * @throws IOException
     */
    public static void numberAdder() throws IOException{
        //two arrays are for storing the individual digits of the entered number
        int[] num1 = new int[6];
        int[] num2 = new int[6];
        int input, i, j;
        int count1 = 0;
        int count2 = 0;
        int sum = 0;

        System.out.println("Welcome to a number adder.\nInput the first number, not exceeding 6 digits");
        i = 0;

        //input for first number
        while (i < 7) {
            input = System.in.read();

            //if the character is a space or an enter, it leaves the loop
            if (input == 13 || input == 10) { i = 7; } else {
                //the integer ASCII value of '0' character is subtracted from input to get integer value of input
                num1[i] = input - (int) '0';
                count1++;
                i++;
            }
        }

        System.out.println("Input the second number not exceeding 6 digits");
        j = 0;

        //input for second number
        while (j < 7) {
            input = System.in.read();

            //if the character is a space or an enter, it leaves the loop
            if (input == 13 || input == 10) { j = 7; } else {
                //the integer ASCII value of '0' character is subtracted from input to get integer value of input
                num2[j] = input - (int) '0';
                count2++;
                j++;
            }
        }

        //uses the arrays from the input ex: [1, 2, 3, 4, 0, 0], and the length of the entered number ex: 4
        //and plugs into getNumFromArray to turn the array into integers
        sum += getNumFromArray(num1, count1) + getNumFromArray(num2, count2);
        System.out.println("The sum of the two numbers was " + sum);
    }

    /**
     * main
     * main function for choosing which program to run
     * @param args
     * @throws IOException
     */
    public static void main(String args[]) throws IOException {
        Scanner in = new Scanner(System.in);

        int option = 0;
        boolean run = true;
        boolean inputRun;

        while (run) {
            inputRun = true;
            System.out.println("\nOptions:\n1. ASCII Code for Character\n2. ASCII Characters and Codes Between Two " +
                    "Characters\n3. Fake nextInt()\n4. Number adder\n5. Quit\nPlease input option number (Enter " +
                    "0 to quit):");

            while (inputRun) {

                while (!in.hasNextInt()) {
                    System.out.println("That isn't a valid input.");
                    in.next();
                }

                option = in.nextInt();

                if (option >= 0) { inputRun = false; }
            }

            switch (option) {
                case 1: ASCIIReturn();
                    break;
                case 2: ASCIIRange();
                    break;
                case 3: copyNextInt();
                    break;
                case 4: numberAdder();
                    break;
                default: System.out.println("Goodbye!");
                    in.close();
                    run = false;
            }
        }
    }
}
