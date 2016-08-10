/**
 * Question 1:
 * In this programming problem and the next you'll code up the clustering algorithm from lecture
 * for computing a max-spacing k-clustering.
 * File has the following format:
 * [number_of_nodes]
 * [edge 1 node 1] [edge 1 node 2] [edge 1 cost]
 * [edge 2 node 1] [edge 2 node 2] [edge 2 cost]
 * ...
 * There is one edge (i,j) for each choice of 1?i<j?n, where n is the number of nodes.
 * For example, the third line of the file is "1 3 5250", indicating that the distance between nodes
 * 1 and 3 (equivalently, the cost of the edge (1,3)) is 5250.
 * You can assume that distances are positive, but you should NOT assume that they are distinct.
 * Your task in this problem is to run the clustering algorithm from lecture on this data set,
 * where the target number k of clusters is set to 4.
 * What is the maximum spacing of a 4-clustering?
 */
package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Clustering1 {
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

    public Clustering1(String filename) {
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
        int vTitle0 = Integer.parseInt(items[0]);
        int vTitle1 = Integer.parseInt(items[1]);
        int cost = Integer.parseInt(items[2]);

        Vertex v0 = vertexList.get(vTitle0);
        Vertex v1 = vertexList.get(vTitle1);

        if (v0 == null) {
            v0 = new Vertex(vTitle0);
            vertexList.put(vTitle0, v0);
        }

        if (v1 == null) {
            v1 = new Vertex(vTitle1);
            vertexList.put(vTitle1, v1);
        }

        edges.add(new Edge(v0, v1, cost));
    }

    public Integer calculate() {
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

        return null;
    }
}