package bfs;

public class myQueue {
    
    public static final int max = 1000;
    private char[] q;
    private int front; 
    private int rear;
    private int cap; // max size of the queue
    private int size; // current size of the queue
    char[] a;

    public myQueue(int cap) {
        this.cap = cap;
        this.q = new char[cap]; // creating a character array
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    // enqueue
    public void eq(char s) {
        if(size==cap) {throw new IllegalArgumentException("Queue is full");}
        q[rear] = s;
        rear = (rear + 1) % cap; // circular increment
        size+=1;
    }

    // dequeue
    public char dq() {
        if (size==0) {throw new IllegalArgumentException("Queue is empty");}
        char t = q[front];
        front = (front+1)%cap;
        size-=1;
        return t;
    }

    public boolean isEmpty() {return size==0;}

    public int getSize() {return size;}
}
