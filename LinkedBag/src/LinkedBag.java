
public final class LinkedBag<T> implements BagInterface<T>{
	private Node firstNode;
	private int numberOfEntries;
	
	public LinkedBag() {
		firstNode = null;
		numberOfEntries = 0;
	}
	
	public int getCurrentSize() {return numberOfEntries;}

	public boolean isEmpty() { return (numberOfEntries == 0); }

	public boolean add(T newEntry) {
		Node newNode = new Node(newEntry);
		newNode.next = firstNode;
		
		firstNode = newNode;
		numberOfEntries++;
		
		return true;
	}

	public T remove() {
		if (firstNode != null) {
			Node removed = firstNode;
			firstNode = firstNode.next;
			numberOfEntries--;
			return removed.data;
		}
		else return null;
	}

	private Node getReferenceTo(T anEntry) {
		boolean found = false;
		Node currNode = firstNode;
		while (!found && (currNode != null)) {
			if (anEntry.equals(currNode.data) ) {
				found = true;
			}
			else {
				currNode = currNode.next;
			}
		}
		return currNode;
	}

	public boolean remove(T anEntry) {
		boolean removed = false;
		int i = 1;
		Node node = getReferenceTo(anEntry);
		if (node != null) {
			node.data = firstNode.data;
			firstNode = firstNode.next;
			numberOfEntries--;
			removed = true;		
		}
		
		return removed;
	}

	public void clear() {
		while (!isEmpty()) {
			remove();
		}	
	}

	public int getFrequencyOf(T anEntry) {
		int freq = 0;
		int loop = 0;
		Node currNode = firstNode;
		
		while ((loop < numberOfEntries) && (currNode != null)) { 	
			if (anEntry.equals(currNode.data)) {
				freq++;
			}
			loop++;
			currNode = currNode.next;
		}
		return freq;
	}

	public boolean contains(T anEntry) {
		boolean found = false;
		Node currNode = firstNode;
		while (!found && (currNode != null)) {
			if (anEntry.equals(currNode.data)) {
				found = true;
			}
			else {
				currNode = currNode.next;
			}
		}
		return found;
	}

	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numberOfEntries];
		
		int index = 0;
		Node currNode = firstNode;
		while((index < numberOfEntries) && (currNode != null)) {
			result[index] = currNode.data;
			index++;
			currNode = currNode.next;
		}
		return result;
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
	    
	    private Node getNext() { return next; }
	    private void setNext(Node nextNode) { next = nextNode; }
	}
}
