package dfs;

public class myStack<E> {
    // array based stack
    
    public static final int max = 1000;
    int top;
    E[] a;

    public myStack() {
        top = -1;
        a = (E[]) new Object[max]; // creating a generic array via casting Object to E
    }

    public boolean isFull() {
        return top >= max-1;
    }

    public boolean isEmpty() {
        return top < 0;
    }

    public void push(E k) {
        if (isFull()) { throw new IllegalArgumentException("Stack overflow"); }
        else {
            top = top + 1;
            a[top] = k;
        }
    }

    public E pop() {
        if (isEmpty()) { throw new IllegalArgumentException("Stack underflow"); }
        else {
            E stored = a[top];
            top = top - 1;
            return stored; 
        }
    }

    public E peek() {
        if (isEmpty()) { System.out.println("Stack is empty"); return null; }
        else { return a[top]; }
    }

    public int getSize() {
        return top + 1; 
    }
}
