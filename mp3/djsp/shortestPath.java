// shortestPath.java
package djsp;

import java.util.*;

public class shortestPath extends Graph {
    private Vertex start;
    private int[] distances;
    private char[] predecessors;
    private boolean algorithmRun = false;

    public shortestPath(int[][] adj, Vertex start) {
        super(adj);
        this.start = start;
        this.distances = new int[getSize()];
        this.predecessors = new char[getSize()];
        
        //initialize distances to infinity and predecessors to undefined
        for (int i = 0; i < getSize(); i++) {
            distances[i] = Integer.MAX_VALUE;
            predecessors[i] = '?'; //using '?' to represent undefined
        }
    }
    
    public shortestPath(Graph graph, Vertex start) {
        super(graph);
        this.start = start;
        this.distances = new int[getSize()];
        this.predecessors = new char[getSize()];
        
        //initialize distances to infinity and predecessors to undefined
        for (int i = 0; i < getSize(); i++) {
            distances[i] = Integer.MAX_VALUE;
            predecessors[i] = '?'; 
        }
    }

    public Iterator<Character> iterator() {
        return new shortestPathIter(this, start);
    }
    
    //get distances to all vertices (debugging)
    public int[] getDistances() {
        if (!algorithmRun) {
            //run algorithm to completion if not already done
            shortestPathIter iter = (shortestPathIter) iterator();
            while (iter.hasNext()) {
                iter.next();
            }
            this.distances = iter.getDistances();
            this.predecessors = iter.getPredecessors();
            algorithmRun = true;
        }
        return distances;
    }
    
    //get predecessor array for path reconstruction
    public char[] getPredecessors() {
        if (!algorithmRun) {
            getDistances();
        }
        return predecessors;
    }

    public static ArrayList<Character> reverseList(ArrayList<Character> list) {
        if (list == null) {
            return null;
        }
        
        ArrayList<Character> reversed = new ArrayList<>(list.size());
        
        for (int i = list.size() - 1; i >= 0; i--) {
            reversed.add(list.get(i));
        }
        
        return reversed;
    }
    
    //reconstruct path from source to destination
    public ArrayList<Character> getPathTo(char destination) {
        if (!algorithmRun) {
            getDistances(); // Ensure algorithm has run
        }
        
        //stores path
        ArrayList<Character> path = new ArrayList<>();
        char current = destination;
        
        //if no path exists
        if (distances[charToInt(destination)] == Integer.MAX_VALUE) {
            return path; //return empty path
        }
        
        //build path from destination back to source
        while (current != start.getVertex()) {
            path.add(current);
            current = predecessors[charToInt(current)];
            if (current == '?') {
                break; //no path exists
            }
        }
        path.add(start.getVertex()); //add the source vertex
        
        //reverse to get path from source to destination
        return reverseList(path);
    }

    private class shortestPathIter implements Iterator<Character> {
        private myHeap<Vertex> pq; // Priority queue (min heap)
        private int[] distances;
        private char[] predecessors;
        private boolean[] visited;
        private Graph graph;
        private List<Character> processOrder; // Vertices in order of processing
        
        public shortestPathIter(Graph graph, Vertex start) {
            this.graph = graph;
            int size = graph.getSize();
            
            this.pq = new myHeap<>(size * size); // Allow for re-adding vertices with new priorities
            this.distances = new int[size];
            this.predecessors = new char[size];
            this.visited = new boolean[size];
            this.processOrder = new ArrayList<>();
            
            // Initialize all distances to infinity and predecessors to undefined
            for (int i = 0; i < size; i++) {
                distances[i] = Integer.MAX_VALUE;
                predecessors[i] = '?'; // Using '?' to represent undefined
            }
            
            // Set start vertex distance to 0
            int startIndex = graph.charToInt(start.getVertex());
            distances[startIndex] = 0;
            
            // Add start vertex to priority queue with priority 0
            Vertex startVertex = new Vertex('0', start.getVertex());
            pq.push(startVertex);
        }

        public boolean hasNext() {
            return !pq.isEmpty();
        }

        public Character next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more vertices to process");
            }
            
            //extract vertex with minimum distance from priority queue
            Vertex current = pq.uproot();
            char currentVertex = current.getVertex();
            int currentIndex = graph.charToInt(currentVertex);
            
            //we may have multiple entries in the queue for the same vertex
            //only process a vertex if it hasn't been visited or if we've found a shorter path
            if (visited[currentIndex]) {
                //if vertex was already visited and current distance is not better,
                //return it but don't process again
                if (current.getWeight() > distances[currentIndex]) {
                    //this is an outdated entry in the queue
                    //skip by recursively calling next() again
                    return next();
                }
            }
            
            //mark as visited and add to process order
            visited[currentIndex] = true;
            processOrder.add(currentVertex);
            
            //process all neighbors of current vertex
            int[][] adj = graph.getMatrix();
            for (int i = 0; i < graph.getSize(); i++) {
                // Skip if no edge
                if (adj[currentIndex][i] <= 0) {
                    continue;
                }
                
                //calculate new distance
                int newDistance = distances[currentIndex] + adj[currentIndex][i];
                
                if (newDistance < 0) {
                    continue;
                }
                
                //update if we found a shorter path
                if (newDistance < distances[i]) {
                    distances[i] = newDistance;
                    predecessors[i] = currentVertex;
                    
                    //add to priority queue with new priorit
                    Vertex neighbor = new Vertex((char)('0' + (newDistance % 10)), graph.intToChar(i));
                    pq.push(neighbor);
                }
            }
            
            return currentVertex;
        }
        
        public int[] getDistances() {
            return distances;
        }
        
        public char[] getPredecessors() {
            return predecessors;
        }
    }
}