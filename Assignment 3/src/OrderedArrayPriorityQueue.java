//Programmer: Joseph Morga
//Red ID: 817281186
//File Name: OrderedArrayPriorityQueue
//Programming Assignment # 1
//Date Created: 02/14/2018
//Date Last Modified: 02/21/2018
//Professor: Patty Kraft
//Class: CS310: Data Structures

//package data_structures;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderedArrayPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E>
{
	private int capacity = 0;
	private int size = 0;
	private E[] queue = null;
	
    private int lastIndex = 0;     //When binarySearch doesn't find the object, it will save the index where
                                   //the object should be found in this variable.
	
	public OrderedArrayPriorityQueue() {
		capacity = DEFAULT_MAX_CAPACITY;
		queue = (E[]) new Comparable[DEFAULT_MAX_CAPACITY];
	}
	
	public OrderedArrayPriorityQueue(int maxCapacity) {
		capacity = maxCapacity;
		queue = (E[]) new Comparable[maxCapacity];
	}
	
	@Override
	public boolean insert(E object) {
		if(size == capacity)
			return false;
		
		if(size == 0)
			queue[size++] = object;
		else {
			int index = binarySearchMin(object);
			
			if(index != -1)
				shiftRight(index);
			else {
				index = lastIndex;
				if(object.compareTo(queue[index]) < 0)
					shiftRight(++index);
				else if(object.compareTo(queue[index]) > 0)
					shiftRight(index);
			}
			queue[index] = object; size++;
		}
		return true;
	}

	@Override
	public E remove() {
		if(size != 0)
			return queue[--size];
		return null;
	}

	@Override
	public boolean delete(E obj)  {
		int min = binarySearchMin(obj);
		int max = binarySearchMax(obj);
		int difference = max - min + 1;
		
		if(min != -1) {
			shiftLeft(min, difference);
			return true;
		}
		return false;
	}

	@Override
	public E peek() {
		if(size != 0)
			return queue[size - 1];
		return null;
	}

	@Override
	public boolean contains(E obj) {
		return obj.compareTo(queue[binarySearchMin(obj)]) == 0;
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
	
//	It will find the object with the lowest priority and return the index where it is located. If object
//	is not found, it will save the index where it should be and will return -1.
	private int binarySearchMin(E target)
	{
		int min = 0; 
		int max = size - 1;
		int middle;
		
		while(min < max){
			middle = (min + max)/ 2;
			if(target.compareTo(queue[middle]) >= 0)
				max = --middle;
			else
				min = ++middle;
		}
		if(target.compareTo(queue[min]) == 0)
			return min;
		
		lastIndex = min;
		return -1;
	}
	
//	It will find the object with the highest priority and return the index where it is located. If object
//	is not found, it will save the index where it should be and will return -1.
	private int binarySearchMax(E target)
	{
		int min = 0; 
		int max = size - 1;
		int middle;
		
		while(min < max){
			middle = (min + max)/ 2;
			
			if(target.compareTo(queue[middle]) == 0 && target.compareTo(queue[middle + 1]) > 0)
				return middle;
			else if (target.compareTo(queue[middle ]) > 0)
				max = --middle;
			else
				min = ++middle;
		}
		if(target.compareTo(queue[min]) == 0)
			return min;
		lastIndex = min;
		return -1;
	}
	
//	It will set the starting index i = to the starting point, and it will set it equal to the object at
//	i + jump. I will continue to do this until i = size.
	private void shiftLeft(int start, int jump) {
		size = size - jump;
		while(start != size) {
			queue[start] = queue[start++ + jump];
		}
	}
	
//	It will set the index i equals to the current size of the array and it will set it equal to the 
//	object at i - 1, it will continue to do this until i equals the stopping index.
	private void shiftRight(int stop){
		for(int i = size; i != stop; i--)
			queue[i] = queue[i - 1];
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