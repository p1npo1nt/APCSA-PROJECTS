package djsp;

import java.util.*;

public class shortestPath extends Graph {
    private Vertex start;

    public shortestPath(int[][] adj, Vertex start) {
        super(adj);
        this.start=start;
    }
    
    public shortestPath(Graph graph, Vertex start) {
        super(graph);
        this.start=start;
    }

    public Iterator<Character> iterator() {
        return new shortestPathIter(this, start);
    }

    private class shortestPathIter implements Iterator<Character> {
    
        private myHeap<Vertex> heap;
        private int[] distances;
        private boolean[] visited;
        private Graph graph;
        private char nextVertex;
        
        private shortestPathIter(Graph graph, Vertex start) {
            this.graph=graph;

            int size = graph.getSize();
            this.heap = new myHeap<>(size);
            this.distances = new int[size];
            this.visited = new boolean[size];
            this.visited[graph.charToInt(start.getVertex())] = true;

            // setup distances, only start vertex has distance 0, all others have "infinity" because
            // we haven't found path to them yet
            for (int k : distances) {
                System.out.println(k);
                k=Integer.MAX_VALUE;
            }
            distances[charToInt(start.getVertex())]=0;

            heap.push(start);

        }

        public boolean hasNext() {
            return !heap.isEmpty();
        }

        public Character next() {
            if (!hasNext()) {throw new IllegalArgumentException("Heap is empty");}
            return ' '; // placeholder
        }
    }
}
