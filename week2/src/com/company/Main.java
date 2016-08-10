package com.company;

public class Main {

    public static void main(String[] args) {
        Clustering1 o1 = new Clustering1("clustering1.txt");
        System.out.println("PA1: " + o1.calculate());


        Clustering2 o2 = new Clustering2("clustering2.txt");
        System.out.println("PA1: " + o2.calculate());

        long startTime = System.nanoTime();

        System.out.println("Init: " + (System.nanoTime()-startTime));
        startTime = System.nanoTime();

        System.out.println("Calculate: " + (System.nanoTime()-startTime));
    }
}
