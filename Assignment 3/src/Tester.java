import java.util.Random;

public class Tester {
	public static int size = 0;
	private int capacity = 20;
	private static int[] heap = null;
	
	public Tester(int newCapacity) {
		capacity = newCapacity;
		heap =  new int[capacity];
	}
	
	private static int compareTo(int x, int y)
	{
		if(x == y)
			return 0;
		if(x < y)
			return -1;
		return 1;
	}
	
	private void swap(int parent, int child) {
		int hold = heap[parent];
		heap[parent] = heap[child];
		heap[child] = hold;
	}
	
	public Tester() {
		heap = new int[capacity];
	}
	
	public boolean isEmpty() { return size == 0; }
	
	public boolean isFull() { return size == capacity; }
	
	public boolean insert(int object) {
		if(isFull())
			return false;
		
		heap[size] = object;
		int child = size++;
		int parent = (child - 1) / 2;
		
		while(parent >= 0 && heap[parent] > heap[child]) {
			swap(parent, child);
			child = parent;
			parent = (child - 1) / 2;
		}	
		
		return true;
	}
	
	public int remove() {
		if(isEmpty())
			return -1;
		
		int target = heap[0];
		int parent = 0;
		heap[0] = heap[--size];
		sortHeap(parent);
		
		return target;
	}

	private void sortHeap(int parent) {

		int rightChild, leftChild;
		
		while(true) {
			leftChild = (2 * parent) + 1;
			rightChild = leftChild + 1;
			
			if(leftChild >= size)
				break;
			
//			Assuming left child is the smaller child
			if(leftChild < size && heap[rightChild] < heap[leftChild])
				leftChild = rightChild;
			if(heap[parent] > heap[leftChild]) {
				swap(parent, leftChild);
				parent = leftChild;
			} else
				break;	
		}
	}
	
	public boolean delete(int obj) {
		int currentSize = size;
		
		for(int parent = 0; parent < size; parent++)
			if(obj == heap[parent]) {
				heap[parent] = heap[--size];
				sortHeap(parent--);
			}
				
		return currentSize != size;
	}

	public boolean contains(int obj) {
		
		for(int i = 0; i < size; i++)
			if(obj == heap[i])
				return true;
		
		return false;
	}
//	public static void main(String[] args) {
//		Random random = new Random();
//		
//		Tester test = new Tester(10);
//		
//		
//		test.insert(2);
//		test.insert(0);
//		test.insert(1);
//		test.insert(4);
//		test.insert(3);
//		test.insert(4);
//		test.insert(4);
//		test.insert(5);
//		test.insert(6);
//		test.insert(5);
//		
////		for(int i = 0; i < 10; i++)
////			test.insert(random.nextInt(10));
//		
//		for(int i = 0; i < size; i++)
//			System.out.print(heap[i] + " ");
//		
//		System.out.println("\nRemoving root!!!");
//		System.out.println(test.remove() + " removed!!!");
//		for(int i = 0; i < size; i++)
//			System.out.print(heap[i] + " ");
//		
//		System.out.println("\nDoes 4 exist in the heap: " + test.contains(4));
//		System.out.println("Does 40 exist in the heap: " + test.contains(40));
//		
//		System.out.println("\nDeleting 4s " + test.delete(4));
//		for(int i = 0; i < size; i++)
//			System.out.print(heap[i] + " ");
//		
//		System.out.println("\n\nDeleting 40s " + test.delete(40));
//		for(int i = 0; i < size; i++)
//			System.out.print(heap[i] + " ");
//		
//		System.out.println("\nDone Testing!!!");
//	}
}
