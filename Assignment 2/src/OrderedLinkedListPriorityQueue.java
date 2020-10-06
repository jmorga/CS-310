//Programmer: Joseph Morga
//Red ID: 817281186
//File Name: OrderedLinkedListPriorityQueue
//Programming Assignment # 2
//Date Created: 03/13/2018
//Date Last Modified: 03/10/2018
//Professor: Patty Kraft
//Class: CS310: Data Structures

//package data_structures;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class OrderedLinkedListPriorityQueue <E extends Comparable<E>> implements PriorityQueue<E>{

	private int size = 0;
	private Node<E> head = null;
	
//	In case someone tries to set a limit to the size
	public OrderedLinkedListPriorityQueue(int size) {}
	
	public OrderedLinkedListPriorityQueue() {}
	
	@Override
//	It will insert the new element into the list which will be ordered by priority and FIFO.
//	Will always return true because there is no max capacity
	public boolean insert(E obj) {
		Node<E> hold = new Node<>();
		hold.next = head;
		Node<E> target = hold;
		
		while(target.next != null && obj.compareTo(target.next.data) >= 0)
			target = target.next;
		
		Node<E> newNode = new Node<>(obj, target.next);
		target.next = newNode;
		
		head = hold.next;
		
		size++;
		return true;
	}

	@Override
//	Will save the data in the head to a variable which will be returned. It will return null 
//	if the list is empty.
	public E remove() {
		if(size == 0)
			return null;
		
		E target = head.data;
		head = head.next;
		size--;
		return target;
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
//	Will return the data in the head
	public E peek() {return head.data;}

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
//	returns the number of elements in the list
	public int size() {return size;}

	@Override
//	sets the head to null and size to 0
	public void clear() {
		size = 0;
		head = null;
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