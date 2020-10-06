//Programmer: Joseph Morga
//Red ID: 817281186
//File Name: BinarySearchTree
//Programming Assignment # 4
//Date Created: 04/15/2018
//Date Last Modified: 04/30/2018
//Professor: Patty Kraft
//Class: CS310: Data Structures

//package data_structures;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchTree <K extends Comparable<K>, V> implements DictionaryADT<K, V>{

	private TNode<K, V> root;
	private int size;
	private long modCounter;
	private K theKey;
	
	public BinarySearchTree() {
		root = null;
		size = 0;
		modCounter = 0;
	}
	
	// Returns true if the dictionary has an object identified by 
	// key in it, otherwise false.
	@Override
	public boolean contains(K key) {
		if(isEmpty()) return false;
		
		TNode<K, V> target = root;
		
		while(((Comparable<K>)target.key).compareTo((K)key) != 0) {
			if(((Comparable<K>)key).compareTo((K)target.key) < 0)
				target = target.leftChild;
			else
				target = target.rightChild;
			
			if(target == null) return false;
		}
		return true;
	}

//	-----------------------------------------------
//	 				add method
	@Override
	public boolean add(K key, V value) {
		if(contains(key)) return false;
		
		if(root == null) 
			root = new TNode<>(key, value);
		else 
			add(key, value, root);
		
		size++;
		modCounter++;
		return true;
	}
	
	// Recursive add method
	private void add(K key, V value, TNode<K,V> parent) {
		
		if(((Comparable<K>)key).compareTo(parent.key) > 0) { // Go to right
			if(parent.rightChild == null) 
				parent.rightChild = new TNode<>(key, value);
			else add(key, value, parent.rightChild);
		}
		else if(parent.leftChild == null) {
			parent.leftChild = new TNode<>(key, value);
		}
		else
			add(key, value, parent.leftChild);
	}
	
//	-----------------------------------------------
//	     			Delete
	@Override
	public boolean delete(K key) {
		
		if(isEmpty()) return false;
		
		TNode<K, V> target = root;
		TNode<K, V> parent = null;
		boolean isLeft = false;     // to know if the target comes from left node
		
		while(((Comparable<K>)target.key).compareTo((K)key) != 0) {
			parent = target;
			
			if(((Comparable<K>)key).compareTo((K)target.key) < 0) {
				target = target.leftChild;
				isLeft = true;
			}
			else {
				target = target.rightChild;
				isLeft = false;
			}
			if(target == null) return false;   //Item not found
		}
		
//		     --------  leaf node :)  --------     
		if(target.leftChild == null && target.rightChild == null) {
			if(parent == null) root = null;
			
			else if(isLeft) 
				parent.leftChild = null;
			else
				parent.rightChild = null;			
		}// --------   1 child  :V ----------
	
		else if((target.leftChild == null && target.rightChild != null)
			 || (target.leftChild != null && target.rightChild == null)) {
			
			if(target.leftChild != null) {  // has left child
				if(parent == null)
					root = root.leftChild;
				else
					parent = parent.leftChild;
			}
			else                            // has right child
				if(parent == null) root = root.rightChild;
			
			else
				parent = parent.rightChild;
		}
		else {  // ------ 2 children :( -------
		
			TNode<K, V> successor = inorderSuccessor(target.rightChild);
			
			if(successor == null) {  // Target's right child doesn't have left child
				target.key = target.rightChild.key;
				target.value = target.rightChild.value;
				target.rightChild = target.rightChild.rightChild;
			}
			else {
				target.key = successor.key;
			    target.value = successor.value;
			}
		}
		size--;
		modCounter++;
		return true;
	}
	
//	Node must be the right child of the target
	private TNode<K, V> inorderSuccessor(TNode<K, V> node) {
		
		TNode<K, V> parent = null;
		
		while(node.leftChild != null){
		    parent = node;
		    node = node.leftChild;
		}
	
		if(parent != null) {
			parent.leftChild = node.rightChild;
			return node;
		}
		return null;
	}
	
//	-----------------------------------------------
//	Returns the value associated with the given key
	
	@Override
	public V getValue(K key) {
		if(root == null) return null;
		
		TNode<K, V> target = root;
		
		while(((Comparable<K>)target.key).compareTo((K)key) != 0) {
			if(((Comparable<K>)key).compareTo((K)target.key) < 0)
				target = target.leftChild;
			else
				target = target.rightChild;
			
			if(target == null) return null;
		}
		return target.value;
	}

//	-----------------------------------------------
//	Returns the key associated with the given value
	@Override
	public K getKey(V value) {
		if(isEmpty()) return null;
		
		getKey(value, root);
		
		return theKey;
	} 
    // Recursive getKey method           
	private void getKey(V value, TNode<K, V> node) {
		
		if(node != null) {
			if(((Comparable<V>)value).compareTo((V)node.value) == 0)
			    theKey = node.key;
			else {
				getKey(value, node.leftChild);
				getKey(value, node.rightChild);
			}
		}
	}
	
//	-----------------------------------------------
//					Other methods
	@Override
	public int size() { return size; }
	
	@Override
	public boolean isFull() { return false; }
	
	@Override
	public boolean isEmpty() { return size == 0; }
	
	@Override
	public void clear() { 
	size = 0;
	root = null;
	}

//	-----------------------------------------------
//				      Iterators
	
	@Override
	public Iterator<K> keys() { return new KeyIteratorHelper(); }

	@Override
	public Iterator<V> values() { return new ValueIteratorHelper(); }
	
//	     ------ Iterators inner classes ------
	private abstract class IteratorHelper<E> implements Iterator<E>{

		TNode<K, V>[] arrayKey;
		int index;
		long modificationCtr;
			
		public IteratorHelper() {
			arrayKey = new TNode[size];
			index = 0;
			modificationCtr = modCounter;
			iterate(root);
			index = 0;
		}
			
		@Override
		public boolean hasNext() {
			if(modificationCtr != modCounter)
				throw new ConcurrentModificationException();
			return index < size;
		}
	
		@Override
		public abstract E next();
		
		private void iterate(TNode<K, V> node) {
			if(node == null) return;
			
			iterate(node.leftChild);
			arrayKey[index++] = node;
			iterate(node.rightChild);
		}	
	}
	
	private class KeyIteratorHelper<K> extends IteratorHelper<K>{

		public KeyIteratorHelper() { super(); }

		public K next() {
			if(!hasNext())
				throw new NoSuchElementException();
			return (K) arrayKey[index++].key;
		}
	}
	
	private class  ValueIteratorHelper<V> extends IteratorHelper<V>{

		public ValueIteratorHelper() { super(); }

		@Override
		public V next() {
			if(!hasNext())
				throw new NoSuchElementException();
			return (V) arrayKey[index++].value;
		}
	}
	
//	-----------------------------------------------
//				Node inner class
	private class TNode<K, V>{
		private K key;
		private V value;
		private TNode<K, V> leftChild;
		private TNode<K, V> rightChild;
		
		public TNode(K k, V v) {
			key = k;
			value = v;
			leftChild = rightChild = null;
		}
	}
}