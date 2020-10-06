import java.util.Random;


public class main {

	private int size = 0;
	private Node head = null;

	
	private static class Node{
		private int data = 0;
		private Node next = null;
		
		public Node() {}
		
		public Node(int obj) {
			data = obj;
		}
		public Node(int obj, Node nextNode) {
			data = obj;
			next = nextNode;
		}
	}
	
	public int size() {return size;}
	
	private static int compareTo(int a, int b)
	{
		if(a == b)
			return 0;
		if(a < b)
			return -1;
		return 1;
	}
	
	public boolean insert(int obj) {
		Node hold = new Node();
		hold.next = head;
		Node target = hold;
		
		while(target.next != null)
			target = target.next;
		
		Node newNode = new Node(obj, target.next);
		target.next = newNode;
		head = hold.next;
		
		size++;
		return true;
	}

	public int remove() {
		Node hold = new Node();
		Node target = new Node();
		hold.next = head;
		target = hold;
		Node priority = target;
		
		if(!isEmpty()) {
			while(target.next != null) {
				if(compareTo(priority.next.data, target.next.data) > 0)
					priority = target;
				target = target.next;
			}
			
			int data = priority.next.data;
			priority.next = priority.next.next;
			head = hold.next;
			size--;
			return data;
		}	
		return -1;
	}

	public boolean delete(int obj) {
		int previousSize = size;
		
		Node hold = new Node();
		hold.next = head;
		Node target = hold;
		
		while(target.next != null)
			if(compareTo(obj,target.next.data) == 0) {
				target.next = target.next.next;
				size--;
			}else
				target = target.next;
		
		head = hold.next;
		
		return previousSize != size;
	}

	public int peek() {
		int object = 0;
		Node target = head;
		if(size != 0)
		{
			object = head.data;
			while(target != null) {
				if(compareTo(object, target.data) > 0) 
					object = target.data;
				target = target.next;
			}
		}
		return object;
	}

	public boolean isEmpty() {return size == 0;}
	
	public boolean contains(int obj) {
		Node target = head;
		while(target != null){
			if(compareTo(obj,target.data) == 0)
				return true;
			target = target.next;
		}
		return false;
	}
	
	public void print()
	{
		Node target = head;
		while(target != null) {
			System.out.print(target.data + " ");
			target = target.next;
		}
		System.out.println();
	}
	
	public void clear() {
		head = null;
		size = 0;
	}
	
//	public static void main(String[] args) {
//		main test =  new main();
//		Random random = new Random();
//		
//		int insertion = random.nextInt(20);
//		
//		for(int i = 0; i < insertion; i++)
//			test.insert(random.nextInt(99));
//		
//		test.insert(4);
//		test.insert(8);
//		test.insert(487);
//		test.insert(3);
//		test.insert(6);
//		test.insert(13);
//		
//		test.print();
//		
//		System.out.println("Peeking: " + test.peek());
//		
//		System.out.println("Size: " + test.size());
//		System.out.println("Removing: " + test.remove());
//		test.print();
//		System.out.println("Size: " + test.size());
//		
//		System.out.println("\nDone testing");
//	}
}
