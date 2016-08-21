/**
 * Question 1:
 * In this programming problem and the next you'll code up the knapsack algorithm from lecture.
 * This file describes a knapsack instance, and it has the following format:
 * [knapsack_size][number_of_items]
 * [value_1] [weight_1]
 * [value_2] [weight_2]
 * ...
 * For example, the third line of the file is "50074 659", indicating that the second item has value 50074 and size 659, respectively.
 * You can assume that all numbers are positive. You should assume that item weights and the knapsack capacity are integers.
 * In the box below, type in the value of the optimal solution.
 *
 * Question 2:
 * This instance is so big that the straightforward iterative implemetation uses an infeasible amount of time and space.
 * So you will have to be creative to compute an optimal solution. One idea is to go back to a recursive implementation,
 * solving subproblems --- and, of course, caching the results to avoid redundant work --- only on an "as needed" basis.
 * Also, be sure to think about appropriate data structures for storing and looking up solutions to subproblems.
 */
package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Knapsack {

    private Integer size = null;
//    private Integer items = null;
    private Integer[][] matrix;

    public Knapsack(String filename) {
        try {
            Scanner scan = new Scanner(new File(filename));
            while (scan.hasNextLine()) {
                if (size == null) {
                    String line[] = scan.nextLine().split(" ");
                    size = Integer.parseInt(line[0]);
//                    items = Integer.parseInt(line[1]);
                    matrix = new Integer[2][size];
                    for (int i = 0; i < size; i++) {
                        matrix[0][i] = 0;
                    }
                } else {
                    prepareLine(scan.nextLine());
                }
            }
            System.out.println(matrix[0][size-1]);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void prepareLine(String line) {
        String splittedLine[] = line.split(" ");
        Integer value = Integer.parseInt(splittedLine[0]);
        Integer weight = Integer.parseInt(splittedLine[1]);

        for (int i = 0; i < size; i++) {
            if (weight > i) {
                matrix[1][i] = matrix[0][i];
            } else {
                matrix[1][i] = Math.max(matrix[0][i], (matrix[0][i-weight] + value));
            }

        }
        for(int i = 0; i < size; i++) {
            matrix[0][i] = matrix[1][i];
        }
    }
}
