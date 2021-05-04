//  Assignment #: 10
//  Name: Rock Palmer
//  StudentID: 1214784662
//  Lecture: T and Th 1:30-2:45PM
//  Description: The LinkedList defines a linked list using its node class
//  object and also defines a iterator class to traverse the linked list.

import java.util.NoSuchElementException;

/**   
 * A linked list is a sequence of nodes with efficient   
 * element insertion and removal. This class   
 * contains a subset of the methods of the standard   
 * java.util.LinkedList class.
 */
public class LinkedList
{   
	private Node first;
	/**      
	 * Constructs an empty linked list.   
	 */   
	public LinkedList()   
	{      
		first = null;   
	}
	/**      
	 * Returns the first element in the linked list.      
	 * 
	 * @return the first element in the linked list   
	 * 
	 */   
	public Object getFirst()   
	{      
		if (first == null)         
			throw new NoSuchElementException();      
		return first.data;   
	}   
	/**      
	 * Removes the first element in the linked list.      
	 * 
	 * @return the removed element
	 * 
	 */ 
	public Object removeFirst()   
	{      
		if (first == null)         
			throw new NoSuchElementException();      
		Object element = first.data;     
		first = first.next;      
		return element;   
	}   
	/**      
	 * Adds an element to the front of the linked list.      
	 * 
	 * @param element the element to add
	 * 
	 */   
	public void addFirst(Object element)
	{      
		Node newNode = new Node();
		newNode.data = element;
		newNode.next = first; 
		first = newNode;   
	}   
	
	/*************** Added methods *******************************/   
	
	// 1. The add adds the parameter string into the linked list. The linked list   
	//     should contain all strings in alphabetical order   
	public void add(String str) 
	{
		ListIterator iterator = listIterator();
		String temp = new String();
		
		// If there's nothing in the list already, adds the specified String
		//		as the first element.
		if (this.size() == 0 || ((String)getFirst()).compareTo(str) >= 0) 
		{
			addFirst(str);
		}
		// If there are already nodes in the list, it searches the list for the
		//		correct spot to place the String.
		else 
		{
			boolean positionIsFound = false;
			
			// The loop goes through the list until it passes a node which,
			//		alphabetically, should go after the parameter String
			while(iterator.hasNext() && !positionIsFound) 
			{
				temp = (String)iterator.next();
				if (temp.compareTo(str) >= 0) 
				{
					positionIsFound = true;
				}
			}
			// If it passes a node that should go after the parameter String,
			//		the node that was just passed is set to the value of the
			//		parameter String and another node is added with the same
			//		data as the past node.
			if (positionIsFound) 
			{
				iterator.set(str);
				iterator.add(temp);
			}
			// If the program reaches the end of the list and it has not past
			//		a node that should go after the parameter String, the program
			//		adds the parameter String at the end of the list.
			else 
			{
				iterator.add(str);
			}
		}
	}
	
	//2. count method counts how many times the parameter object   
	//appears in the linked list and return the number. It returns 0   
	//if the parameter object does not exist in the linked list.
	public int count(String str) 
	{
		int count = 0;
		
		ListIterator iterator = listIterator();
		while(iterator.hasNext()) 
		{
			if (str.equals(iterator.next())) 
			{
				count++;
			}
		}
		
		return count;
	}
	
	//3. search method returns the index of the parameter object  
	//in the linked list if it exists. It return -1 if it does not  
	//exits. If the index is out of bounds, then it throws an exception.  
	public int search(String str) 
	{
		int index = 0;
		
		ListIterator iterator = listIterator();
		boolean indexIsFound = false;
		while(!indexIsFound)
		{
			if (str.equals((String)iterator.next())) 
			{
				indexIsFound = true;
			}
			else 
			{
				index++;
				indexIsFound = !iterator.hasNext();
			}
		}
		
		if (!iterator.hasNext()) 
		{
			index = -1;
		}
		
		return index;
	}
	
	//4. remove method removes the element at the parameter   
	//index in the linked list. 
	public String remove(int index)
	{
		String str = null;
		int count = 0;
		
		ListIterator iterator = listIterator();
		while (count <= index && iterator.hasNext()) 
		{
			str = (String)iterator.next();
			count++;
		}
		if(count == index + 1) 
		{
			iterator.remove();
		}
		
		return str;
	}
	
