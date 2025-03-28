package djsp;

public class heapTest {
    public static void main(String[] args) {
        char[] testNodes = {'A', 'D', 'C', 'F', 'B', 'E', 'H', 'G'};
        myHeap heap = new myHeap(8);

        for(char c : testNodes) {
            heap.push(c);
        }

        heap.print();
    }
}
