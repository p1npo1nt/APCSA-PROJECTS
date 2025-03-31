package djsp;

import java.util.*;

public class Graph implements Iterable<Character> {
    private final int[][] adj;

    public Graph(int[][] adj) {
        // constructor no. 1
        int dim = adj.length;
        this.adj = new int[dim][dim];
        
        // copy the values from the input matrix
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                this.adj[i][j] = adj[i][j];
            }
        }
    }
    
    public Graph(Graph graph) {
        //constructor no. 2
        int dim = graph.getSize();
        this.adj = new int[dim][dim];
        
        //copy the values from the original graph's matrix
        int[][] origMatrix = graph.getMatrix();
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                this.adj[i][j] = origMatrix[i][j];
            }
        }
    }
    public int[][] getMatrix() {
        return adj;
    }

    public boolean isAdjacent(char A, char B) {
        return adj[charToInt(A)][charToInt(B)] > 0;
    }

    public ArrayList<Character> allAdj(char A) {
        //all vertices adjacent to A
        ArrayList<Character> adjList = new ArrayList<>();

        int k = charToInt(A);

        for (int i = 0; i < getSize(); i++) {
            if (adj[k][i] > 0) {
                adjList.add(intToChar(i));
            }
        }

        return adjList;
    }

    public int getWeight(char A, char B) {
        int weight = adj[charToInt(A)][charToInt(B)];
        if (weight>0) {
            return weight;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    public int getSize() {
        //return size of the graph (No. of vertices)
        return adj[0].length;
    }

    public int charToInt(char vertex) {
        return vertex - 'A';
    }

    public char intToChar(int index) {
        return (char) ('A' + index);
    }

    public Iterator<Character> iterator() {
        return new GraphIter(this.getSize());
    }

    private class GraphIter implements Iterator<Character> {
        private char t;
        private int size;

        private GraphIter(int size) {
            t = 'A';
            this.size = size;
        }

        public boolean hasNext() {
            return ((t - 65) < size);
        }

        public Character next() {
            char store = t;
            t = (char) (t+1);
            return store;
        }
    }
}
