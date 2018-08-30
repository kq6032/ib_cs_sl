/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Aug. 29, 2018
*/

import java.util.Scanner;

class bmiCalc {
    public static void main(String Args[]) {

        float weight = 0;
        float height = 0;
        float bmi = 0;

        Scanner input = new Scanner(System.in);

        System.out.println("Please input your weight in pounds:");
        weight = input.nextFloat();

        System.out.println("Please input your height in inches:");
        height = input.nextFloat();

        bmi = (weight * 703) / (height * height);

        System.out.println("Your BMI is " + bmi);

        if (bmi < 18.5) {
            System.out.println("You are underweight.");
        } else if (bmi >= 18.5 && bmi < 25) {
            System.out.println("You are normal.");
        } else if (bmi >= 25 && bmi <= 30) {
            System.out.println("You are overweight.");
        } else {
            System.out.println("You are obese.");
        }
    }
}
