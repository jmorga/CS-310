import java.util.Scanner;
import java.util.Random;

public class main {
	

//							 {9,9,8,6,4,3,3,3,2,2,2, 2, 0, 0, 0};
//                            0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22
	
    private static int capacity = 15;
	private static int lastIndex;
	private static int[] i = new int[capacity]; // {9,9,8,6,4,3,3,3,2,2,2, 2, 0, 0, 0, -1};
//												   0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22
	private static int size = 15;
	
	
//	public static void main(String[] args) {
//
//		int search = 9;
//		Random random = new Random();
//		
//		Scanner scan = new Scanner(System.in);
//	
//		for(int x = 0; x < i.length; x++)
//			i[x] = random.nextInt(10);
//		
//		for(int x = 0; x < i.length; x++)
//			System.out.print(i[x] + " ");
//		
//		System.out.println("\nDelete "  + search + ": " + delete(search) + " Size: " + size);
//		
//		for(int x = 0; x < i.length; x++)
//			System.out.print(i[x] + " ");
//	}

	public static int remove() {
		int object = -1;
	
		if(size != 0) {
			object = i[0];
			shiftLeft(0);
		}
		return object;
	}

	private static boolean delete(int obj) {
		int previousSize = size;
		int jump = 0;
		int holdIndex = 0;
		
		for(int x = 0; x < size; x++) {
			holdIndex = x;
			
			if(compareTo(obj, i[x + jump]) == 0) {
				jump++;
				size--;
				x--;	
			}
			if(holdIndex == x)
				i[x] = i[x + jump];
		}
		
		return previousSize != size;
	}
	
	public static void shiftLeft(int start) {
		for(int x = start; x < size - 1; x++)
			i[x] = i[x + 1];
	}
	
	public static int peek() {
		if(size != 0)
			return i[0];
		return -1;
	}
	
	public static boolean contains(int target) {
		for(int index = 0; index < i.length; index++)
			if(compareTo(target, i[index]) == 0)
				return true;
		return false;
	}
	
/*	
	public static boolean delete(int obj) {
		int min = binarySearchMin(obj);
		int max = binarySearchMax(obj);
		int difference = max - min + 1;
		
		if(min != -1) {
			shiftLeft(min, difference);
		}
		return false;
	}
	
	public static void shiftLeft(int start, int jump) {
		size = size - jump;
		while(start != size) {
			i[start] = i[start++ + jump];
		}
	}
	
	public static boolean insert(int obj) {
		if(size == capacity)
			return false;
		
		if(size == 0)
			i[size++] = obj;
		else {
			int index = binarySearchMin(obj);
			
			if(index != -1)
				shiftRight(index);
			else {
				index = lastIndex;
				if(compareTo(obj, i[index]) < 0)
					shiftRight(++index);
				else if(compareTo(obj, i[index]) > 0)
					shiftRight(index);
			}
			
			i[index] = obj; size++;
		}
		return true;
	}

	private static void shiftRight(int stop){
		for(int x = size; x != stop; x--)
			i[x] = i[x - 1];
	}

	
	public static int binarySearchMin(int target)
	{
		int min = 0; 
		int max = size - 1;
		int middle;
		
		while(min < max){
			middle = (min + max)/ 2;
			if(compareTo(target, i[middle]) >= 0)
				max = --middle;
			else
				min = ++middle;
		}
		if(compareTo(target, i[min]) == 0)
			return min;
		
		lastIndex = min;
		return -1;
	}
	
	public static int binarySearchMax(int target)
	{
		int min = 0; 
		int max = size - 1;
		int middle;
		
		while(min < max){
			middle = (min + max)/ 2;
			
			if(compareTo(target, i[middle]) == 0 && compareTo(target, i[middle + 1]) > 0)
				return middle;
			else if (compareTo(target, i[middle ]) > 0)
				max = --middle;
			else
				min = ++middle;
		}
		if(compareTo(target, i[min]) == 0)
			return min;
		
		lastIndex = min;
		return -1;
	}
	*/
	private static int compareTo(int x, int y)
	{
		if(x == y)
			return 0;
		if(x < y)
			return -1;
		return 1;
	}
}
