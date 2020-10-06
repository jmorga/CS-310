//Programmer: Joseph Morga
//Red ID: 817281186
//File Name: ProductLookup
//Programming Assignment # 4
//Date Created: 04/15/2018
//Date Last Modified: 05/03/2018
//Professor: Patty Kraft
//Class: CS310: Data Structures

//package Store;
import java.util.Iterator;
//import data_structures.*;

public class ProductLookup {

	private DictionaryADT<String, StockItem> dictionary;
	
	// Constructor. There is no argument-less constructor, or default size
	public ProductLookup(int maxSize) {
		dictionary = new Hashtable<>(maxSize);
	}
		
	// Adds a new StockItem to the dictionary
    public void addItem(String SKU, StockItem item) { dictionary.add(SKU, item); }
	
    // Returns the StockItem associated with the given SKU, if it is
    // in the ProductLookup, null if it is not.
    public StockItem getItem(String SKU) { return dictionary.getValue(SKU); }
    
    // Returns the retail price associated with the given SKU value. 
    // -.01 if the item is not in the dictionary
    public float getRetail(String SKU) {
    	if(!dictionary.contains(SKU)) return (float)-0.01;
    	
    	return dictionary.getValue(SKU).getRetail();
    }
    
    // Returns the cost price associated with the given SKU value. 
    // -.01 if the item is not in the dictionary
    public float getCost(String SKU) {
    	if(!dictionary.contains(SKU)) return (float)-0.01;
    	
    	return dictionary.getValue(SKU).getCost();
    }
    
    // Returns the description of the item, null
    public String getDescription(String SKU) {
    	if(!dictionary.contains(SKU)) return null;
    	
    	return dictionary.getValue(SKU).getDescription();
    }

	// Deletes the StockItem associated with the
	// in the ProductLookup.  Returns true if it
    // deleted, otherwise false.
	public boolean deleteItem(String SKU) { return dictionary.delete(SKU); }
	 
	// Prints a directory of all StockItems with
	// price, in sorted order (ordered by SKU).
	public void printAll() {
		Iterator<StockItem> iterator = dictionary.values();
		
		while(iterator.hasNext())
			System.out.println(iterator.next().toString());
	}
	
	// Prints a directory of all StockItems from
	// in sorted order (ordered by SKU).
	public void print(String vendor) {
		Iterator<StockItem> iterator = dictionary.values();
		
		while(iterator.hasNext()) {
			StockItem stock = iterator.next();
			if(stock.getVendor().compareTo(vendor) == 0)
				System.out.println(stock.toString());
		}
	}
	
	// An iterator of the SKU keys.
    public Iterator<String> keys(){ return dictionary.keys(); }
    
    // An iterator of the StockItem values.
    public Iterator<StockItem> values(){ return dictionary.values(); }
}
