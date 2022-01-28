import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UASpellCheck {

	
	private ArrayList<String> words = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		
		UASpellCheck sp = new UASpellCheck(args[0]);
		
		sp.checkSpelling(args[1]);	
		
	}
	
	public UASpellCheck(String filename) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String in;
		
		while((in = br.readLine()) != null) {
			
			words.add(in);
			
		}	
	}
	
	public boolean isValid(String word) {
		
		for(String s : words) {
			if(editDistance(word, s) == 0) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public ArrayList<String> checkSpelling(String word){
		
		if(word.length() < 1) {
			return null;
		}
		
		ArrayList<String> matches = new ArrayList<>();
		int min = Integer.MAX_VALUE;
		
		for(String s : words) {
			
			if(editDistance(word, s) < min) {
				matches.clear();
				matches.add(s);
				min = editDistance(word, s);
			}else if(editDistance(word, s) < min) {
				matches.add(s);
			}
			
		}
		
		return matches;
	}
	
	public int editDistance(String a, String b) {
		
		int[][] compare = new int[a.length()+1][b.length()+1];
		
		for (int i = 0; i < compare.length; i++) {
			for(int j = 0; j < compare[i].length; j++) {
				
				if(i == 0) {
					compare[i][j] = j;
				}else if(j == 0) {
					compare[i][j] = i;
				}else {
					
					int min;
					
					if(compare[i-1][j] < compare[i][j-1]) {
						min = compare[i-1][j];
					}else {
						min = compare[i][j-1];
					}
					
					
					if(compare[i-1][j-1] < min) {
						min = compare[i-1][j-1];
						if(a.charAt(i-1) != b.charAt(j-1)) {
							min += 2;
						}
					}else {
						min += 1;
					}
					
					compare[i][j] = min;
				}
				
			}
		}
		
		return compare[a.length()][b.length()];
		
	}
	
	
}
