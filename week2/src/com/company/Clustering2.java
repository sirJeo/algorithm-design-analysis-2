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

public class Clustering2 {
    private Integer nodeAmount = null;
    private TreeMap<Integer, Vertex> vertexList = new TreeMap<>();

    private PriorityQueue<Edge> edges = new PriorityQueue<>(new EdgeComparator());

    class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return (o1.getCost() > o2.getCost()) ? 1 : -1;
        }
    }

    private class Vertex {

        private Integer name = null;
        private Vertex parent = null;
        private Integer weight = 0;

        public Vertex(int name) {
            this.name = name;
        }

        public Integer getName() {
            return name;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) { this.weight = weight; }

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

        public Vertex join(Vertex v) {
            Vertex vp = v.getParent();
            Vertex tp = getParent();

            switch(vp.getWeight().compareTo(tp.getWeight())) {
                case -1:
                    vp.setParent(tp);
                    return vp;

                case 0:
                    vp.setWeight(vp.getWeight()+1);

                case 1:
                    tp.setParent(vp);
                    return tp;
            }
            return null;
        }
    }

    private class Edge {
        private Integer cost = null;
        private Vertex v0 = null;
        private Vertex v1 = null;

        public Edge(Vertex v0, Vertex v1, Integer cost) {
            this.v0 = v0;
            this.v1 = v1;
            this.cost = cost;
        }

        public Integer getCost() {
            return cost;
        }

        public Vertex getV0() {
            return v0;
        }

        public Vertex getV1() {
            return v1;
        }

        public Vertex collapse() {
            if(v0.getParent().getName() != v1.getParent().getName()) {
                return v0.join(v1);
            }
            return null;
        }
    }

    public Clustering2(String filename) {
        try {
            Scanner scan = new Scanner(new File(filename));
            while (scan.hasNextLine()) {
                if (nodeAmount == null) {
                    nodeAmount = Integer.parseInt(scan.nextLine());
                } else {
                    prepareLine(scan.nextLine());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void prepareLine(String line) {
        String[] items = line.split(" ");
//        int vTitle0 = Integer.parseInt(items[0]);
//        int vTitle1 = Integer.parseInt(items[1]);
//        int cost = Integer.parseInt(items[2]);
//
//        Vertex v0 = vertexList.get(vTitle0);
//        Vertex v1 = vertexList.get(vTitle1);
//
//        if (v0 == null) {
//            v0 = new Vertex(vTitle0);
//            vertexList.put(vTitle0, v0);
//        }
//
//        if (v1 == null) {
//            v1 = new Vertex(vTitle1);
//            vertexList.put(vTitle1, v1);
//        }
//
//        edges.add(new Edge(v0, v1, cost));
    }

    public Integer calculate() {
/*
        while (vertexList.size() > 4 && !edges.isEmpty()) {
            Edge edge = edges.poll();

            Vertex toRemove = edge.collapse();

            if (toRemove != null) {
                vertexList.remove(toRemove.getName(), toRemove);
            }
        }

        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            if (edge.getV0().getParent().getName() != edge.getV1().getParent().getName()) {
                return edge.getCost();
            }
        }
*/

        return null;
    }
}