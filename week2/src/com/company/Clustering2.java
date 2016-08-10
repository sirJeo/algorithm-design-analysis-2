/**
 * Question 2:
 *
 * In this question your task is again to run the clustering algorithm from lecture, but on a
 * MUCH bigger graph. So big, in fact, that the distances (i.e., edge costs) are only defined
 * implicitly, rather than being provided as an explicit list.
 *
 * The data set is here. The format is:
 * [# of nodes] [# of bits for each node's label]
 * [first bit of node 1] ... [last bit of node 1]
 * [first bit of node 2] ... [last bit of node 2]
 * ...
 * For example, the third line of the file "0 1 1 0 0 1 1 0 0 1 0 1 1 1 1 1 1 0 1 0 1 1 0 1"
 * denotes the 24 bits associated with node #2. The distance between two nodes u and v in this
 * problem is defined as the Hamming distance--- the number of differing bits --- between the
 * two nodes' labels. For example, the Hamming distance between the 24-bit label of node #2
 * above and the label "0 1 0 0 0 1 0 0 0 1 0 1 1 1 1 1 1 0 1 0 0 1 0 1" is 3 (since they differ
 * in the 3rd, 7th, and 21st bits). The question is: what is the largest value of k such that
 * there is a k-clustering with spacing at least 3? That is, how many clusters are needed to
 * ensure that no pair of nodes with all but 2 bits in common get split into different clusters?
 * NOTE: The graph implicitly defined by the data file is so big that you probably can't write
 * it out explicitly, let alone sort the edges by cost. So you will have to be a little creative
 * to complete this part of the question.
 * For example, is there some way you can identify the smallest distances without explicitly
 * looking at every pair of nodes?
 */

package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map;

public class Clustering2 {
    private Integer nodeAmount = null;
    private Integer bits = null;
    private Integer clusters = 0;

    private TreeMap<Integer, Vertex> vertexList = new TreeMap<>();

    private class Vertex {

        private Integer name = null;
        private Vertex parent = null;

        public Vertex(int name) {
            this.name = name;
        }

        public Integer getName() {
            return name;
        }

        public void setParent(Vertex parent) {
            this.parent = parent;
        }

        public Vertex getParent() {
            if (parent == null) {
                return this;
            } else {
                setParent(parent.getParent());
                return parent;
            }
        }

        public void join(Vertex v) {
            v.setParent(getParent());
        }
    }

    public Clustering2(String filename) {
        try {
            Scanner scan = new Scanner(new File(filename));
            while (scan.hasNextLine()) {
                if (nodeAmount == null) {
                    String[] items = scan.nextLine().split(" ");
                    nodeAmount = Integer.parseInt(items[0]);
                    bits = Integer.parseInt(items[1]);
                } else {
                    prepareLine(scan.nextLine());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void prepareLine(String line) {
        Integer number = Integer.parseInt(line.replace(" ", ""), 2);
        Vertex newVertex = new Vertex(number);
        boolean inCluster = false;

        System.out.println("=============================: " + number);
        for (Map.Entry<Integer, Vertex> entry : vertexList.entrySet()) {
            Vertex v = entry.getValue();

//            System.out.println(Integer.toBinaryString(v.getName()));
//            System.out.println(Integer.toBinaryString(number));

            if(Integer.bitCount(v.getName() ^ number) < 3) {
                System.out.println(Integer.bitCount(v.getName() ^ number));
                v.join(newVertex);
                inCluster = true;
            }
        }
        if (!inCluster) {
            clusters++;
            System.out.println("Clusters: " + clusters);
        }

        vertexList.put(vertexList.size(), newVertex);
    }

    public Integer calculate() {
        return clusters;
    }
}