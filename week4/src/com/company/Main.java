

package com.company;

public class Main {

    public static void main(String[] argsm) {
        try {
            System.out.println("=====================================");
            Apsp g1 = new Apsp("g1.txt");
            System.out.println("Calculation start");
            System.out.println("G1: " + g1.calculate());
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            System.out.println("=====================================");
            Apsp g2 = new Apsp("g2.txt");
            System.out.println("Calculation start");
            System.out.println("G2: " + g2.calculate());
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            System.out.println("=====================================");
            Apsp g3 = new Apsp("g3.txt");
            System.out.println("Calculation start");
            System.out.println("G3: " + g3.calculate());

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
