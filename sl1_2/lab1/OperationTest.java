/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Aug. 28, 2018
*/

import java.util.Scanner;

class OperationTest{
	public static void main(String args[]) {

		int firstNumber = 0;
		int secondNumber = 0;

		int sum = 0;
		int difference = 0;
		int product = 0;
		int quotient = 0;

		Scanner input = new Scanner(System.in);

		System.out.println("Please input a number:");
		firstNumber = input.nextInt();

		System.out.println("Please input another number:");
		secondNumber = input.nextInt();

		sum = firstNumber + secondNumber;
		difference = firstNumber - secondNumber;
		product = firstNumber * secondNumber;
		quotient = firstNumber / secondNumber;

		System.out.println("Sum: " + sum);
		System.out.println("Difference: " + difference);
		System.out.println("Product: " + product);
		System.out.println("Quotient: " + quotient);

		/*
		What happens to the quotient when dividing 9 by 5 is that
		since the quotient was declared as an int, it will only take
		in an integer, so it finds how many 5s fit into 9.
		That's why quotient prints out 1.
		*/

		input.close();
	}
}
