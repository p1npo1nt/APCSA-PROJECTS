package djsp;

public class heapTest {
    public static void main(String[] args) {
        myHeap<Vertex> heap = new myHeap<>(8);

        heap.push(new Vertex('3', 'X'));
        heap.push(new Vertex('1', 'Y'));
        heap.push(new Vertex('5', 'Z'));

        heap.print();
    }
}
