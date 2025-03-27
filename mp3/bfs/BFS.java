package bfs;

import java.util.*;

public class BFS extends Graph {
    private char start;

    public BFS(Graph graph, char start) {
        super(graph);
        this.start = start;
    }

    public BFS(boolean[][] adj, char start) {
        super(adj);
        this.start = start;
    }

    public Iterator<Character> iterator() {
        return new BFSIter((Graph) this, start);
    }

    private class BFSIter implements Iterator<Character> {
        private myQueue queue;
        private boolean[] isVisited;
        private Graph graph;
    
        private BFSIter(Graph graph, char start) {
            this.graph = graph;
            this.queue = new myQueue();
            this.isVisited = new boolean[graph.getSize()];
            
            // mark start as visited before enqueuing
            isVisited[graph.charToInt(start)] = true;
            queue.eq(start);
        }
    
        public boolean hasNext() {
            return queue.getSize() > 0; 
        }
    
        public Character next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more vertices in BFS traversal");
            }
    
            char current = queue.dq();
            
            // get all adjacent vertices
            List<Character> adjList = graph.allAdj(current);
            
            // add unvisited neighbors to the queue
            for (Character neighbor : adjList) {
                int neighborIndex = graph.charToInt(neighbor);
                if (!isVisited[neighborIndex]) {
                    isVisited[neighborIndex] = true; // Mark as visited when enqueuing
                    queue.eq(neighbor);
                }
            }
    
            return current;
        }
    }
}
