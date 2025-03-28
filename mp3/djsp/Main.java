package djsp;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> lines = new ArrayList<>();

        // stdinput (usage: <wn_25)
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) break;
            lines.add(line);
        }

        scanner.close();

        int n = lines.size();
        int[][] graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] weights = lines.get(i).split(" ");
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(weights[j]);
            }
        }
    }
}