	//5. The method size return the current size of the linked list,   
	//that is, the number of elements in it.   
	public int size() 
	{
		int size = 0;
		
		ListIterator iterator = listIterator();
		while(iterator.hasNext()) 
		{
			iterator.next();
			size++;
		}
		
		return size;
	}
	
	//6. The toString method returns a string containing the content   
	//of the linked list. In this assignment, the linked list will   
	//contain only string, so it returns a concatenation of all strings  
	//in the linked list and a line break   
	public String toString() 
	{
		String content = "{";
		ListIterator iterator = listIterator();
		while(iterator.hasNext()) 
		{
			String temp = (String)iterator.next();
			content += " " + temp;
		}
		content += " }\n";
		return content;
	}
	
	//7. The removeLastFew method removes the parameter specified number   
	//of elements from the end of the linked list.   
	//If the parameter integer is larger than the current size of   
	//the linked list, then the linked-list will be empty.   
	//If the parameter integer is less than 0,   
	//nothing should be removed from the linked list.
	public void removeLastFew(int num) 
	{
		int size = this.size();
		int count = 0;
		
		// If the num integer is larger than the actual size of the list,
		//		The program will just remove all nodes from the list.
		if(num > size) 
		{
			num = size;
		}
		else if (num > 0) 
		{
			ListIterator iterator = listIterator();
			
			// If the number is greater than zero, the program will go from node
			//		to node until it reaches the start of the numbers it must remove
			while (count <= size - num) 
			{
				iterator.next();
				count++;
			}
			
			// Once it arrives at the first node it should remove, it continues to remove
			//		nodes until it reaches the end of the list.
			while (iterator.hasNext()) 
			{
				iterator.remove();
				iterator.next();
			}
			iterator.remove();
		}
		
		// If the num integer is equal to the size of the array, either because the user put
		//		put in a number that was too large or because they actually inputed the size
		//		of the list, the program will also remove the head of the list.
		if (num == size) 
		{
			removeFirst();
		}
	}
	
	/***************************************************************/  
	
	/**      
	 * Returns an iterator for iterating through this list.     
	 * 
	 * @return an iterator for iterating through this list   
	 */   
	public ListIterator listIterator() 
	{      
		return new LinkedListIterator();
	}   
	private class Node
	{      
		public Object data;
		public Node next;
	}   
	private class LinkedListIterator implements ListIterator   
	{
		private Node position;   
		private Node previous; 
		/**         
		 * Constructs an iterator that points to the front         
		 * of the linked list.      
		 */      
		public LinkedListIterator()      
		{         
			position = null;         
			previous = null;      
		}      
		/**         
		 * Moves the iterator past the next element.         
		 * 
		 * @return the traversed element      
		 * 
		 */      
		public Object next()      
		{         
			if (!hasNext())            
				throw new NoSuchElementException();        
			previous = position; 
			
			// Remember for remove        
			if (position == null)          
				position = first;      
			else            
				position = position.next;       
			return position.data;     
		}      
		/**         
		 * Tests if there is an element after the iterator         
		 * position.         
		 * 
		 * @return true if there is an element after the iterator       
		 * position      
		 */      
		public boolean hasNext()      
		{         
			if (position == null)            
				return first != null;
			else            
				return position.next != null;      
		}      
		/**         
		 * Adds an element before the iterator position         
		 * and moves the iterator past the inserted element.         
		 * 
		 * @param element the element to add    
		 */      
		public void add(Object element)  
		{         
			if (position == null)         
			{            
				addFirst(element);            
				position = first;         
			}         
			else         
			{
				Node newNode = new Node();
				newNode.data = element;
				newNode.next = position.next;
				position.next = newNode;
				position = newNode;
			}         
			previous = position;    
		}      
		/**         
		 * Removes the last traversed element. This method may         
		 * only be called after a call to the next() method.      
		 */      
		public void remove()      
		{         
			if (previous == position)            
				throw new IllegalStateException();
			if (position == first)
			{            
				removeFirst();         
			}         
			else         
			{            
				previous.next = position.next;         
			}         
			position = previous;      
		}      
		/**        
		 * Sets the last traversed element to a different         
		 * value.         
		 * 
		 * @param element the element to set      
		 */      
		public void set(Object element)      
		{         
			if (position == null)            
				throw new NoSuchElementException();         
			position.data = element;      
		} 
	}
}