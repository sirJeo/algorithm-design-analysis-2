package com.company;



public class Main {

    public static void main(String[] args) {
        Assignment1 a1 = new Assignment1("./week1-1.txt");
        System.out.println("PA1: " + a1.calculate());

        Assignment2 a2 = new Assignment2("./week1-1.txt");
        System.out.println("PA2: " + a2.calculate());

//        o.readSource("./week1-3.txt", 3);
    }
}
