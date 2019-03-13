package stacks;

public interface StackInterface<E> {

	/** Adds a new entry to the top of this stack.
    @param newEntry  an object to be added to the stack */
	void push(E newEntry);

	/** Removes and returns this stack’s top entry.
    @return either the object at the top of the stack or, if the
            stack is empty before the operation, null */
	E pop();

	/** Retrieves this stack’s top entry.
    @return either the object at the top of the stack or null if
            the stack is empty */
	E peek();

	/** Retrieves this stack’s top entry.
    @return either the object at the top of the stack or null if
            the stack is empty */
	boolean isEmpty();

	/** Removes all entries from this stack */
	void clear();

	void display(); 

} // end StackInterface

