/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the first node of the list
	 * @return The first node of the list.
	 */		
	public Node getFirst() {
		return this.first;
	}

	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */		
	public Node getLast() {
		return this.last;
	}
	
	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */		
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		Node node = this.first;
		int counter = 0;
		while(node != null){
			if(counter == index){
				return node;
			}
			node = node.next;
			counter++;
		}
		return node;
	}
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		if(index == 0){
			addFirst(block);
			return;
		}
		if(index == size){
			addLast(block);
			return;
		}
		Node newNode = new Node(block);
		Node node = this.first;
		int counter = 1;
		while(node != null){
			if(counter == index){
				newNode.next = node.next;
				node.next = newNode;
				size++;
				return;
			}
			node = node.next;
			counter++;

		}

	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		if(block == null){
			return;
		}
		Node newLast = new Node(block);
		if(this.last == null){
			this.first = newLast;
			this.last = newLast;
			size++;
			
		}
		else{
			this.last.next = newLast;
			this.last = newLast;
			size++;
		}
	}
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		if(block == null){
			return;
		}

		Node newFirst = new Node(block);
		if(this.first == null){
			this.first = newFirst;
			this.last = newFirst;
			size++;
			
		}
		else{
			newFirst.next = first;
			this.first = newFirst;
			size++;
		}
		
	}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		Node node = this.first;
		int counter = 0;
		while(node != null){
			if(counter == index){
				return node.block;
			}
			node = node.next;
			counter++;
		}
		if (index <= 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		
		return null;
	}	

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		Node node = this.first;
		int counter = 0;
		if(node == null){
			return -1;
		}
		while(node != null){
			if(node.block.equals(block)){
				return counter;
			}
			else{
				counter++;
				node = node.next;
			}
		}
		return -1;
	}

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		if(this.first == node){
			this.first = this.first.next;
			size--;
			if(this.last == node){
				this.last = null;
			}
			return;
		}
		if(this.first == null){
			return;
		}
		Node checkNode = this.first.next;
		Node priorNode = this.first;
		while(priorNode != null){
			if(checkNode == null){
				return;
			}
			else if(checkNode == node){
				if(checkNode == this.last){
					this.last = priorNode;
					priorNode.next = null;
					size--;
					return;
				}
				else{
					priorNode.next = checkNode.next;
					size--;
					return;
				}
			}
			priorNode = checkNode;
			checkNode = checkNode.next;
		}
		
	}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		if(index == 0){
			if(this.last == this.first){
				this.last = null;
			}
			this.first = this.first.next;
			size--;
			return;
		}
		if(this.first == null){
			return;
		}
		int counter = 1 ;
		Node checkNode = this.first.next;
		Node priorNode = this.first;
		while(checkNode != null){
			if(counter == index || counter == size){
				if(checkNode == this.last){
					this.last = priorNode;
					priorNode.next = null;
					size--;
					return;
				}
				else{
					priorNode.next = checkNode.next;
					size--;
					return;
				}
			}
			priorNode = checkNode;
			checkNode = checkNode.next;
			counter++;
		}


	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		if(block == null){
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		if(this.first.block == block){
			if(this.last == this.first){
				this.last = null;
			}
			this.first = this.first.next;
			size--;
			return;
		}
		Node checkNode = this.first.next;
		Node priorNode = this.first;
		while(checkNode != null){
			if(checkNode.block == block){
				if(checkNode == this.last){
					this.last = priorNode;
					priorNode.next = null;
					size--;
					return;
				}
				else{
					priorNode.next = checkNode.next;
					size--;
					return;
				}
			}
			priorNode = checkNode;
			checkNode = checkNode.next;
		}
		throw new IllegalArgumentException(
					"index must be between 0 and size");
	}	

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
		String ans = "";
		Node node = this.first;
		while(node != null){
			ans += node.toString();
		}
		return ans;
	}
}