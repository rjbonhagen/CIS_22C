
public class Node<T> {
    private T data;
    private Node<T> next;

    private Node(T data) {
        this(data, null);
    }	

    private Node(T dataPortion, Node nextNode) {
        data = dataPortion;
        next = nextNode;
    }
    
    private T getData() { return data; }
    private void setData(T newData) { data = newData; }
    
    private Node getNext() { return next; }
    private void setNext(Node nextNode) { next = nextNode; }
}
