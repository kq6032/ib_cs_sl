package com.sl1_2.lab2;
/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Aug. 29, 2018
*/

import java.util.Scanner;

class NumberSeparator {
    public static void main(String Args[]) {

        int number = 0;
        int tenThousands = 0;
        int thousands = 0;
        int hundreds = 0;
        int tens = 0;
        int ones = 0;

        Scanner input = new Scanner(System.in);
        System.out.println("Please input a 5 digit integer to parse");
        number = input.nextInt();

        tenThousands = number / 10000;
        thousands = (number - tenThousands * 10000) / 1000;
        hundreds = (number - tenThousands * 10000 - thousands * 1000) / 100;
        tens = (number - tenThousands * 10000 - thousands * 1000 - hundreds * 100) / 10;
        ones = number - tenThousands * 10000 - thousands * 1000 - hundreds * 100 - tens * 10;

        System.out.print(tenThousands + " " + thousands + " " + hundreds + " " + tens + " " + ones);
    }
}
