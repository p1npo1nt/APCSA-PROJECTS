package djsp;

import java.util.*;

public class Vertex implements Comparable<Vertex> {
    // (weight, vertex) pair class

    private ArrayList<Character> pair;

    public Vertex(char w, char v) {
        pair = new ArrayList<>();
        pair.add(w);
        pair.add(v);
    }

    public int getWeight() {
        return Character.getNumericValue(pair.get(0));
    }

    public char getVertex() {
        return pair.get(1);
    }

    public int compareTo(Vertex second) {
        return Integer.compare(this.getWeight(), second.getWeight());
    }

    public String toString() {
        return "(Weight: " + String.valueOf(getWeight()) + " Vertex: " + getVertex() + ") ";
    }
}
