package dfs;

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
        DFS dfs = new DFS(graph, startVertex);
        Iterator<Character> dfsiter = dfs.iterator();

        System.out.println("DFS traversal starting from vertex " + startVertex + ":");

        while (dfsiter.hasNext()) {
            System.out.print(dfsiter.next() + " ");
        }

        System.out.print("\n");
    }
}
