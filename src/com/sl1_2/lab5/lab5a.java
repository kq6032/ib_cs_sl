package com.sl1_2.lab5;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Sep. 18, 2018
*/


import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class lab5a {

    public static void ASCIIReturn() throws IOException {
        int charValue = 0;
        char character;

        while (true) {
            try {
                System.out.println("Input a character. If you enter anything longer, it will only use the first character.");
                charValue = System.in.read();
                character = (char) charValue;
                break;
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again");
                continue;
            }
        }

        System.out.println("The character was " + character + " and its value is " + charValue);
    }

    public static void ASCIIRange() throws IOException {
        int char1Val = 0;
        int char2Val = 0;

        while (true) {
            try {
                System.out.println("Input your first character.");
                char1Val = System.in.read();
                break;
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again");
                continue;
            }
        }

        System.in.read();

        while (true) {
            try {
                System.out.println("Input your second character.");
                char2Val = System.in.read();
                break;
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again");
                continue;
            }
        }

        for (int i = ((char1Val > char2Val) ? (char2Val) : (char1Val)); i < ((char1Val > char2Val) ? (char1Val + 1) : (char2Val + 1)); i++) {
            System.out.println((char) i + " " + i);
        }
    }

    public static void copyNextInt() throws IOException {
        ArrayList<Character> list= new ArrayList<>();
        int input = 0;

        System.out.println("Welcome to a copy of nextInt()");
        System.out.println("Please input a six digit number.");

        for (int i = 0; i < 7; i++) {
            input = System.in.read();
            if (((int) input == 32) || ((int) input == 13)) {
                continue;
            }
            list.add((char) input);
        }

        System.out.print("\nYour number was ");
        for (int j = 0; j < list.size(); j++) {
            if (((int) list.get(j) == 32) || ((int) list.get(j) == 13)) {
                continue;
            }
            System.out.print(list.get(j));
        }
    }

    public static void numberAdder() throws IOException{
        ArrayList<Character> list1= new ArrayList<>();
        ArrayList<Character> list2 = new ArrayList<>();
        int input = 0;
        int sum = 0;
        int mult = 100000;

        System.out.println("Welcome to a number adder.");
        System.out.println("Please input two numbers with a space in between.");
        System.out.println("Please don't exceed 6 numbers (excluding enter)");

        for (int i = 0; i < 7; i++) {
            input = System.in.read();
            if (input == 32 || input == 13) {
                break;
            }
            list1.add((char) input);
            sum += (input - (int) '0') * mult;
            mult /= 10;
        }

        mult = 100000;
        for (int i = 0; i < 7; i++) {
            input = System.in.read();
            if (input == 32 || input == 13) {
                break;
            }
            list2.add((char) input);
            sum += (input - (int) '0') * mult;
            mult /= 10;
        }

        System.out.println(sum);
    }

    public static void main(String args[]) throws IOException {

        int option = 0;
        Scanner input = new Scanner(System.in);

        while (true) {
            boolean loopBool = true;
            System.out.println("\nOptions:");
            System.out.println("1. ASCII Code for Character");
            System.out.println("2. ASCII Characters and Codes Between Two Characters");
            System.out.println("3. Fake nextInt()");
            System.out.println("4. Number adder");
            System.out.println("5. Quit");
            System.out.println("Please input option number:");

            while (loopBool) {
                if (input.hasNextInt()) {
                    loopBool = false;
                }

                option = input.nextInt();

                if (option != 1 && option != 2 && option != 3 && option != 4) {
                    System.out.println("Please try again. That isn't an option.");
                }
            }

            if (option == 1) {
                ASCIIReturn();
            } else if (option == 2) {
                ASCIIRange();
            } else if (option == 3) {
                copyNextInt();
                continue;
            } else if (option == 4) {
                numberAdder();
            } else{
                System.out.println("Bye!");
                break;
            }
        }
    }
}
