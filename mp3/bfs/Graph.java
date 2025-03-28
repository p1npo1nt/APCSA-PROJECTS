package bfs;

public class myQueue {
    
    public static final int max = 1000;
    private int front; // index of front
    private int size; // amount of elements in the queue
    private int cap; // max size of the queue
    char[] a;

    public myQueue() {
        a = new char[max]; // creating a character array
        this.front = 0;
        this.size = 0;
        this.cap = max;
    }

    // enqueue
    public void eq(char s) {
        if(size==cap) {throw new IllegalArgumentException("Queue is full");}
        a[(front+size)%cap] = s; // calculate rear index and insert s there
        size+=1;
    }

    // dequeue
    public char dq() {
        if (size==0) {throw new IllegalArgumentException("Queue is empty");}
        char t = a[front];
        front = (front+1)%cap;
        size-=1;
        return t;
    }

    public boolean isEmpty() {return size==0;}

    public boolean isFull() {return size==cap;}

    public int getSize() {return size;}
}
