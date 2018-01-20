/**
 * Custom singly Linked List implementation 
 * to be used with MyStack.
 *
 * @author Alex Bledsoe, Benjamin Yuen
 * @version Jan 14, 2018
 */
public class MyLinkedList<T> {

	/** Reference to the node at the start of the list. */
	private Node myStart;
	
	/** Keeps track of how many nodes are currently in the list */
	private int mySize;
	
	/** Constructor initializes instance fields to reflect empty list. */
	public MyLinkedList() {
		myStart = null;
		mySize = 0;
	}
	
	/**
	 * Creates and adds a node to the front of the list 
	 * containing the object passed in.
	 *
	 * @param theData the object passed in.
	 */
	public void insertAtStart(final T theData) {
		final Node newNode = new Node(theData, myStart);
		myStart = newNode;
		mySize++;		
	}
	
	/**
	 * Removes and returns the node at the front of the list.
	 *
	 * @return the node at the front of the list.
	 */
	public Node removeFromFront() {
		final Node result = myStart;
		myStart = myStart.getNext();
		mySize--;
		return result;
	}
	
	/**
	 * Returns the node at the start of the list.
	 * However, this does not remove it from the list.
	 *
	 * @return the node at the start of the list.
	 */
	public Node getStart() {
		return myStart;
	}
	
	/**
	 * Returns the current size of the list.
	 *
	 * @return the current number of nodes in the list.
	 */
	public int getSize() {
		return mySize;
	}
	
	/**
	 * Returns whether or not the list currently has any nodes in it.
	 *
	 * @return true if the list is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return mySize == 0;
	}
	
	/**
	 * Node objects store the data passed to them 
	 * and reference to the next node in the list.
	 *
	 * @author Alex Bledsoe
	 * @version Jan 14, 2018
	 */
	class Node {
		
		/** The object/data inside the node. */
		private T myData;		
		
		/** 
		 * The reference to the next node in the list. 
		 * Null if this is the only node in the list.
		 */
		private Node myNext;
		
		/**
		 * Constructor initializes instance fields with data
		 * and reference to next node.
		 * 
		 * @param theData the object/data inside the node.
		 * @param theNext the reference to the next node in the list.
		 * 		  null if it's the first node.
		 */
		public Node(final T theData, final Node theNext) {
			myData = theData;
			myNext = theNext;
		}
		
		/**
		 * Returns the object/data inside the node.
		 *
		 * @return The node's data.
		 */
		public T getData() {
			return myData;
		}
		
		/**
		 * Returns the node immediately following this one.
		 *
		 * @return the next node in the list. Null if this
		 * 		   this is the only node in the list.
		 */
		public Node getNext() {
			return myNext;
		}
	}
}

