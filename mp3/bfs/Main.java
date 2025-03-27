package bfs;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> lines = new ArrayList<>();

        // stdinput (usage: <graphn)
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) break;
            lines.add(line);
        }

        scanner.close();

        // retreive start vertex and then remove it from the arraylist
        char startVertex = lines.get(lines.size() - 1).charAt(0);
        lines.remove(lines.size() - 1);

        // get appropriate matrix size
        int dim = lines.size();
        boolean[][] adj = new boolean[dim][dim];

        // int to bool
        for (int i = 0; i < dim; i++) {
            String[] elements = lines.get(i).split(" ");
            for (int j = 0; j < elements.length; j++) {
                adj[i][j] = elements[j].equals("1");
            }
        }

        Graph graph = new Graph(adj);
        BFS bfs = new BFS(graph, startVertex);
        Iterator<Character> BFSiter = bfs.iterator();

        System.out.println("BFS traversal starting from vertex " + startVertex + ":");

        while (BFSiter.hasNext()) {
            System.out.print(BFSiter.next() + " ");
        }

        System.out.print("\n");
    }
}
