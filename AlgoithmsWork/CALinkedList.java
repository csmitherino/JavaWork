import java.util.Scanner;

public class CALinkedList {

	public static class MyNode{
		public int key;
		public MyNode parent;
		public MyNode left;
		public MyNode right;		
		
		public MyNode(int key){
			this.key = key;
		}
		
		public String toString(){
			return "" + this.key;
		}
	}
	
	public static class MyBinarySearchTree{
		public MyNode root;
		
		public void insert(MyNode z){
			
			MyNode y = null;
			MyNode x = this.root;
			
			while (x != null){
				y = x;
				
				if (z.key < x.key){
					x = x.left;
				}else{
					x = x.right;
				}
			}
			
			z.parent = y;
			
			if (y == null){
				this.root = z;
			}else if (z.key < y.key){
				y.left = z;
			}else{
				y.right = z;
			}
		}
		
		public MyNode predecessor(MyNode x){
			
			if (x.left != null){
				return minimum(x.left);
			}
			
			MyNode y = x.parent;
			
			while (y != null && x == y.left){
				x = y;
				y = y.parent;
			}
			
			return y;
		}
		
		public MyNode minimum(MyNode x){
			
			while (x.left != null){
				x=x.left;
			}
			
			return x;
			
		}
		
		public MyNode search(MyNode x, int k){
			
			if (x == null || k == x.key){
				return x;
			}
			
			if (k < x.key){
				return search(x.left, k);
			}else{
				return search(x.right,k);
			}
			
		}
		
	}
	
	public static void main (String[] args){
		
		Scanner scan = new Scanner(System.in);
		MyBinarySearchTree t = new MyBinarySearchTree();
		
		String input = "notend";
		
		System.out.println("Enter a key or put end to stop: ");
		
		while (!((input = scan.nextLine()).equalsIgnoreCase("end"))){
			System.out.println("Enter a key or put end to stop: ");
			
			t.insert(new MyNode(Integer.parseInt(input)));
			
		}
		
		System.out.println("Enter a key to find the predecessor: ");
		
		int inp = scan.nextInt();
		
		System.out.println(t.predecessor(t.search(t.root, inp)));
		
		
	}
	
	
}
