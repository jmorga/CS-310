//Programmer: Joseph Morga
//Red ID: 817281186
//File Name: UnorderedArrayPriorityQueue
//Programming Assignment # 1
//Date Created: 02/15/2018
//Date Last Modified: 02/21/2018
//Professor: Patty Kraft
//Class: CS310: Data Structures

//package data_structures;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnorderedArrayPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E>
{
	private int capacity = 0;
	private int size = 0;
	private E[] queue = null;
	
	public UnorderedArrayPriorityQueue() {
		capacity = DEFAULT_MAX_CAPACITY;
		queue = (E[]) new Comparable[DEFAULT_MAX_CAPACITY];
	}
	
	public UnorderedArrayPriorityQueue(int maxCapacity) {
		capacity = maxCapacity;
		queue = (E[]) new Comparable[maxCapacity];
	}
	
	@Override
	public boolean insert(E object) {
		if(size == capacity)
			return false;
		queue[size++] = object;
		return true;
	}

	@Override
	public E remove() {
		E object = null;
		int index = 0;
		
		if(size != 0) {
			object = queue[0];
			for(int i = 0; i < size; i++) {
				if(object.compareTo(queue[i]) > 0) {
					object = queue[i];
					index = i;
				}
			}
			shiftLeft(index);
			size--;
		}
		return object;
	}

	@Override
	public boolean delete(E obj) {
		int previousSize = size;
		int jump = 0;
		int holdIndex = 0;
		
		for(int i = 0; i < size; i++) {
			holdIndex = i;
			if(obj.compareTo(queue[i + jump]) == 0) {
				jump++;
				size--;
				i--;	
			}
			if(holdIndex == i)
				queue[i] = queue[i + jump];
		}
		return previousSize != size;
	}

	@Override
	public E peek() {
		E object = null;
		if(size != 0)
		{
			object = queue[0];
			for(int i = 0; i < size; i++) 
				if(object.compareTo(queue[i]) > 0) 
					object = queue[i];
		}
		return object;
	}

	@Override
	//Will use linear search to find the object
	public boolean contains(E obj) {
		for(int i = 0; i < size; i++)
			if(obj.compareTo(queue[i]) == 0)
				return true;
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		size = 0;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean isFull() {
		return size == capacity;
	}

	@Override
	public Iterator iterator() {
		return new IteratorHelper();
	}
	
//	It will set the index i equals to the starting point provided and it will set it equal to the 
//	object at i + 1, it will continue to do this until i equals the size of the array.
	private void shiftLeft(int start){
		for(int i = start; i < size - 1; i++)
			queue[i] = queue[i + 1];
	}
	
	private class IteratorHelper<E> implements Iterator<E>
	{
		int iterIndex = 0;
		public boolean hasNext(){
			return (iterIndex < size);
		}
		public E next(){
			if(!hasNext())
				throw new NoSuchElementException();
			return (E) queue[iterIndex++];
		}
	}
}
