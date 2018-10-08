package com.sl1_2.utility;

import java.util.Scanner;

public class utilities {

    public static int getInt(String prompt) {
        int integer = 0;
        Scanner in = new Scanner(System.in);

        while (true) {
            try {
                System.out.println(prompt);
                integer = in.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("That isn't a valid input. Please try again.");
                in.next();
            }
        }
        return integer;
    }


}
