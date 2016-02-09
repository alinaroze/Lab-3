/** class Node for the implementation of simple linked list. The node
 * contains only an item and a reference to the following Node
 */

public class Node<E> {

    /** Object contained in the Node */
    private E item;
    /** reference to the following node */
    private Node<E> next;
    /**reference to the previous node */
    private Node<E> prev;

    /** Contructor: create a new Node containing the specified item
     * @param item the item encapsulated in the node
     */
    Node(E item) 
    {
    	this.item = item;
    } // end constructor

    /** Constructor: creates a new Node containing the specified item
     * and links this node to the specified node: this -> nextNode
     * @param item the item encapsulated in the node
     * @param next the successor node of this node.
     * @param prev the predecessor node of this node
     */
    Node(E item, Node<E> prev, Node <E> next ) 
    {
    	this.item = item;
    	setPrev(prev);
    	setNext(next);
    } // end constructor

    /** set the reference to the previous node
     * @param new previous node */
    void setPrev(Node<E> prev) 
    {
		this.prev = prev;
		
	}// end setPrev
    
    /** set the reference to the following node
     * @param new following node */
    void setNext(Node<E> next)
    {
    	this.next = next;
    }//end setNext

	/** set the item in the node
     * @param newItem the new item to be encapsulated in the node.
     */
    void setItem(E newItem) {
	item = newItem;
    } // end setItem
    
    /** get the item encapsulated in the node
     * @return the object encapsulated in the node
     */
    E getItem() {
	return item;
    } // end getItem
    

    /** get the previous node
     * @returns the next node
     */
    Node<E> getNext() 
    {
	 return next;
    } // end getNext
    
    /** get the following node
     * @returns the previous node
     */
    Node<E> getPrev() 
    {
	 return prev;
    } // end getPrev

} // end class Node
