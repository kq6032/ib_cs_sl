/*
Author: Kevin Qiu
Class: IB Computer Science 1-2 Period 3
Last Edited: Aug. 29, 2018
*/

import java.util.Scanner;

class EvenTest {
    public static void main(String Args[]) {

        int testNum = 0;
        Scanner input = new Scanner(System.in);

        System.out.println("Please input an integer to test");
        testNum = (input.nextInt()) % 2;

        if (testNum == 0) {
            System.out.println("Your integer is even.");
        } else {
            System.out.println("Your integer is odd.");
        }
    }
}
