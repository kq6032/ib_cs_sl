package com.sl1_2;

import java.util.Scanner;

/**
 * Tester File - All things in this file are temporary.
 */

public class Tester {

    public static void main (String args[]) {
        Scanner in = new Scanner(System.in);
        String delims = "[,\\s]+";
        String line = in.nextLine();

        String[] splitBois = line.split(delims);
        for(int i = 0; i < splitBois.length; i++) {
            System.out.println(splitBois[i]);
        }
    }
}
