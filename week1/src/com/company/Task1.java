package com.company;

import java.io.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Scanner;
import java.util.Collections;

public class Task1 {

    private static Integer amount = null;
    private static Map<Integer, ArrayList<Integer>> tasks = new TreeMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("./week1-1.txt"));
        while(scan.hasNextLine()) {
            if (amount == null) {
                amount = Integer.parseInt(scan.nextLine());
            } else {
                String line = scan.nextLine();
                String[] job = line.split(" ");
                ArrayList<Integer> task = tasks.get(Integer.parseInt(job[0]));

                if (task == null) {
                    task = new ArrayList<>();
                    tasks.put(Integer.parseInt(job[0]), task);
                }
                task.add(Integer.parseInt(job[1]));

            }
        }
        calculateScadule();
    }

    private static void calculateScadule() {
        int length = 0;
        long job = 0;

        for(Map.Entry<Integer, ArrayList<Integer>> entry : tasks.entrySet()) {
            Integer key = entry.getKey();
            ArrayList<Integer> value = entry.getValue();

//            System.out.println(key + " => " + value.size());

            Collections.sort(value);

            for (Integer val: value){
                job += key * (length + val);
//                System.out.println (job + " += " + key + " * " + " ( " + length + " +  " + val + ");");
                length += val;
//                System.out.println ( "new len: " + length);
            }
        }
        System.out.println ( "Task 1: " + job);

    }


}
/*
* W - larget
* H - shorter
* */