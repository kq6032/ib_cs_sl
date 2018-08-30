/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Aug. 29, 2018
*/

import java.util.Scanner;

class gasUseCalc {
	public static void main(String args[]) {

        double startPos = 0;
        double endPos = 0;
        double initialGas = 0;
        double endGas = 0;
        double mileage = 0;

		Scanner input = new Scanner(System.in);

        System.out.println("Please input the starting position of the car:");
        startPos = input.nextDouble();

        System.out.println("Please input the ending position of the car:");
        endPos = input.nextDouble();

        System.out.println("Please input the starting gas amount of the car:");
        initialGas = input.nextDouble();

        System.out.println("Please input the ending gas amount of the car:");
        endGas = input.nextDouble();

        mileage = (endPos - startPos) / (initialGas - endGas);

        System.out.println("The mileage of the vehicle is " + mileage  + " miles.");
	}
}
