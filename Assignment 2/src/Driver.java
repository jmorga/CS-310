import java.util.Iterator;

public class Driver {
    private int [] array;
    private static final int SIZE = 100;
    private PriorityQueue<Integer> pq;
    private PriorityQueue<PrioritizedItem> pq2;    
    
    public Driver() {
        array = new int[SIZE];
        pq = new OrderedLinkedListPriorityQueue<Integer>();
//        pq = new UnorderedLinkedListPriorityQueue<Integer>();
      
        pq2 = new OrderedLinkedListPriorityQueue<PrioritizedItem>();
//        pq2 = new UnorderedLinkedListPriorityQueue<PrioritizedItem>();                     
        initArray();
        test1();
        test2();
        test3(); 
        test4(); 
        test5(); 
//        test6();
//        test7();
//        test8();
//        test9();
//        test10();
//        test11();
//        test12();
//        test13();
//        test14();
//        test15();
//        test16();
//        test17();
        }
        
    private void initArray() {
        for(int i=0; i < SIZE; i++)
            array[i] = i+1;
        // now scramble array order
        for(int i=0; i < SIZE; i++) {
            int idx = (int) (SIZE*Math.random());
            int tmp = array[i];
            array[i] = array[idx];
            array[idx] = tmp;
            }
        }
        
    private void test1() {
        pq.clear();
        for(Integer i : pq)
            throw new RuntimeException("Failed test #1, value returned in empty iterator");
        
        for(int i=0; i < SIZE; i++)  {
            if(!pq.insert(array[i])) 
                throw new RuntimeException("Failed test #1");
        }
//////////////////////////////////////////////////////////////////////////////                
//  Comment this block for linked list based implementations         
//       if(!pq.isFull())
//            throw new RuntimeException("Failed test #1, isFull reports false, but pq should be full");       
        //try to exceed the capacity
//        if(pq.insert(0))
//            throw new RuntimeException("Failed test1, exceeded capacity");
//////////////////////////////////////////////////////////////////////////////       
        System.out.println("Passed test #1, simple insert");
        }
        
    private void test2() {
    	//for(int i:pq)
    		//System.out.print(i + " ");
		//System.out.println();
        for(int i=0; i < SIZE; i++) {
        	int remove = pq.remove();
//        	displayIntegers();
//        	System.out.println(remove + " was removed");
//        	for(int j:pq)
//        		System.out.print(j + " ");
//    		System.out.println();
            if(remove != (i+1)) {
            	//System.out.println(remove);
                throw new RuntimeException("Failed test #2, out of order removal");
            }    
        }
                
        if(pq.remove() != null)
            throw new RuntimeException("Failed test #2, removal from empty pq did not return null");
            
        if(!pq.isEmpty())
            throw new RuntimeException("Failed test #2, isEmpty reports false, but pq is empty");
            
        System.out.println("Passed test #2, simple removal");
        }
        
    private void test3() { // check FIFO behavior
        int size=10;
        pq2.clear();
        int sequenceNumber = 0;
        int midPoint = size >> 1;
        
        for(int i=0; i < midPoint; i++)
            pq2.insert(new PrioritizedItem(2,sequenceNumber++));
        for(int i=midPoint; i < size; i++)
            pq2.insert(new PrioritizedItem(1,sequenceNumber++));
            
        PrioritizedItem item = pq2.peek();
        System.out.println("Peek returned " + item);
        if(item.getPriority() != 1 || item.getSequenceNumber() != 5)
            throw new RuntimeException("Failed test #3, peek returns wrong element");

        sequenceNumber = midPoint;
        for(int i=0; i < midPoint; i++) {
            PrioritizedItem tmp = pq2.remove();
            if(tmp.getPriority() != 1)
                throw new RuntimeException("Failed test #3, out of order removal");
            if(tmp.getSequenceNumber() != (sequenceNumber++))
                throw new RuntimeException("Failed test #3, out of order removal");
            }
            
        sequenceNumber = 0;    
        for(int i=midPoint; i < size; i++) {
            PrioritizedItem tmp = pq2.remove();
            if(tmp.getPriority() != 2)
                throw new RuntimeException("Failed test #3, out of order removal");
            if(tmp.getSequenceNumber() != (sequenceNumber++))
                throw new RuntimeException("Failed test #3, out of order removal");                
            }
        System.out.println("Passed test #3, FIFO check");                                   
                 
        }
        
