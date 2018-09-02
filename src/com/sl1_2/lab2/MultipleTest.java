package com.sl1_2.lab2;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Aug. 29, 2018
*/

import java.util.Scanner;

class MultipleTest {
    public static void main(String Args[]) {

        int integer1 = 0;
        int integer2 = 0;

        Scanner input = new Scanner(System.in);

        System.out.println("Please input your first integer");
        integer1 = input.nextInt();

        System.out.println("Please input your second integer");
        integer2 = input.nextInt();

        if ((integer1 % integer2) == 0) {
            System.out.println("Your first integer is a multiple of your second integer.");
        } else {
            System.out.println("Your first integer is not a multiple of your second integer.");
        }
    }
}
