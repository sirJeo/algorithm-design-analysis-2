package com.company;

public class Main {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        Assignment3 o3 = new Assignment3("week1-3.txt");
        System.out.println("Init: " + (System.nanoTime()-startTime));
        startTime = System.nanoTime();
        System.out.println(o3.calculate());
        System.out.println("Calculate: " + (System.nanoTime()-startTime));
    }
}
