package com.sl1_2;

import java.io.IOException;

/**
 * Tester File - All things in this file are temporary.
 */

public class Tester {

    public static void main (String args[]) throws IOException {
        String hi = "hello how are you doing";
        String subhi = hi.substring(hi.indexOf(" "), hi.length());

        System.out.println(hi);
        System.out.println(subhi);
        System.out.println(subhi.trim());
    }
}
