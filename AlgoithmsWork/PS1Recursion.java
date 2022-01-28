/**************************************************
Student Name:		Cale Smith
Problem Set:		PS1
Student User Name:	ua310   
Date:				8-26-2019
**************************************************/
public class PS1Recursion {
	//This method doesn't use j. I've kept it there to be consistent with the instructions.
	public static double getOddAverage(int[] arr, int total, int i, int j){
		

		if (i != arr.length){
			
			if(i%2 != 0){
				total+=arr[i];
			}
			
			return getOddAverage(arr, total, i+1, j);
			
		}
		
		
		return (double)total ;
	}
	
	//Overload method that takes only the array and sets the other values to 0
	public static double getOddAverage(int[] arr){
		return getOddAverage(arr,0,0,0);
	}
	
	
	
	
	public static void main(String[] args){
		
		int[] arr = {1,2,3,4,5,6,7,8,9,10};
		
		//Should print 2+4+6+8+10 = 30.0
		System.out.println("Odd Total:  " + getOddAverage(arr));
		
		
	}
	
}
