/**
 * Reference-based implementation of ADT list.
 * Modify the code to implement a doubly linked list.
 * @author
 */

import java.util.Iterator;

public class ListReferenceBased<E> implements ListInterface<E>, Iterable<E>{

    /** reference to the first element of the list */
    private Node<E> head; 
    /** number of items in list */
    private int numItems;
    
    /** default constructor */
    public ListReferenceBased()
    {
    	numItems = 0;
    	head = null;
    }  // end default constructor
    
    /**
     * constructor with one default element
     * @param item in the list created
     */
    public ListReferenceBased(E item)
    {
    	numItems = 1;
    	head = new Node<E>(item);	
    	
    }//end constructor
    
    /**
     * the copy constructor
     * @param list the new copy
     */
    public ListReferenceBased(ListReferenceBased<E> list)
    {
    	numItems = list.numItems;
    	Node<E> temp = list.head;
    	head = new Node<E>(temp.getItem());
    	Node<E> lastNode = head;
    	Node<E> newNode = head;
    	while(temp.getNext() != null)
    	{
    		temp = temp.getNext();
    		newNode = new Node<E>(temp.getItem());
    		lastNode.setNext(newNode);
    		newNode.setPrev(lastNode);
    		lastNode = newNode;
    	}
    	
    }//end copy constructor
  
	/** Tests if this list has no elements.
     * @return <code>true</code> if this list has no elements;
     * <code>false</code> otherwise.
     */

    public boolean isEmpty() {
	return numItems == 0;
    }  // end isEmpty
    
    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list.
     */

    public int size() {
	return numItems;
    }  // end size
    

    /** Locates a specified node in a linked list.
     * <li> Postcondition: 
     * @param index position of the desired node. Assumes that <code>
     * 1 <= index <= numItems+1</code>
     * @return a reference to the desired Node.
     */
    private Node<E> find(int index) {
	Node<E> curr = head;
	for (int skip = 1; skip < index; skip++) {
	    curr = curr.getNext();
	} // end for
	return curr;
    } // end find

    /** get the item location at the specified position in the list 
     * @param index position of the node containing the item
     * @return the Object located at the specified index
     * @throws ListIndexOutOfBoundsException if the index is out of
     * range, i.e. when <code> index <=0 || index > size() </code>
    */ 
    public E get(int index) 
	throws ListIndexOutOfBoundsException {
	if (index >= 1 && index <= numItems) {
	    // get reference to node, then data in node
	    Node<E> curr = find(index);
	    return curr.getItem();
	    
	} 
	else {
	    throw new ListIndexOutOfBoundsException(
						    "List index out of bounds exception on get");
	} // end if
    } // end get
    
    
    
    /**
     * Inserts the specified element before curr in this
     * list. Shifts the element currently at that position (if any)
     * and any subsequent elements to the right (adds one to their
     * indices).
     * @param item element to be inserted.
     * @throws IndexOutOfBoundsException - if index is out of range
     * (index < 0 || index > size()).
     */
    public void add(int index, E item)
	throws ListIndexOutOfBoundsException 
	{
    	if(numItems == 0) {
    		head = new Node<E>(item);
    		numItems++;
       	}
    	else if(index == 0)
    	{
    		addHead(item);
    	}
    	else if(index >= (numItems+1))
    	{
    		append(item);
    	}
    	else if(index>0)	
    	{
    	Node<E> curr = find(index);
    	Node <E> temp = new Node<E>(item);
    	temp.setNext(curr);
    	temp.setPrev(curr.getPrev());
    	curr.getPrev().setNext(temp);
    	curr.setPrev(temp);
    	numItems++;
    	}
    	else
    		throw new ListIndexOutOfBoundsException( "List index out of bounds exception on add");
    	
	
    }  // end add
    
