/**
 * Programming Assignment #1
 *
 * PA1.3:
 *
 * This file describes an undirected graph with integer edge costs. It has the format
 * [number_of_nodes] [number_of_edges]
 * [one_node_of_edge_1] [other_node_of_edge_1] [edge_1_cost]
 * [one_node_of_edge_2] [other_node_of_edge_2] [edge_2_cost]
 *
 * Your task is to run Prim's minimum spanning tree algorithm on this graph.
 * You should report the overall cost of a minimum spanning tree --- an integer,
 * which may or may not be negative --- in the box below.
 */

package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Assignment3 {

    private Integer vertexes = null;
    private Integer edges = null;

    private TreeMap<Integer, Vertex> vertexList = new TreeMap<>();

    class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return (o1.getCost() > o2.getCost()) ? 1 : -1;
        }
    }

    private class MSTree {
        private Set<Integer> vertexList = new TreeSet<>();
        private PriorityQueue<Edge> edges = new PriorityQueue<>(new EdgeComparator());
        private PriorityQueue<Edge> treeEdges = new PriorityQueue<>(new EdgeComparator());

        private Integer vSize = null;
        private long cost = 0;

        public MSTree(int sise, Vertex root) {
            vSize = sise;
            addVertex(root);
            buildTree();
        }

        public boolean addVertex(Vertex v) {
            if (!vertexList.contains(v.getName())) {
                vertexList.add(v.getName());
                for (Edge e: v.getEdges()) {
                    edges.add(e);
                }
                return true;
            } else {
                return false;
            }
        }

        public void buildTree() {
            Edge e;
            while(vertexList.size() < vSize || edges.size() < 0) {
                e = edges.poll();

                if(addVertex(e.getV0()) || addVertex(e.getV1())) {
                    treeEdges.add(e);
                    cost += e.getCost();
                }
            }
        }

        public long getCost() {
            return cost;
        }
    }

    private class Vertex {
        private PriorityQueue<Edge> edges = new PriorityQueue<>(new EdgeComparator());
        private Integer name = null;
        public Vertex(int name) {
            this.name = name;
        }

        public void addEdge(Edge e) {
            edges.add(e);
        }

        public Integer getName() {
            return name;
        }

        public PriorityQueue<Edge> getEdges() {
            return edges;
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

            v0.addEdge(this);
            v1.addEdge(this);
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
    }

    public Assignment3(String filename) {
        try {
            Scanner scan = new Scanner(new File(filename));
            while (scan.hasNextLine()) {
                if (vertexes == null) {
                    String[] sizes = scan.nextLine().split(" ");
                    vertexes = Integer.parseInt(sizes[0]);
                    edges = Integer.parseInt(sizes[1]);
                } else {
                    String line = scan.nextLine();
                    prepareLine(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void prepareLine(String line) {
        String[] job = line.split(" ");
        int vTitle0 = Integer.parseInt(job[0]);
        int vTitle1 = Integer.parseInt(job[1]);
        int cost = Integer.parseInt(job[2]);

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

        new Edge(v0, v1, cost);
    }

    public long calculate() {
        Vertex v = vertexList.get(vertexList.firstKey());
        MSTree tree = new MSTree(vertexes, v);
        return tree.getCost();
    }
}
