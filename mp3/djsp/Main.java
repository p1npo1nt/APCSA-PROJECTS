// Main.java
package djsp;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        List<String> lines = new ArrayList<>();
        
        // Read input lines
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            
            if (line.length() == 1) {
                // This is the starting vertex character
                char startVertex = line.charAt(0);
                
                // Process the graph
                int n = lines.size();
                int[][] adj = new int[n][n];
                
                // parse adjacency matrix
                for (int i = 0; i < n; i++) {
                    String[] weights = lines.get(i).split(" ");
                    for (int j = 0; j < n; j++) {
                        adj[i][j] = Integer.parseInt(weights[j]);
                    }
                }
                
                // Create graph
                Graph g = new Graph(adj);
                
                // Create starting vertex with weight 0
                Vertex start = new Vertex('0', startVertex);
                
                shortestPath sp = new shortestPath(g, start);
                
                // Run Dijkstra's algorithm
                System.out.println("Shortest path from vertex " + startVertex + ":");
                
                Iterator<Character> iter = sp.iterator();
                while (iter.hasNext()) {
                    char vertex = iter.next();
                    System.out.print(vertex + " ");
                }
                System.out.println();
                
                // Print distances for verification
                int[] distances = sp.getDistances();
                System.out.println("\nDistances from vertex " + startVertex + ":");
                for (int i = 0; i < g.getSize(); i++) {
                    char vertex = g.intToChar(i);
                    String distStr = (distances[i] == Integer.MAX_VALUE) ? "∞" : String.valueOf(distances[i]);
                    System.out.println(vertex + ": " + distStr);
                }
                
                break;
            }
            lines.add(line);
        }
        
        scanner.close();
    }
}