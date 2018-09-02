/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Aug. 29, 2018
*/

import java.util.Scanner;

class CircFinder {
	public static void main(String args[]) {

		double pi = 3.14159;
		double radius = 0;
		double circumference = 0;

		Scanner input = new Scanner(System.in);

		System.out.println("Please input the radius of the circle: ");

		radius = input.nextDouble();

		circumference = 2 * pi * radius;

		System.out.println("The circumference of the circle with radius of " + radius +
							" is " + circumference);

	}
}
