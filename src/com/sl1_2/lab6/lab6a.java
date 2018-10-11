package com.sl1_2.lab6;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Oct. 11, 2018
*/

import java.util.Scanner;

public class lab6a {

    public static final String PRIME_TIME = "IT'S PRIME TIME";

    public static long[] fillPrimeArray(long[] primeArray) {
        boolean isPrime;
        int currentNum = 3;
        primeArray[0] = 2;

        for (int i = 1; i < primeArray.length; i++) {
            isPrime = true;

            for (int j = 0; j < i; j++) {
                if (currentNum % primeArray[j] == 0) {
                    isPrime = false;
                }
            }

            if (isPrime) {
                primeArray[i] = currentNum;
            } else {
                i--;
                currentNum += 2;
                continue;
            }
        }

        return primeArray;
    }

    public static void askForPrime(long[] primeArray) {
        Scanner in = new Scanner(System.in);
        int primeInput = 0;

        while (true) {
            System.out.println("Please input the nth prime you want between 1 and 1000. Input -1 to quit.");
            try {
                primeInput = in.nextInt();
                if (primeInput < 1 || primeInput > 1000) {
                    if (primeInput == -1) {
                        System.out.println("Bye!");
                        in.close();
                        break;
                    }
                    System.out.println("Please input a different number.\n");
                    continue;
                }
                System.out.println(primeArray[primeInput - 1] + "\n");
            } catch (Exception e) {
                System.out.println("Something went wrong. Please try again.");
                in.next();
                continue;
            }
        }
    }

    public static void main (String args[]) {
        long[] primeArray = new long[1000];
        primeArray = fillPrimeArray(primeArray);
        askForPrime(primeArray);
    }
}
