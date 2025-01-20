public class CustomStack<T> implements StackInterface<T> {
    //this class implements a generic stack data structure using a singly linked list
    Node<T> top;
    int size;
    //the node class for the objects of the stack
    public class Node<T> {
        T data;
        Node<T> next;
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    //constructor (for an empty stack)
    public CustomStack() {
        this.top = null;
        this.size = 0;
    }
    @Override
    //adds an element to the stack
    //O(1)
    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = top;
        top = newNode;
        size++;
    }
    @Override
    //removes the element on top and returns it
    //O(1)
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }
    @Override
    //returns the element on top
    //O(1)
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return top.data;
    }
    @Override
    //O(1)
    public boolean isEmpty() {
        return top == null;
    }
    @Override
    //O(1)
    public int size() {
        return size;
    }
}
