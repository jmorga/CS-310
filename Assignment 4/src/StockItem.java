//Programmer: Joseph Morga
//Red ID: 817281186
//File Name: StockItem
//Programming Assignment # 4
//Date Created: 04/15/2018
//Date Last Modified: 05/03/2018
//Professor: Patty Kraft
//Class: CS310: Data Structures

//package Store;
//import data_structures.*;
import java.util.Iterator;

public class StockItem implements Comparable<StockItem> {
	String SKU;
    String description;
    String vendor;
    float cost;
    float retail;
    
    // Constructor. Creates a new StockItem instance.
    public StockItem(String SKU, String description, String vendor, float cost, float retail) {
    		this.SKU = SKU;
    		this.description = description;
    		this.vendor = vendor;
    		this.cost = cost;
    		this.retail = retail;
    }
    
    // Follows the specifications of the Comparable Interface.
    // The SKU is always used for comparisons, in dictionary order. 
    public int compareTo(StockItem n) {
    	return SKU.compareTo(n.SKU);
    }
    
    // Returns an int representing the hashCode of the SKU. 
    public int hashCode() { return SKU.hashCode(); }
    
    // standard get methods
    public String getDescription() { return description; } 
    
    public String getVendor() { return vendor; }
    
    public float getCost() { return cost; }
    
    public float getRetail() { return retail; }
    
    // All fields in one line, in order
    public String toString() { 
    		return "Description: " + description + " Vendor: " + 
                vendor + " Cost: " + cost + " Retail: " + retail;
    }
}
