import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class ComplexityTester {

	public static void main(String[] args) {
		
		long start = 0, stop = 0;
		int n = 1;
		int limit = 51200;
		
		PrintWriter insertion = null;
        try{
        		insertion = new PrintWriter(new FileOutputStream("insertion.txt"));
        }
        catch(FileNotFoundException e) {
    		System.out.println("Putos de mierda");
        }
        
        PrintWriter timeInsert = null;
        try{
        		timeInsert = new PrintWriter(new FileOutputStream("timeInsert.txt"));
        }
        catch(FileNotFoundException e) {
        		System.out.println("Putos de mierda");
        }
		
        PrintWriter timeRemove = null;
        try{
        		timeRemove = new PrintWriter(new FileOutputStream("timeRemove.txt"));
        }
        catch(FileNotFoundException e) {
        		System.out.println("Putos de mierda");
        }
        
		PriorityQueue<Integer> pq = 
//                new OrderedArrayPriorityQueue<Integer>(512000);
//                new UnorderedArrayPriorityQueue<Integer>(512000);
//                new BinaryHeapPriorityQueue<Integer>();
                new UnorderedLinkedListPriorityQueue<Integer>(); 
//				 new OrderedLinkedListPriorityQueue<Integer>(); 
		
//		Insertion measurements
		timeInsert.println("Timing Insertion\n");
		insertion.println("n inserted\n");
		timeRemove.println("Timing Removal\n");
		while(n < limit) {
			
			start = System.nanoTime();
			for(int i=0; i < n; i++) {
                int x = (int) (Integer.MAX_VALUE * Math.random());
                pq.insert(x);
            }
			stop = System.nanoTime();
			
			timeInsert.println((stop - start) * 0.001);
			insertion.println(n);
			
			start = System.nanoTime();
			while(!pq.isEmpty())
				pq.remove();
			stop = System.nanoTime();
			
			timeRemove.println((stop - start) * 0.001);
			
			n += n;
			pq.clear();
		}
		
		timeRemove.close();
		timeInsert.close();
		insertion.close();
		
		System.out.println("Done!!");
	}

}
