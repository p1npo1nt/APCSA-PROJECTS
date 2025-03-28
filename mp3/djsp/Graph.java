package djsp;

import java.util.*;

public class Graph implements Iterable<Character> {
    private final boolean[][] adj;

    public Graph (boolean[][] adj) {
        // constructor no. 1
        this.adj = adj;
    }

    public Graph (Graph graph) {
        // constructor no. 2
        int dim = graph.getSize();
        this.adj = new boolean[dim][dim];
        boolean[][] adjPrevious = graph.getMatrix();

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                this.adj[i][j] = adjPrevious[i][j];
            }
        }
    }

    public boolean[][] getMatrix() {
        return adj;
    }

    public boolean isAdjacent(char A, char B) {
        return adj[charToInt(A)][charToInt(B)];
    }

    public ArrayList<Character> allAdj(char A) {
        // all vertices adjacent to A
        ArrayList<Character> adjList = new ArrayList<>();

        int k = charToInt(A);

        for (int i = 0; i < getSize(); i++) {
            if (adj[k][i]) {
                adjList.add(intToChar(i));
            }
        }

        return adjList;
    }

    public int getSize() {
        // return size of the graph (No. of vertices)
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
