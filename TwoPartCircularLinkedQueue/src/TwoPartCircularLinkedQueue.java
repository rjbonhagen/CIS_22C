public final class TwoPartCircularLinkedQueue<T> implements QueueInterface<T> {
	
	private Node queueNode; // References first node in queue
	private Node freeNode; // References node after back of queue

	public TwoPartCircularLinkedQueue() {
		freeNode = new Node(null, null);
		freeNode.setNextNode(freeNode);
		queueNode = freeNode;
	} // end default constructor
	
	@Override
	public void enqueue(T newEntry) {
		freeNode.setData(newEntry);
		
		if(isChainFull()) {
			Node newNode = new Node(null, freeNode.getNextNode());
			freeNode.setNextNode(newNode);
		}
		freeNode = freeNode.getNextNode();
		
	}
	
	@Override
	public T dequeue() {
		T front = getFront();
		assert !isEmpty();
		queueNode.setData(null);
		queueNode = queueNode.getNextNode();
		
		return front;
	
	}

	@Override
	public void clear() {
		while (!isEmpty())
			dequeue();
	} // end clear

	@Override
	public T getFront() {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
		else {
			return queueNode.getData();
		}
	}

	@Override
	public boolean isEmpty() { return queueNode == freeNode; }
	
	private boolean isChainFull() { return queueNode == freeNode.getNextNode(); }

	
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

		private T getData() {
			return data;
		}

		private void setData(T newData) {
			data = newData;
		}

		private Node getNextNode() {
			return next;
		}

		private void setNextNode(Node nextNode) {
			next = nextNode;
		}
	}
	
} // end TwoPartCircularLinkedQueue