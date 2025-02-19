
public final class LinkedStack<T> implements StackInterface<T> {
	private Node topNode;

	public LinkedStack() {
		topNode = null;
	}
	@Override
	public void push(T newEntry) {
		Node newNode = new Node(newEntry, topNode);
		topNode = newNode;
	}

	@Override
	public T pop() {
		T top = peek();
		assert topNode != null;
		if (topNode != null)
			topNode = topNode.getNextNode();
		return top;
	}

	@Override
	public T peek() {
		if (topNode != null)
			return topNode.data;
		else throw new EmptyStackException("peek failed stack is empty");
	}

	@Override
	public boolean isEmpty() {
		return topNode == null;
	}

	@Override
	public void clear() {
		topNode = null;
		
	}
	
	private class Node {
	    private T data;
	    private Node next;

	    private Node(T data) {
	        this(data, null);
	    }	

	    private Node(T dataPortion, Node nextNode) {
	        data = dataPortion;
	        next = nextNode;
	    }
	    
	    private T getData() { return data; }
	    private void setData(T newData) { data = newData; }
	    
	    private Node getNextNode() { return next; }
	    private void setNextNode(Node nextNode) { next = nextNode; }
	}
	
}
