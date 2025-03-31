package djsp;

public class Vertex implements Comparable<Vertex> {
    //vertex class which stores both weight and vertex name
    
    private int weight;
    private char vertex;

    public Vertex(char v) {
        this.vertex = v;
        this.weight = Integer.MAX_VALUE; //default to infinity
    }
    
    public Vertex(int weight, char v) {
        this.weight = weight;
        this.vertex = v;
    }
    
    public Vertex(char w, char v) {
        this.vertex = v;
        //try to parse the weight as a digit, default to MAX_VALUE if not a digit
        if (Character.isDigit(w)) {
            this.weight = Character.getNumericValue(w);
        } else {
            this.weight = Integer.MAX_VALUE;
        }
    }

    public int getWeight() {
        return weight;
    }

    public char getVertex() {
        return vertex;
    }
    
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int compareTo(Vertex second) {
        return Integer.compare(this.weight, second.weight);
    }

    public String toString() {
        return "(Weight: " + weight + " Vertex: " + vertex + ")";
    }
}