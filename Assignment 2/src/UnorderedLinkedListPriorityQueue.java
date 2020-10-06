//Programmer: Joseph Morga
//Red ID: 817281186
//File Name: UnorderedLinkedListPriorityQueue
//Programming Assignment # 2
//Date Created: 03/14/2018
//Date Last Modified: 03/10/2018
//Professor: Patty Kraft
//Class: CS310: Data Structures

//package data_structures;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnorderedLinkedListPriorityQueue <E extends Comparable<E>> implements PriorityQueue<E>{

	private int size = 0;
	private Node<E> head = null;
	
//	In case someone tries to set a limit to the size
	public UnorderedLinkedListPriorityQueue(int size) {}
	
	public UnorderedLinkedListPriorityQueue() {}
	
	@Override
//	It will insert the element obj at the tail of the list
	public boolean insert(E obj) {
		Node<E> hold = new Node<>();
		hold.next = head;
		Node<E> target = hold;
		
		while(target.next != null)
			target = target.next;
		
		Node<E> newNode = new Node<>(obj, target.next);
		target.next = newNode;
		head = hold.next;
		
		size++;
		return true;
	}

	@Override
//	it will iterate through the linked list to find the object with the highest priority.
//	it will delete the node where it was found and return the data
	public E remove() {
		Node<E> hold = new Node<>();
		Node<E> target = new Node<>();
		hold.next = head;
		target = hold;
		Node<E> priority = target;
		
		if(!isEmpty()) {
			while(target.next != null) {
				if(priority.next.data.compareTo(target.next.data) > 0)
					priority = target;
				target = target.next;
			}
			
			E data = priority.next.data;
			priority.next = priority.next.next;
			head = hold.next;
			size--;
			return data;
		}	
		return null;
	}

	@Override
//	It will delete all the instances of the parameter obj, then it'll return true if an object was
//	removed, false otherwise
	public boolean delete(E obj) {
		int previousSize = size;
		
		Node<E> hold = new Node<>();
		hold.next = head;
		Node<E> target = hold;
		
		while(target.next != null)
			if(obj.compareTo(target.next.data) == 0) {
				target.next = target.next.next;
				size--;
			}else
				target = target.next;
		
		head = hold.next;
		return previousSize != size;
	}

	@Override
//	it will iterate through the linked list to find the object with the highest priority.
//	and return the data but will not delete the node
	public E peek() {
		E object = null;
		Node<E> target = head;
		if(size != 0)
		{
			object = head.data;
			while(target != null) 
				if(object.compareTo(target.data) > 0) 
					object = target.data;
				else
					target = target.next;
		}
		return object;
	}

	@Override
//	will iterate into the list and will return true if the parameter obj was found, false
//	otherwise.
	public boolean contains(E obj) {
		Node<E> target = head;
		while(target != null){
			if(obj.compareTo(target.data) == 0)
				return true;
			target = target.next;
		}
		return false;
	}

	@Override
//	return the number of elements in the list
	public int size() {return size;}

	@Override
//	deletes all elements by setting the head to null and size to 0
	public void clear() {
		head = null;
		size = 0;
	}

	@Override
//	returns true if there are no elements in the list, false otherwise
	public boolean isEmpty() {return size == 0;}

	@Override
//	There is always space in the linked list, so it always returns true
	public boolean isFull() {return false;}

	@Override
	public Iterator<E> iterator() {return new IteratorHelper();}

	private static class Node<E>{
		private E data = null;
		private Node<E> next = null;
		
		public Node() {}
		
		public Node(E obj, Node<E> nextNode) {
			data = obj;
			next = nextNode;
		}
	}
	
	public class IteratorHelper implements Iterator<E>
	{
		private Node<E> current;
		public IteratorHelper() {current = head;}
		
		public boolean hasNext() {return current != null;}

		public E next() {
			if(!hasNext())
				throw new NoSuchElementException();
			
			E data = current.data;
			current = current.next;
			return data;
		}
	}
}
