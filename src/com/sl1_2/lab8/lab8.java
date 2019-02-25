package com.sl1_2.lab8;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Jan. 25, 2019
*/

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class lab8 {
    /**
     * Get integer input
     * @param in Scanner for input from system.in
     * @param message Prompt for input
     * @return Given integer
     */
    public static int intInput(Scanner in, String message) {
        int input = 0;
        boolean run = true;

        while (run) {
            System.out.println(message);

            while (!in.hasNextInt()) {
                System.out.println("Invalid input");
                in.next();
            }

            input = in.nextInt();

            run = false;
        }

        return input;
    }

    /**
     * Partitioning method for quicksort
     * Utilizes random pivot index to compare the elements
     * @param array Array of numbers to be sorted
     * @param low Lower bound for sub-array
     * @param high Upper bound for sub-array
     * @param rm Random object for pivot
     * @return The pivot index
     */
    public static int partition(int[] array, int low, int high, Random rm) {
        int pivot = array[rm.nextInt(high - low) + low]; // Accessing array for pivot value
        int i = low - 1;
        int j = high + 1;
        int temp;

        // Swapping the elements of the array
        while (true) {
            // Finding the first element in the array with greater value than the pivot
            do {
                i++;
            } while (array[i] < pivot);

            // Finding the first element in the array from the back with lesser value than pivot
            do {
                j--;
            } while (array[j] > pivot);

            // Returning index to split the original array into further sub-arrays
            if (i >= j) {
                return j;
            }

            // Swapping the elements in array[i] and array[j]
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    /**
     * Recursive quicksort method
     * @param array Array of numbers to sort
     * @param low Lower bound for sub-array in recursive calls
     * @param high Upper bound for sub-array in recursive calls
     * @param rm Random object for partitioning method pivot
     */
    public static void quicksort(int[] array, int low, int high, Random rm) {
        int splitIndex;

        // Continues sorting while lower and upper indices are different
        if (low < high) {
            splitIndex = partition(array, low, high, rm);
            quicksort(array, low, splitIndex, rm);
            quicksort(array, splitIndex + 1, high, rm);
        }
    }

    /**
     * Prints out smallest of 3 given integers
     * @param in Scanner for input from System.in
     */
    public static void smallestNum(Scanner in) {
        int nums[] = new int[3];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = intInput(in, "Input a number");
        }

        // Uses quicksort to sort the array
        quicksort(nums, 0, nums.length - 1, new Random());

        // nums[0] is smallest value after being sorted
        System.out.println("The smallest number entered was " + nums[0]);
    }

    /**
     * Check if a number is prime
     * @return Whether the inputted number is prime
     */
    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }

        // Checks whether a number is prime from 2 up to the square root of the number
        for (int i = 2; i < Math.ceil(Math.sqrt((double) n)); i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Calculates the prime factors of an integer
     * @param num Number to find prime factors for
     * @return Arrays.copyOfRange(arr, 0, j + 1) Returns another array with the filled part of the array
     */
    public static int[] primeFact(int num) {
        int i = 2;
        int j = 0;
        int[] arr = new int[1280];

        // Adds prime factors to array of integers
        while (!isPrime(num)) {
            if (num % i == 0) { // If i is factor of num, adds to string and continues
                arr[j] = i;
                num /= i;
                j++;

            } else { // Increments i by 1 if not a factor of num
                i++;
            }
        }

        // Adding final value of num to the array, which is prime
        arr[j] = num;

        // Returns another array of only the filled part of the original array
        return Arrays.copyOfRange(arr, 0, j + 1);
    }

    public static void primeFactPrinter(Scanner in) {
        boolean run = true;
        int num = 0;

        // Get positive input from the user
        while (run) {
            num = intInput(in, "Input a number");

            if (num <= 1) {
                System.out.println("Input an integer greater than 1");
            } else {
                run = false;
            }
        }

        int[] arr = primeFact(num);

        System.out.println();

        // Printing out the values in the array
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + " x ");
        }

        System.out.print(arr[arr.length - 1]);
    }

    /**
     * Converts temperatures between Celsius and Fahrenheit
     * @param in Scanner for input from System.in
     */
    public static void tempChanger(Scanner in) {
        boolean run = true;

        while (run) {
            System.out.println("\nWelcome to the temperature changer!\n0. Exit\n" +
                               "1. Fahrenheit to Celsius\n2. Celsius to Fahrenheit");
            int option = intInput(in, "Input program to run:");

            if (option == 1) { // Fahrenheit to Celsius
                int temp = intInput(in, "Input temperature to convert");
                System.out.println(((double) temp - 32) * 5 / 9);
            } else if (option == 2) { // Celsius to Fahrenheit
                int temp = intInput(in, "Input temperature to convert");
                System.out.println(((double) temp) * 9 / 5 + 32);
            } else {
                run = false;
            }
        }
    }

    /**
     * Prints a rectangle of specified character and dimensions
     * @param in Scanner to read input
     */
    public static void rectangles(Scanner in) {
        boolean run = true;
        int length = 0;
        int height = 0;
        char pchar = ' ';

        // Getting height and length of rectangle
        while (run) {
            height = intInput(in, "Input the height of the rectangle");
            length = intInput(in, "Input the length of the rectangle");

            if (height <= 0 || length <= 0) {
                System.out.println("Input integers greater than 0");
            } else {
                run = false;
            }
        }

        run = true;

        // Getting char to print rectangle using
        while (run) {
            System.out.println("Input a character to print as the rectangle");

            while (!in.hasNext()) {
                System.out.println("Invalid input");
                in.next();
            }

            pchar = in.next().charAt(0);

            run = false;

        }

        // Drawing the rectangle
        drawRect(length, height, pchar);
    }

    /**
     * Draws rectangle using specified characters and dimensions
     * @param len Length of rectangle
     * @param hei Height or rectangle
     * @param pchar Character to print rectangle with
     */
    public static void drawRect(int len, int hei, char pchar) {
        for (int i = 0; i < hei; i ++) {
            System.out.println();

            for (int j = 0; j < len; j++) {
                System.out.print(pchar);
            }
        }

        System.out.println();
    }

    /**
     * Prints reverse of a number
     * @param in Scanner for getting input
     */
    public static void reverser(Scanner in) {
        int num = intInput(in, "Input an integer");
        String str = Integer.toString(num);

        for (int i = str.length() - 1; i >= 0; i--) {
            System.out.print(str.charAt(i));
        }

        System.out.println();
    }

    /**
     * Prints out perfect numbers less than 1000
     */
    public static void perfectNums() {
        int sum;

        for (int i = 2; i <= 1000; i++) {
            sum = 0;

            for (int j = 1; j <= i / 2; j++) {
                if (i % j == 0) {
                    sum += j;
                }
            }

            if (i == sum) {
                System.out.println(i);
            }
        }
    }

    /**
     * Main method of file
     * @param args Command line arguments
     */
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        boolean run = true;
        int val;

        // Main run loop
        while (run) {
            System.out.println("\nOptions:\n0. Quit Program\n1. Smallest Number\n2. Prime Time\n3. Prime Factorization" +
                    "\n4. Temp Changer\n5. More Rectangles!\n6. Reverser\n7. Perfect Numbers");

            val = intInput(in, "Input program to run (0-8):");

            if (val == 1) {
                smallestNum(in);
            } else if (val == 2) {
                int n = intInput(in, "Input a number");
                if (isPrime(n)) {
                    System.out.println(n + " is prime");
                } else {
                    System.out.println(n + " is not prime");
                }
            } else if (val == 3) {
                primeFactPrinter(in);
            } else if (val == 4) {
                tempChanger(in);
            } else if (val == 5) {
                rectangles(in);
            } else if (val == 6) {
                reverser(in);
            } else if (val == 7) {
                perfectNums();
            } else if (val == 0) {
                run = false;
            } else {
                System.out.println("Invalid input");
            }
        }
    }
}
