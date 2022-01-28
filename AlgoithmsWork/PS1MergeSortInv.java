/**************************************************
Student Name:		Cale Smith
Problem Set:		PS1
Student User Name:	ua310   
Date:				8-26-2019
**************************************************/

public class PS1MergeSortInv {
	
	static int inversions = 0;

	public static void main( String[] args ) {
	
		int[] A = { 5, 3, 1, 2, 4};
		//6 Inversions (5,3) (5,1) (5,2) (5,4) (3, 1) (3, 2)
		int[] B = mergeSort( A, 0, ( A.length - 1 ) );
		for(int i = 0; i < B.length; i++ ) {
			System.out.print( B[i] + " " );
		}
		System.out.println();
		System.out.println("Inversions: " + inversions);
	}
	
	public static int[] mergeSort( int[] A, int p, int r) {

		if(p < r){
			int q = (p+r)/2;
			
			
			mergeSort(A, p, q);
			mergeSort(A, q+1, r);
			merge(A, p, q, r);
		}

		return A;
	}

	public static void merge ( int [] A , int p , int q , int r ) {

		int s1 = q-p+1;
		int s2= r-q;
		
		int[] L = new int[s1+1];
		int[] R = new int[s2+1];
		
		for (int i = 0; i<s1; i++){
			L[i] = A[p+i];
		}
		
		for (int i = 0; i < s2; i++){
			R[i] = A[q+i+1];
		}

		L[s1] = Integer.MAX_VALUE;
		R[s2] = Integer.MAX_VALUE;
		
		int i = 0;
		int j = 0;
		
		for (int k = p; k<=r; k++){
			if(L[i] <= R[j]){
				A[k] = L[i];
				i++;
			}else{
				A[k] = R[j];
				j++;
				//Add leftsize (minus how many left elements have already been sorted) to inversions when an element is taken from the right array
				inversions+= s1-i;
			}
		}
		
	} 

}