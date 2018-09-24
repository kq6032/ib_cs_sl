package com.sl1_2;

import java.util.Scanner;

public class Tester {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        /*while (true) {
            float dens1 = in.nextFloat();
            float dens2 = in.nextFloat();
            float dens3 = in.nextFloat();

            float unc1 = in.nextFloat();
            float unc2 = in.nextFloat();
            float unc3 = in.nextFloat();
            float uncTot = unc1 + unc2 + unc3;

            System.out.println((dens1 + dens2 + dens3) / 3f);
            System.out.println(uncTot);
            System.out.println(uncTot / 3f);
        }*/

        float mass;
        float vol;
        float dens;
        while (true) {
            mass = in.nextFloat();
            vol = in.nextFloat();

            dens = mass / vol;
            System.out.println(dens);
            System.out.println(((0.02f / mass) + (0.5f / vol)) * dens);
        }
    }
}
