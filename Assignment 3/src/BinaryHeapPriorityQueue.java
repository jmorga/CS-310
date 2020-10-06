//Programmer: Joseph Morga
//Red ID: 817281186
//File Name: BinaryHeapPriorityQueue
//Programming Assignment # 3
//Date Created: 03/30/2018
//Date Last Modified: 04/06/2018
//Professor: Patty Kraft
//Class: CS310: Data Structures

//package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinaryHeapPriorityQueue <E extends Comparable<E>> implements PriorityQueue<E>{

	private int size;
	private long entryNumber;
	private int capacity;
	private Wrapper<E>[] heap;
	
	public BinaryHeapPriorityQueue(int newCapacity) {
		entryNumber = 0;
		size = 0;
		capacity = newCapacity;
		heap = new Wrapper[newCapacity];
	}
	
	public BinaryHeapPriorityQueue() {
		entryNumber = 0;
		size = 0;
		capacity = DEFAULT_MAX_CAPACITY;
		heap = new Wrapper[capacity];
	}
	
	@Override
//	It will insert a new element into the heap which will be ordered by priority and FIFO.
//	It will return true if the insertion was successful, false otherwise.
	public boolean insert(E obj) {
		if(isFull())
			return false;
		
		heap[size] = new Wrapper<>(obj);
		int child = size++;
		int parent = (child - 1) / 2;
		
		while(parent >= 0 && heap[parent].compareTo(heap[child]) > 0) {
			swap(parent, child);
			child = parent;
			parent = (child - 1) / 2;
		}	
		return true;
	}

	@Override
//	Will remove the root and will heapify the tree before returning the data in the root
	public E remove() {
		if(isEmpty())
			return null;
		
		E target = heap[0].data;
		int parent = 0;
		heap[0] = heap[--size];
		heapify(parent);
		
		return target;
	}

	@Override
//	Will delete all the instances of the obj and return true if all obj were deleted, false otherwise
	public boolean delete(E obj) {
		int currentSize = size;
		
		for(int parent = 0; parent < size; parent++)
			if((heap[parent].data).compareTo(obj) == 0) {
				heap[parent] = heap[--size];
				heapify(parent--);
			}
				
		return currentSize != size;
	}

	@Override
//	will return true if the ibj exists in the heap, false otherwise
	public boolean contains(E obj) {
		
		for(int i = 0; i < size; i++)
			if((heap[i].data).compareTo(obj) == 0)
				return true;
		
		return false;
	}

	@Override
//	If the heap is empty, it will return null, otherwise, it will return the data in the root
	public E peek() { 
		if(isEmpty())
			return null;
		return heap[0].data;
	}
	
	@Override
//	Returns the number of elements in the heap
	public int size() { return size; }

	@Override
//	Sets size to zero
	public void clear() { size = 0; }

	@Override
//	Returns true if the heap is empty, returns false otherwise
	public boolean isEmpty() { return size == 0; }

	@Override
//	Returns true if the heap is full, returns false otherwise
	public boolean isFull() { return size == capacity; }

	@Override
	public Iterator<E> iterator() { return new IteratorHelper(); }
	
//	Swaps the position of the child and the parent
	private void swap(int child, int parent) {
		Wrapper<E> hold = heap[parent];
		heap[parent] = heap[child];
		heap[child] = hold;
	}
	
	private class IteratorHelper<E> implements Iterator<E> {

		int iterIndex = 0;
		long modCheck;
		
		public IteratorHelper() {
			iterIndex = 0;
			modCheck = entryNumber;
		}
		@Override
		public boolean hasNext(){ 
			
			if(modCheck != entryNumber)
				throw new ConcurrentModificationException();
			
			return iterIndex < size; 
		}
		@Override
		public E next(){
			if(!hasNext())
				throw new NoSuchElementException();
			
			return (E) heap[iterIndex++].data;
		}
	}
//	Rearranges the elements in the array to keep the order of the heap
	private void heapify(int parent) {

		int rightChild, leftChild;
		
		while(true) {
			leftChild = (2 * parent) + 1;
			rightChild = leftChild + 1;
			
			if(leftChild >= size)
				break;
			
//			Assuming left child is the smaller child
			if(leftChild < size && heap[rightChild].compareTo(heap[leftChild]) < 0)
				leftChild = rightChild;
			if(heap[parent].compareTo(heap[leftChild]) > 0) {
				swap(parent, leftChild);
				parent = leftChild;
			} else
				break;	
		}
	}
	
//	Helper class to keep priority since the heap is unstable
	private class Wrapper<E> implements Comparable<Wrapper<E>> {
		long number;
		E data;
		
		public Wrapper(E theData) {
			number = entryNumber++;
		    data = theData;
		}
		
		public int compareTo(Wrapper<E> obj) {
			
			if(((Comparable<E>)data).compareTo(obj.data) == 0)
				return (int)(number - obj.number);
			
			return ((Comparable<E>)data).compareTo(obj.data);
		}
	}
}
