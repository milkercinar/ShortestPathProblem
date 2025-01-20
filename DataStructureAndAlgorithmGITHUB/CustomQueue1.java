public class CustomQueue<T> implements QueueInterface<T> {
    //this class implements a generic queue data structure using a singly linked list
    Node<T> front, rear;
    int size;
    //node class for the objects
    public class Node<T> {
        T data;
        Node<T> next;
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    //constructor
    public CustomQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }
    @Override
    //adding an object to the rear of the queue
    //O(1)
    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        if (rear != null) {
            rear.next = newNode;
        }
        rear = newNode;
        if (front == null) {
            front = rear;
        }
        size++;
    }
    @Override
    //removing an object from the front of the queue and returning it
    //O(1)
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        T data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return data;
    }
    @Override
    //O(1)
    public boolean isEmpty() {
        return front == null;
    }
    @Override
    //O(1)
    public int size() {
        return size;
    }
}
