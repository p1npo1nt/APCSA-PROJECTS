package dfs;

import java.util.*;

public class DFS extends Graph {
    private char start;

    public DFS(Graph graph, char start) {
        super(graph);
        this.start = start;
    }

    public DFS(boolean[][] adj, char start) {
        super(adj);
        this.start = start;
    }

    public Iterator<Character> iterator() {
        return new DFSIter((Graph) this, start);
    }

    private class DFSIter implements Iterator<Character> {
        private  myStack<Character> stack;
        private boolean[] isVisited;
        private Graph graph;

        private DFSIter(Graph graph, char start) {
            this.graph = graph;
            this.stack = new myStack<Character>();
            this.stack.push(start);
            this.isVisited = new boolean[graph.getSize()]; // initialize isVisited
        }


        public boolean hasNext() {
            return !stack.isEmpty();
        }


        public Character next() {
            if (!hasNext()) {
                System.out.println("");
            }

            char current = stack.pop();

            // if visited => skip
            if (isVisited[graph.charToInt(current)]) {
                if (hasNext()) {
                    return next();
                }
            }

            // mark current vertex as visited
            isVisited[graph.charToInt(current)] = true;

            // get all adj vertices and push on to stack if not visited
            List<Character> adjList = graph.allAdj(current);

            // push unvisited neighbors to the stack in reverse order
            for (int i = adjList.size() - 1; i >= 0; i--) {
                char neighbor = adjList.get(i);
                if (!isVisited[graph.charToInt(neighbor)]) {
                    stack.push(neighbor);
                }
            }

            return current;
            }

        }
    }