    private void test4() {
        pq2.clear();
        int sequenceNumber = 0;
        System.out.println("\nNow checking iterators, output is below.");
        System.out.println("NOTE: No specific order is required for these iterators.");
        for(int i=0; i < 5; i++)
            pq2.insert(new PrioritizedItem(10,sequenceNumber++));
        for(int i=0; i < 5; i++)
            pq2.insert(new PrioritizedItem(1,sequenceNumber++));    
        for(int i=0; i < 5; i++)
            pq2.insert(new PrioritizedItem(5,sequenceNumber++));                        
            
        for(PrioritizedItem item : pq2)
            System.out.println(item);
            
        System.out.println("Now removing elements with priority=5");
        pq2.delete(new PrioritizedItem(5,100));
            
        System.out.println("\nNow removing items, they should be in proper order,\n"+
                            "with all priority=5 items removed.");
        while(!pq2.isEmpty())
            System.out.println(pq2.remove());
            
        if(pq2.size() != 0)
            System.out.println("Failed test #4, size is wrong.");
        }
        
    private void test5() {
        final int TEST_5_SIZE = 1000;
//        pq = new OrderedLinkedListPriorityQueue<Integer>();
        pq = new UnorderedLinkedListPriorityQueue<Integer>();
        
        for(int i=0; i<TEST_5_SIZE; i++) {             
            int someInteger = (int) (TEST_5_SIZE*Math.random());                                
            if(!pq.insert(someInteger)) {
                System.out.println("ERROR in test 5, insertion failed!");
                System.exit(1);
                }
            }
        int removed = pq.remove();
        for(int i=1; i < TEST_5_SIZE; i++) {
            int removed2 = pq.remove();
            // System.out.println(removed2);            
            if(removed2 < removed) {
                System.out.println("ERROR, out of order removal in test 5");
                System.exit(1);
                }
            removed = removed2;
            }
        System.out.println("Passed test #5");
        }
    
    private void test6() {
    	final int TEST_6_SIZE = 1000;
//    	pq = new OrderedLinkedListPriorityQueue<Integer>();
        pq = new UnorderedLinkedListPriorityQueue<Integer>();
    	for(int i=0; i<TEST_6_SIZE; i++) {
    		pq.insert(5);
    	}
    	System.out.println("Test 6 contains #5: " + pq.contains(5));
    	System.out.println("Test 6 contains #1: " + pq.contains(1));
    	
    	
    }
    
    private void test7() {
//    	pq = new OrderedLinkedListPriorityQueue<Integer>();
        pq = new UnorderedLinkedListPriorityQueue<Integer>();
        System.out.println("Test 7 on empty array: " + pq.contains(24));
    }
    
    private void test8() {
    	final int TEST_8_SIZE = 1000;
//    	pq = new OrderedLinkedListPriorityQueue<Integer>();
        pq = new UnorderedLinkedListPriorityQueue<Integer>();
    	for(int i=0; i<TEST_8_SIZE; i++) {
    		pq.insert(5);
    	}
    	for(int j:pq)
    		System.out.print(j + " ");
		System.out.println();
		
    	pq.delete(5);
    	
    	for(int j:pq)
    		System.out.print(j + " ");
		System.out.println();
    	System.out.println("Test 8 delete all elements of #5: " + pq.isEmpty());
    }
    
    private void test9() {
    	final int TEST_9_SIZE = 1000;
//    	pq = new OrderedLinkedListPriorityQueue<Integer>();
        pq = new UnorderedLinkedListPriorityQueue<Integer>();
    	
    	pq.insert(1);
    	pq.insert(2);
    	pq.insert(3);
    	pq.insert(4);
    	pq.insert(5);
    	pq.insert(6);
    	
    	for(int j:pq)
    		System.out.print(j + " ");
		System.out.println();
		
		pq.insert(2); //change me!!
    
		for(int j:pq)
    		System.out.print(j + " ");
		System.out.println();
		
		System.out.println("Test 9 for dupicate elements");	
    }
    
    private void test10() {
//    	pq = new OrderedLinkedListPriorityQueue<Integer>();
        pq = new UnorderedLinkedListPriorityQueue<Integer>();
        
        pq.insert(10);
        pq.insert(9);
        
        for(int j:pq)
    		System.out.print(j + " ");
		System.out.println();
		
		System.out.println("Test 10 for inserting at an empty array");
    }
    
    private void test11() {
    	final int TEST_11_SIZE = 10;
//    	pq = new OrderedLinkedListPriorityQueue<Integer>();
        pq = new UnorderedLinkedListPriorityQueue<Integer>();
    	
    	for(int i=0; i<TEST_11_SIZE; i++) {
    		pq.insert(i);
    	}
    	
    	for(int j:pq)
    		System.out.print(j + " ");
		System.out.println();
		
		System.out.println("Test 11, trying to insert on a full array: " + pq.insert(24));
		
		for(int j:pq)
    		System.out.print(j + " ");
		System.out.println();
    }
    
