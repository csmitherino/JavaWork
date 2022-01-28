import java.util.Scanner;

public class Sorter {

	public static void main (String [] args){
		
		String [] names = new String[10];
		String[] sort = new String[10];
		int points = 0;
		Scanner keyboard = new Scanner(System.in);
		String input;
		
		
		for (int i =0; i <10; i++){
			System.out.println("Enter name " + (i+1) + "/10:");
			input = keyboard.nextLine();
			input = input.toLowerCase();
			names[i] = input;
		}
		
		
		
		
		for (int i = 0; i <10; i++){
			
			for (int j = 0; j <10; j++){
				
				if (names[i].compareTo(names[j]) < 0){
					points++;
				}
				
			}
			
			if (points == 9){
				sort[0] = names[i];
			}else if (points == 8){
				sort[1] = names[i];
			}else if (points == 7){
				sort[2] = names[i];
			}else if (points == 6){
				sort[3] = names[i];
			}else if (points == 5){
				sort[4] = names[i];
			}else if (points == 4){
				sort[5] = names[i];
			}else if (points == 3){
				sort[6] = names[i];
			}else if (points == 2){
				sort[7] = names[i];
			}else if (points == 1){
				sort[8] = names[i];
			}else if (points == 0){
				sort[9] = names[i];
			}
			
			points = 0;
		}
		
		for (int i = 0 ; i<10; i++){
			names[i] = sort[i];
		}
		
		for (int i = 0; i< 10; i++){
			System.out.println(names[i]);
		}
		
	}
	
	
	
}
