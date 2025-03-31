package djsp;

public class myHeap<E extends Comparable<E>> {
    // min heap
    // node at index i, parent at (i-1)/2, left child at 2i+1, right child at 2i+2
    private E[] h;
    private int cap; // max size
    private int size; // current size

    @SuppressWarnings("unchecked")
    public myHeap(int cap) {
        this.cap = cap;
        h = (E[]) new Comparable[cap];
        this.size = 0;
    }


    public void push(E s) {
        if (isFull()) {throw new IllegalArgumentException("Heap is full");}
        h[size] = s; // add at end of heap 
        heapifyUp(size); // bubble up to put s in proper position
        size+=1;
    }

    public E uproot() {
        // get and remove root (index=0) i.e. min element of heap
        // replace min w/ last element, heapify down 
        if (isEmpty()) {throw new IllegalArgumentException("Heap is empty");}
        E t = h[0]; // store root
        h[0] = h[size-1];
        size-=1;
        heapifyDown(0); // restore heap property
        return t;
    }

    public void heapifyUp(int n) {
        // n, the index of the node we are trying to swap up
        // move the element to its correct position
        // compare with parent and swap if smaller, repeat until in correct position

        while(n>0) {
            if (h[parent(n)].compareTo(h[n]) <= 0) {
                // heap property already satisfied
                break;
            } else {
                // if h[n] greater than parent, swap
                E t = h[parent(n)];
                h[parent(n)] = h[n];
                h[n] = t;
                n = parent(n);
            }
        }
    }

    public void heapifyDown(int n) {
        // Move element down to restore heap property
        int smallest;
        
        while(true) {
            int leftChild = Lchild(n);
            int rightChild = Rchild(n);
            smallest = n;
            
            // check if left child exists and is smaller than current smallest
            if (leftChild < size && h[leftChild].compareTo(h[smallest]) < 0) {smallest = leftChild;}
            
            // check if right child exists and is smaller than current smallest
            if (rightChild < size && h[rightChild].compareTo(h[smallest]) < 0) {smallest = rightChild;}
            
            // if smallest is still n, heap property is satisfied
            if (smallest==n) {break;}
            
            E temp = h[n];
            h[n] = h[smallest];
            h[smallest] = temp;
            
            // continue from the smallest position
            n = smallest;
        }
    }

    public E peek() {
        if(!isEmpty()) {return h[0];} 
        else {throw new IllegalArgumentException("Can't peek, heap is empty");}
    } // return min

    public int parent(int n) {return (n-1)/2;}
    public int Lchild(int n) {return 2*n+1;}
    public int Rchild(int n) {return 2*n+2;}

    public int getSize() {return size;}
    
    public boolean isEmpty() {return size==0;}

    public boolean isFull() {return size==cap;}

    public void print() {
        if (isEmpty()) {
            System.out.println("Heap is empty");
            return;
        }
        
        System.out.println("heap contents (size=" + size + "):");
        
        System.out.print("input array: ");
        for (int i = 0; i < size; i++) {
            System.out.print(h[i] + " ");
        }
        System.out.println();
        
        int level = 0;
        int levelSize = 1;
        int printed = 0;
        
        while (printed < size) {
            System.out.print("Level " + level + ": ");
            
            for (int i = 0; i < levelSize && printed < size; i++) {
                System.out.print(h[printed].toString() + " ");
                printed++;
            }
            
            System.out.println();
            level++;
            levelSize *= 2;  // each level has twice as many nodes as the previous
        }
    }
}