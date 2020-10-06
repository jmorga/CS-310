//Programmer: Joseph Morga
//Red ID: 817281186
//File Name: BalancedTree
//Programming Assignment # 4
//Date Created: 04/20/2018
//Date Last Modified: 04/20/2018
//Professor: Patty Kraft
//Class: CS310: Data Structures

//package data_structures;

import java.util.Iterator;
import java.util.TreeMap;

public class BalancedTree <K extends Comparable<K>, V> implements DictionaryADT<K, V>{

	private TreeMap<K, V> rbTree;
	
	public BalancedTree() {
		rbTree = new TreeMap<>();
	}
	
	@Override
	public boolean contains(K key) { return rbTree.containsKey(key); }

	@Override
	public boolean add(K key, V value) {
		
		if(contains(key)) return false;
		
		rbTree.put(key, value);
		return true;
	}

	@Override
	public boolean delete(K key) { return rbTree.remove(key) != null; }

	@Override
	public K getKey(V value) {
		
		Iterator<K> key = keys();
        Iterator<V> val = values();
       
        while(val.hasNext()) {
            if(((Comparable<V>)val.next()).compareTo(value) == 0) {
                return key.next();
            }
            key.next();
        }
        return null;
	}

	@Override
	public V getValue(K key) { return rbTree.get(key); }
	
//	-----------------------------------------------
//	  				 Iterators
	@Override
	public Iterator<K> keys() { return rbTree.navigableKeySet().iterator(); }

	@Override
	public Iterator<V> values() { return rbTree.values().iterator(); }
//	-----------------------------------------------
//	 			   Other methods
	@Override
	public int size() { return rbTree.size(); }

	@Override
	public boolean isFull() { return false; }

	@Override
	public boolean isEmpty() { return rbTree.isEmpty(); }

	@Override
	public void clear() { rbTree.clear(); }
}
