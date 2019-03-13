package stacks;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/*******************************************
 * MyStack Class
 * 
 * 	Implements a Stack using single linked list
 * 	Tests insertion and deletion of values to stack.
 * 	Displays stack values.
 *  
 * @author markgroves
 * @version 10/06/18
 * CMSC 256 Fall 18 
 * Project 2
 * 
 *******************************************/

public class MyStack<E> implements StackInterface<E> {

	// instance variables
	private MyNode head;
	private int size;

	//____________________________________________________________________________________
	//	Main method
	//	
	// Initialize a Stack with Integer type @param
	//
	// Pushes various integers into stack and pops some out
	//		then displays remaining values.
	// 
	//____________________________________________________________________________________
	public static void main(String[] args) throws NullPointerException{

		MyStack <Integer> st = new MyStack<Integer>();
		st.push(20);
		st.push(50);
		st.push(80);
		st.push(40);
		st.push(60);
		st.push(75);
		System.out.println("Element removed from LinkedList: "+st.pop());
		System.out.println("Element removed from LinkedList: "+st.pop());

		if (st.peek() != null) {
			st.display();
		}
	}
	
	/*******************************************
	 * Node (Inner) Class
	 * 
	 * 	Determines node values for Stack and next node values
	 * 		
	 * @author markgroves
	 * @version 10/06/18
	 * CMSC 256 Fall 18 
	 * Project 2
	 *******************************************/
	class MyNode   
	{
		// instance variables
		E value;
		MyNode next;

		public MyNode()
		{
			next = null;
			value = null;
		} 
	}

	// default constructor
	public MyStack()
	{

	}

	/** Adds a new entry to the top of this stack.
    @param newEntry  an object to be added to the stack */
	@Override
	public void push(E newEntry) {
		MyNode e = new MyNode();
		e.next = head; 
		e.value = newEntry;
		head = e;   
		size++;	
	}

	/** Removes and returns this stack’s top entry.
    @return either the object at the top of the stack or, if the
            stack is empty before the operation, null */
	@Override
	public E pop() throws EmptyStackException{
		E item = head.value;
		head = head.next;
		size--;
		return item;
	}

	/** Retrieves this stack’s top entry.
    @return either the object at the top of the stack or null if
            the stack is empty */
	@Override
	public E peek() {
		return head.value;
	}

	/** Retrieves this stack’s top entry.
    @return either the object at the top of the stack or null if
            the stack is empty */
	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	/** Removes all entries from this stack */
	@Override
	public void clear() 


	{
		head = null; 
	}

	/** returns value at head of stack */
	public E head(){
		if (isEmpty()) {
			throw new NoSuchElementException("Stack underflow");
		}
		return head.value;
	}

	/** returns integer size of stack */
	public int size()

	{
		return size;
	}

	/** displays values of stack with top and bottom labeled*/
	public void display() throws NullPointerException{
		
		  System.out.printf("Top of stack: %s\n", head());
		    for (MyNode current = head.next; current != null; current = current.next) {
		    		if (current.next == null) {
		    			System.out.printf("Bottom Stack: %s\n", current.value);
		    		} else {
		    			System.out.printf("%16s\n", current.value);
		    		}
		    	}	}

}
