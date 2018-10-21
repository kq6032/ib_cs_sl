package com.sl1_2.lab6;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Oct. 18, 2018
*/

import java.util.Scanner;

public class lab6a {

    /**
     * fillPrimeArray
     * fills parameter array with prime numbers
     * @param primeArray passes an array by reference to be filled with primes
     */
    public static void fillPrimeArray(long[] primeArray) {
        boolean isPrime;
        int currentNum = 3;
        //i is used for looping through the array
        int i;

        primeArray[0] = 2;
        i = 1;

        //goes through the array and fills it with primes
        while(i < primeArray.length) {
            isPrime = true;

            for (int j = 0; j < i; j++) {
                if (currentNum % primeArray[j] == 0) {
                    isPrime = false;
                }
            }

            if (isPrime) {
                primeArray[i] = currentNum;
                i++;
            } else {
                currentNum += 2;
                continue;
            }
        }
    }

    /**
     * askForPrime
     * asks user for nth prime, 1 <= n <= 1000
     * @param primeArray filled array of primes
     */
    public static void askForPrime(long[] primeArray) {
        Scanner in = new Scanner(System.in);

        int primeInput;
        boolean run = true;

        while (run) {
            System.out.println("Please input the nth prime you want between 1 and 1000. Enter a number outside that to quit.");
            while (!in.hasNextInt()) {
                System.out.println("That's not a valid input!");
                in.next();
            }

            primeInput = in.nextInt();

            if (primeInput >= 1 && primeInput <= 1000) {
                System.out.println(primeArray[primeInput - 1]);
            } else {
                System.out.println("Goodbye!");
                in.close();
                run = false;
            }
        }
    }

    /**
     * main
     * main method of program
     * @param args
     */
    public static void main(String args[]) {
        long[] primeArray = new long[1000];
        fillPrimeArray(primeArray);
        askForPrime(primeArray);
    }
}
