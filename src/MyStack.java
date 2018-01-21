/**
 * Custom Stack implementation using custom LinkedList under the hood.
 *
 * @author Alex Bledsoe, Benjamin Yuen
 * @version Jan 14, 2018
 */
public class MyStack<T> {
	
	/** Custom LinkedList that keeps track of order and data in MyStack. */
	private MyLinkedList<T> myLinkedList;

	/** Constructor initializes LinkedList field. */
	public MyStack() {
		myLinkedList = new MyLinkedList<T>();
	}
	
	/**
	 * Adds an object to the top of the stack.
	 *
	 * @param theData the object to add to the stack.
	 */
	public void push(final T theData) {
		myLinkedList.insertAtStart(theData);
	}
	
	/**
	 * Returns and removes the object on top of the stack.
	 *
	 * @return the object on top of the stack.
	 */
	public T pop() {
		return myLinkedList.removeFromFront().getData();
	}
	
	/**
	 * Returns the object on top of the stack.
	 * This does not remove it from the stack.
	 *
	 * @return the object on top of the stack.
	 */
	public T peek() {
		return myLinkedList.getStart().getData();
	}
	
	/**
	 * Returns the number of objects in the stack.
	 *
	 * @return the size of the stack.
	 */
	public int size() {
		return myLinkedList.getSize();
	}
	
	/**
	 * Returns whether or not the stack is has objects in it.
	 *
	 * @return true if the stack is empty, false otherwise. 
	 */
	public boolean isEmpty() {
		return myLinkedList.isEmpty();
	}
}