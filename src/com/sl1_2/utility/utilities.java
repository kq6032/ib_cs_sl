package com.sl1_2.utility;

import java.util.Scanner;

public class utilities {

    public static int getIntInput(String message, Scanner in) {
        int input;
        boolean run;

        run = true;
        input = 0;

        while (run) {
            System.out.println(message);

            while (!in.hasNextInt()) {
                System.out.println("That input is invalid. Input something else.");
                in.next();
            }

            input = in.nextInt();

            if (input > 0) {
                run = false;
            }
        }

        return input;
    }
}
