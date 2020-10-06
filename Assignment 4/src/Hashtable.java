//Programmer: Joseph Morga
//Red ID: 817281186
//File Name: Hashtable
//Programming Assignment # 4
//Date Created: 04/15/2018
//Date Last Modified: 05/02/2018
//Professor: Patty Kraft
//Class: CS310: Data Structures

//package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Hashtable <K extends Comparable<K>, V> implements DictionaryADT<K, V>{

	private int size;
	private int tableSize;
	private int capacity;
	private int modCounter;
	LinkedList<DictionaryNode<K, V>> [] table;
	
	public Hashtable(int theCapacity) {
		capacity = theCapacity;
		tableSize = (int) (1.3 * capacity);
		size = 0;
		modCounter = 0;
		table = new LinkedList[tableSize];
		
		for(int i = 0; i < tableSize; i++)
			table[i] = new LinkedList<>();
	}

	@Override
	public boolean contains(K key) {
		if(isEmpty()) return false;
		
		return table[getHashCode(key)].contains(new DictionaryNode<>(key, null));
	}

	@Override
	public boolean add(K key, V value) {
		if(isFull() || contains(key)) return false;
		
		size++;
		modCounter++;
		return table[getHashCode(key)].add(new DictionaryNode<>(key, value));
	}

	@Override
	public boolean delete(K key) {
		
		if(isEmpty() || !contains(key)) return false;
		
		size--;
		modCounter++;
		table[getHashCode(key)].delete(new DictionaryNode<>(key, null));
		return true;
	}

	@Override
	public V getValue(K key) {
		if(isEmpty() || !contains(key)) return null;
		DictionaryNode<K, V> node = table[getHashCode(key)].getValueNode(new DictionaryNode<>(key, null));
		
		if(node == null) return null;
		
		return node.value;
	}

	@Override
	public K getKey(V value) {
		if(isEmpty()) return null;
		
		for(int i = 0; i < table.length; i++)
			for(DictionaryNode<K, V> node : table[i])
				if(((Comparable<V>)node.value).compareTo(value) == 0)
					return node.key;
		return null;
	}

//	-----------------------------------------------
//	 			  Other methods
	@Override
	public int size() { return size;}

	@Override
	public boolean isFull() {return size == capacity;}

	@Override
	public boolean isEmpty() { return size == 0; }

	@Override
	public void clear() {
		size = 0;
		modCounter++;
		
		for(int i = 0; i < tableSize; i++)
			table[i] = new LinkedList<>();
	}
	
//	-----------------------------------------------
//	 			Private Methods
	
	private int getHashCode(K key){
		return (key.hashCode() & 0xFFFFFFF) % tableSize;
	}
	
	private DictionaryNode<K,V>[] shellSort(DictionaryNode[] array){
		 
		DictionaryNode<K,V> [] node = array;
		int in, out, h = 1;
	    DictionaryNode<K,V> temp = null;
	    int theSize = node.length;
	    	
	    while(h <= theSize/3)
	    	h = h*3+1;
		
	    while(h>0){
	    	for(out = h; out < theSize; out++){
	    		temp = node[out];
		    	in = out;
		    	while(in > h - 1 && node[in - h].compareTo(temp) >= 0){
		    		node[in] = node[in - h];
		    		in -= h;
		    	}
		   		node[in]=temp;
		   	}
		    h = (h - 1)/3;
	    }
		return node;
	}
//	-----------------------------------------------
//	 Iterators
	@Override
	public Iterator<K> keys() { return new KeyIteratorHelper();}
	
	@Override	
	public Iterator<V> values() { return new ValueIteratorHelper(); }
	
	
	private abstract class IteratorHelper<E> implements Iterator<E>{
		protected DictionaryNode <K,V> [] output;
		protected int index;
		private long modCtr;
		
		
		public IteratorHelper() {
			index = 0;
			modCtr = modCounter;
			output = new DictionaryNode[size];
			
			int j = 0;
			
			for(int i=0; i<tableSize; i++)
				for(DictionaryNode node : table[i])
					output[j++] = node;
			
			output = (DictionaryNode<K,V>[]) shellSort(output);
		}
		
		@Override
		public boolean hasNext() {
			if(modCtr != modCounter)
				throw new ConcurrentModificationException();
			return index < size;
		}
	
		@Override
		public abstract E next();
		
	}
	
	private class KeyIteratorHelper<K> extends IteratorHelper<K>{

		public KeyIteratorHelper() { super(); }
		
		@Override
		public K next() {
			if(!hasNext())
		        throw new NoSuchElementException();
		    return (K) output[index++].key;
		}
	}
	
	private class ValueIteratorHelper<V> extends IteratorHelper<V>{

		public ValueIteratorHelper() { super(); }
		
		@Override
		public V next() {
			if(!hasNext())
		        throw new NoSuchElementException();
		    return (V) output[index++].value;
		}
	}
//	-----------------------------------------------
//				 Dictionary Node class
	private class DictionaryNode<K, V> implements Comparable<DictionaryNode<K, V>>
	{
		private K key;
		private V value;

		public DictionaryNode(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public int compareTo(DictionaryNode<K, V> node) {
			return ((Comparable<K>) key).compareTo((K) node.key);
		}
	}
//	-----------------------------------------------
//	 			 Linked List Class
	private class LinkedList<E extends Comparable<E>> implements Iterable<E> {

		private Node<E> head;
		
		public LinkedList() { head = null; }
		
		public boolean contains(E obj) {
			Node<E> target = head;
			while(target != null){
				if(obj.compareTo(target.data) == 0)
					return true;
				target = target.next;
			}
			return false;
		}
		
		public boolean add(E obj) {
			
			if(head == null)
				head = new Node<>(obj);
			else {
				Node<E> newNode = new Node<>(obj);
				newNode.next = head;
				head = newNode;
			}
			return true;
		}
		
		public void delete(E obj) {
			Node<E> hold = new Node<>();
			hold.next = head;
			Node<E> target = hold;
			
			while(target.next != null)
				if(obj.compareTo(target.next.data) == 0) 
					target.next = target.next.next;
				else
					target = target.next;
			
			head = hold.next;
		}
		
		public E getValueNode(E obj) {
			Node<E> target = head;
			while(target != null){
				if(obj.compareTo(target.data) == 0)
					return target.data;
				target = target.next;
			}
			return null;
		}
		
		@Override
		public Iterator<E> iterator() { return new IteratorHelper(); }
		
		public class IteratorHelper implements Iterator<E>{
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
		
		private class Node<E>{
			private E data = null;
			private Node<E> next = null;
			
			public Node() {}
			
			public Node(E obj) {
				data = obj;
				next = null;
			}
		}
	}
}