    /**
     * adds a head to the list
     * @param item element before which the head is inserted
     */
    private void addHead(E item)
    {
    	
    	Node<E> curr = head;
    	Node<E> temp = new Node<E> (item);
    	temp.setPrev(curr);
    	temp.setNext(curr.getNext());
    	curr.getNext().setPrev(temp);
    	curr.setNext(temp);
    	head = temp;
    	numItems++;
    }//end addHead

       
    /**
     * appends the specified element to the end of this list.
     * @param elt element to be added at the end of the list
     */
    public void append(E item)
    {
    	Node<E> curr = find(numItems);
    	Node<E> temp = new Node<E>(item);
    	temp.setPrev(curr);
    	curr.setNext(temp);
    	numItems++;
    }//end append
    

    /**
     * Removes the element at the specified position in this
     * list. Shifts any subsequent elements to the left (subtracts one
     * from their indices).
     * @param index the index of the element to remove
     * @throws IndexOutOfBoundsException - if index is out of range
     * (index < 0 || index > size()).
     */
    public void remove(int index) 
	throws ListIndexOutOfBoundsException {
	if (index >= 1 && index <= numItems) {
	    if (index == 1) {
		// delete the first node from the list
		head = head.getNext();
	    } 
	    else{
		Node<E> prev = find(index-1);
		// delete the node after the node that prev
		// references, save reference to node
		Node<E> curr = prev.getNext(); 
		prev.setNext(curr.getNext());
	    } // end if
	    numItems--;
	} // end if
	else {
	    throw new ListIndexOutOfBoundsException(
						    "List index out of bounds exception on remove");
	} // end if
    }   // end remove
    
  
    /**
     * Remove all the elements in this list.
     */
    public void removeAll() {
	// setting head to null causes list to be
	// unreachable and thus marked for garbage 
	// collection
	head = null;
	numItems = 0;
    } // end removeAll
    


    /**
     * delete the the specified element in this list if exists,
     * otherwise state that the item is not in the current
     * list. Shifts any subsequent elements to the left (subtracts one
     * from their indices).
     * @param elt the element, if it exists, to delete
     */
    public void delete(E item)
    {
    	if(contains(item) != -1)
    		remove(contains(item));
    }//end delete
    
    /**
     * Looks for the index of the specified element in this list. If
     * the element is not present, the method returns <code>-1</code>
     * @param elt the element which index is looked for.
     * @return either the index of the location in the list where the
     * argument is present or <code>-1</code> if the argument is not
     * contained in the list.
     */
    public int contains(E elt)
    {
    	int counter = 1;
    	Node<E> curr = head;
    	do
    	{
    		if(curr.getItem().equals(elt))
    		{
    			return counter;
    		}
    		curr = curr.getNext();
    	    counter++;
    	}
    	
    	while(curr.getNext() != null);
    	   	
    	return -1;
    	
    }//end contains
    
    /**
     * Prints all the elements in this list on the console in sequence
     */
    public void display()
    {
    	Node<E> curr = head;
    	while(curr != null)
    	{
    		System.out.print(" "+curr.getItem());
    		curr = curr.getNext();
    	}
    	System.out.println("");
    	
    }//end display

    /**
     * Prints all the elements in this list on the console in reverse
     * order
     */
    public void displayReverse()
    {
    	Node<E> curr = find(numItems);
    	while(curr != null)
    	{
    		System.out.print(" "+curr.getItem());
    		curr = curr.getPrev();
    	}
    	System.out.println();
    }// end displayReverse

    public ListReferenceBasedIterator<E> iterator(){
	return  new ListReferenceBasedIterator<E>(head);
    }
    public class ListReferenceBasedIterator<E> implements Iterator<E>{
	Node<E> current;

	public ListReferenceBasedIterator(Node<E> node){
	    current=node;
	}
	
	public boolean hasNext(){
	    return (current!=null);
	}

	public E next(){
	    E elt = current.getItem();
	    current = current.getNext();
	    return elt;
	}

	public void remove(){}
    }
} // end ListReferenceBased
