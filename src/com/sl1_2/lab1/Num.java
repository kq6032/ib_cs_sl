package com.sl1_2.lab1;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Aug. 28, 2018
*/

import java.io.*;
import java.util.Random;
import java.util.Scanner;

class Num {
	public static void main(String args[]) {

		int firstNumber = 5;
		int secondNumber = 17;
		int userNumber = 0;
		int sum = 0;

		Scanner in = new Scanner(System.in);

		System.out.println("Welcome to my number adding machine");

		sum = firstNumber + secondNumber;

		System.out.println("The sum of " + firstNumber + " and " + secondNumber + " is " + sum);

		System.out.println("Please enter a number:");

		userNumber = in.nextInt();
		sum += userNumber;

		System.out.println("The sum of the numbers is " + sum);

		in.close();
	}
}
