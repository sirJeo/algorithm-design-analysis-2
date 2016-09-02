/**
 * In this assignment you will implement one or more algorithms for the all-pairs shortest-path problem.
 * Here are data files describing three graphs:
 * The first line indicates the number of vertices and edges, respectively. Each subsequent line describes
 * an edge (the first two numbers are its tail and head, respectively) and its length (the third number).
 *
 * NOTE: some of the edge lengths are negative. NOTE: These graphs may or may not have negative-cost cycles.
 *
 * Your task is to compute the "shortest shortest path". Precisely, you must first identify which, if any,
 * of the three graphs have no negative cycles. For each such graph, you should compute all-pairs shortest
 * paths and remember the smallest one (i.e., compute minu,v∈Vd(u,v), where d(u,v) denotes the
 * shortest-path distance from u to v).
 *
 * If each of the three graphs has a negative-cost cycle, then enter "NULL" in the box below.
 * If exactly one graph has no negative-cost cycles, then enter the length of its shortest shortest path
 * in the box below. If two or more of the graphs have no negative-cost cycles, then enter the smallest
 * of the lengths of their shortest shortest paths in the box below.
 *
 * OPTIONAL: You can use whatever algorithm you like to solve this question. If you have extra time,
 * try comparing the performance of different all-pairs shortest-path algorithms!
 */

package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Apsp {
    private double result = Double.POSITIVE_INFINITY;
    private Integer vertex = null;
    private double[][][] matrix = null;

    public Apsp(String filename) {
        try {
            Scanner scan = new Scanner(new File(filename));
            while (scan.hasNextLine()) {
                if (vertex == null) {
                    String line[] = scan.nextLine().split(" ");
                    vertex = Integer.parseInt(line[0]);
                    System.out.println("Vertex Amount: " + vertex);
                    matrix = new double[vertex][vertex][2];
                    for (int i = 0; i < vertex; i++) {
                        for (int j = 0; j < vertex; j++) {
                            if(i == j) {
                                matrix[i][j][0] = 0;
                            } else {
                                matrix[i][j][0] = Double.POSITIVE_INFINITY;
                            }
                        }
                    }
                    System.out.println("Matrix created.");
                } else {
                    prepareLine(scan.nextLine());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void prepareLine(String line) {
        String items[] =line.split(" ");
        int from = Integer.parseInt(items[0]);
        int to = Integer.parseInt(items[1]);
        int weight = Integer.parseInt(items[2]);

        matrix[from-1][to-1][0] = weight;
    }

    public double calculate() throws Exception {
        result = Double.POSITIVE_INFINITY;

        for (int k = 0; k < vertex; k++) {
            for (int i = 0; i < vertex; i++) {
                for (int j = 0; j < vertex; j++) {
                    matrix[i][j][1] = Double.min(matrix[i][j][0], (matrix[i][k][0] + matrix[k][j][0]));

                    matrix[i][j][0] = matrix[i][j][1];
                    if(matrix[j][j][1] < 0) {
                        throw new Exception("Negative cycle.");
                    }
                }
            }
        }

        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                result = matrix[i][j][1] < result ? matrix[i][j][1] : result;
            }
        }
        return result;
    }
}
