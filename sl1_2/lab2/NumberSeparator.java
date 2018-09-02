/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Aug. 29, 2018
*/

import java.util.Scanner;

class NumberSeparator {
    public static void main(String Args[]) {

        Scanner input = new Scanner(System.in);
        System.out.println("Please input a number to parse");
        String line = input.nextLine();

        for (int i = 0; i < line.length(); i++) {
            System.out.print(line.charAt(i) + " ");
        }
    }
}