    private void test12() {
    	final int TEST_12_SIZE = 10;
//    	pq = new OrderedLinkedListPriorityQueue<Integer>();
        pq = new UnorderedLinkedListPriorityQueue<Integer>();
    	
    	for(int i=0; i<TEST_12_SIZE; i++) {
    		pq.insert(i);
    	}
    	
    	for(int j:pq)
    		System.out.print(j + " ");
		System.out.println();
		
		System.out.println("Test 12, trying to delete an element that is not in the array: " + pq.delete(24));
    }
    
    private void test13() {
    	final int TEST_13_SIZE = 10;
//    	pq = new OrderedLinkedListPriorityQueue<Integer>();
        pq = new UnorderedLinkedListPriorityQueue<Integer>();
    	
    	for(int i=0; i<TEST_13_SIZE; i++) {
    		pq.insert(-3);
    	}
    	
    	for(int j:pq)
    		System.out.print(j + " ");
		System.out.println();
		
		pq.delete(-3);
		
		for(int j:pq)
    		System.out.print(j + " ");
		System.out.println();
    	
		System.out.println("Test 13, array with -3 and then deletion: ");
    }
    
    private void test14() {
    	final int TEST_14_SIZE = 10;
//    	pq = new OrderedLinkedListPriorityQueue<Integer>();
        pq = new UnorderedLinkedListPriorityQueue<Integer>();
    	
    	pq.insert(1);
    	pq.insert(2);
    	pq.insert(3);
    	pq.insert(4);
    	pq.insert(5);
    	
    	for(int j:pq)
    		System.out.print(j + " ");
		System.out.println();
		
		System.out.println("Gonna insert a -1");
		
		pq.insert(-1);
		
		for(int j:pq)
    		System.out.print(j + " ");
		System.out.println();
		
		System.out.println("Test 14, inserting a negative in the array");
    }
    
    private void test15() {
    	final int TEST_15_SIZE = 10;
//    	pq = new OrderedLinkedListPriorityQueue<Integer>();
        pq = new UnorderedLinkedListPriorityQueue<Integer>();
    	
    	pq.insert(-50);
    	pq.insert(-40);
    	pq.insert(-30);
    	pq.insert(-20);
    	pq.insert(-10);
    	
    	for(int j:pq)
    		System.out.print(j + " ");
		System.out.println();
		
		System.out.println("Test 15: remove with negative numbers " + pq.remove());
    }
    
    private void test16() {
    	final int TEST_16_SIZE = 10;
//    	pq = new OrderedLinkedListPriorityQueue<Integer>();
        pq = new UnorderedLinkedListPriorityQueue<Integer>();
    	
    	pq.insert(1);
    	pq.insert(2);
    	pq.insert(3);
    	pq.insert(4);
    	pq.insert(5);
    	
    	for(int j:pq)
    		System.out.print(j + " ");
		System.out.println();
		
		System.out.println("Gonna peek " + pq.peek());
		
		for(int j:pq)
    		System.out.print(j + " ");
		System.out.println();
		
    	System.out.println("Test 16 testing the peek method.");
    }
    
    private void test17() {
    	final int TEST_17_SIZE = 10;
//    	pq = new OrderedLinkedListPriorityQueue<Integer>();
        pq = new UnorderedLinkedListPriorityQueue<Integer>();
    	
    	pq.insert(-50);
    	pq.insert(-40);
    	pq.insert(-30);
    	pq.insert(-20);
    	pq.insert(-10);
    	
    	for(int j:pq)
    		System.out.print(j + " ");
		System.out.println();
		
		System.out.println("Gonna peek " + pq.peek());
		
		for(int j:pq)
    		System.out.print(j + " ");
		System.out.println();
		
    	System.out.println("Test 17 testing the peek method with negative.");
    }
    
    private void displayIntegers() {
    	Iterator<Integer> it = pq.iterator();
    	System.out.println("/**** PRINTING CONTENTS OF INTEGER PQ ****/");
    	int mod = 0;
    	while(it.hasNext()) {
    		System.out.printf("%4d ", it.next());
    		mod++;
    		if(mod % 10 == 0)
    			System.out.println();
    	}
    	System.out.println();
    	System.out.println("/**** ------ DONE PRINTING ------ ****/\n\n");
    		
    }
    
    ///////////////////////////////////////////////////////////////////////   
    private class PrioritizedItem implements Comparable<PrioritizedItem>{
        private int priority;
        private int itemNumber;
        
        public PrioritizedItem(int p, int n) {
            priority = p;
            itemNumber = n;
            }
            
        public int compareTo(PrioritizedItem item) {
            return priority - item.priority;
            }
            
        public String toString() {
            return "Priority: " + priority + " Item Number: " + itemNumber;
            }
            
        public int getPriority() {
            return priority;
            }
            
        public int getSequenceNumber() {
            return itemNumber;
            }
        }
    ///////////////////////////////////////////////////////////////////////
        
    public static void main(String [] args) {
        new Driver();
        }
    }
