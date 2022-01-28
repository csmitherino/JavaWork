import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class UAFindMyClass {

	ArrayList<UANode> nodes = new ArrayList<>();
	UANode[][] map;
	UANode startNode;
	UANode endNode;
	
	public static void main(String[] args) throws IOException {
		
		UAFindMyClass c = new UAFindMyClass();
	
		c.loadMaze(args[1]);
		
		if(args[0].equals("dfs")) {
			c.depthFirstSearch(args[2]);
		}else if (args[0].equals("bfs")) {
			c.breadthFirstSearch(args[2]);
		}else {
			System.out.println("Invalid Operation!");
		}
		
		
		
		
	}
	
	
	public void loadMaze(String filename) throws IOException {
		
		ArrayList<String> lines = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(filename));
		
		String in;
		
		int i = 0;
		while((in = br.readLine()) != null) {
			
			lines.add(in);
			i++;
		}
		
		map = new UANode[i][lines.get(0).length()];
		
		int line = 0;
		for(String s : lines) {
			int character = 0;
			for (char c : s.toCharArray()) {
				if(c == ' ' || c == 'S' || c == 'M') {
					map[line][character] = new UANode();
				}
				character++;
			}
			line++;
		}
		
		
		//System.out.println();
		
				for(  i = 0; i < map.length; i++) {
					for (int j=0; j < map[0].length; j ++) {
						
						
						if(map[i][j] == null) {
							//System.out.print("*");
						}else {
							//System.out.print(map[i][j]);
						}
					}
					//System.out.println();
				}
		
		
		line = 0;
		for(String s : lines) {
			int character = 0;
			for(char c : s.toCharArray()) {
				if(c == '*') {
				}else if(c == ' ') {
					UANode n = map[line][character];
					UANode m;
					if(line > 0 && (m = map[line-1][character]) != null) {
						n.getAdjecent().add(m);
					}
					if(line < map.length-1 && (m = map[line+1][character]) != null) {
						n.getAdjecent().add(m);
					}
					if(character > 0 && (m = map[line][character-1]) != null) {
						n.getAdjecent().add(m);
					}
					if(character < map[0].length-1 && (m = map[line][character+1]) != null) {
						n.getAdjecent().add(m);
					}
					
					nodes.add(n);
					
				}else if(c == 'S') {
					
					UANode n = new UANode();
					map[line][character] = n;
					startNode = n;
					UANode m;
					
					if(line > 0 && (m = map[line-1][character]) != null) {
						n.getAdjecent().add(m);
						//m.getAdjecent().add(n);
					}
					if(line < map.length-1 && (m = map[line+1][character]) != null) {
						n.getAdjecent().add(m);
						//m.getAdjecent().add(n);
					}
					if(character > 0 && (m = map[line][character-1]) != null) {
						n.getAdjecent().add(m);
						//m.getAdjecent().add(n);
					}
					if(character < map[0].length-1 && (m = map[line][character+1]) != null) {
						n.getAdjecent().add(m);
						//m.getAdjecent().add(n);
					}
					
					nodes.add(n);
					
				}else if(c == 'M') {
					
					UANode n = new UANode();
					map[line][character] = n;
					endNode = n;
					UANode m;
					
					if(line > 0 && (m = map[line-1][character]) != null) {
						n.getAdjecent().add(m);
						//m.getAdjecent().add(n);
					}
					if(line < map.length-1 && (m = map[line+1][character]) != null) {
						n.getAdjecent().add(m);
						//m.getAdjecent().add(n);
					}
					if(character > 0 && (m = map[line][character-1]) != null) {
						n.getAdjecent().add(m);
						//m.getAdjecent().add(n);
					}
					if(character < map[0].length-1 && (m = map[line][character+1]) != null) {
						n.getAdjecent().add(m);
						//m.getAdjecent().add(n);
					}
					
					nodes.add(n);
					
				}
				character++;
			}
			line++;
		}
		
	}
	
	public void depthFirstSearch(String filename) throws IOException {
		for(UANode n : nodes) {
			n.setWhite(true);
			n.setPath(false);
			n.setPredecesor(null);
		}
		
		DFSVisit(startNode);
		
		for (UANode n : nodes) {
			if(n.isWhite()) {
				DFSVisit(n);
			}
		}
		
		
		
		UANode n = endNode;
		
		
		while(n.getPredecesor() != null) {
			n.setPath(true);
			n = n.getPredecesor();
		}
		
		n.setPath(true);
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		
		
		//System.out.println();
		
		for(int  i = 0; i < map.length; i++) {
			for (int j=0; j < map[0].length; j ++) {
				
				
				if(map[i][j] == null) {
					//System.out.print("*");
				}else {
					//System.out.print(map[i][j]);
				}
				
				
				if(map[i][j] == null) {
					bw.write("*");
				}else if(map[i][j] == startNode) {
					bw.write("S");
				}else if(map[i][j] == endNode) {
					bw.write("M");
				}else if (map[i][j].getPath()) {
					bw.write("X");
				}else {
					bw.write(" ");
				}
				
			}
			//System.out.println();
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		
		
		
		
	}
	
	public void DFSVisit(UANode n) {
		n.setWhite(false);
		for(UANode m : n.getAdjecent()) {
			if(m.isWhite()) {
				m.setPredecesor(n);
				DFSVisit(m);
			}
		}
		
		
		
	}
	
	public void breadthFirstSearch(String filename) throws IOException {
		
		for(UANode n : nodes) {
			n.setWhite(true);
			n.setPath(false);
			n.setPredecesor(null);
		}
		
		Queue<UANode> q = new LinkedList<>();
		
		q.add(startNode);
		
		while(!q.isEmpty()) {
			UANode n = q.remove();
			//System.out.println(n.getAdjecent());
			for(UANode m : n.getAdjecent()) {
				if(m.isWhite()) {
					
					//System.out.print("White");
					m.setPredecesor(n);
					q.add(m);
					
				}
			}
			
			n.setWhite(false);
			
		}
		
		
		UANode n = endNode;
		
		
		while(n.getPredecesor() != null) {
			n.setPath(true);
			n = n.getPredecesor();
		}
		
		n.setPath(true);
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		
		
		//System.out.println();
		
		for(int  i = 0; i < map.length; i++) {
			for (int j=0; j < map[0].length; j ++) {
				
				
				if(map[i][j] == null) {
					//System.out.print("*");
				}else {
					//System.out.print(map[i][j]);
				}
				
				
				if(map[i][j] == null) {
					bw.write("*");
				}else if(map[i][j] == startNode) {
					bw.write("S");
				}else if(map[i][j] == endNode) {
					bw.write("M");
				}else if (map[i][j].getPath()) {
					bw.write("X");
				}else {
					bw.write(" ");
				}
				
			}
			//System.out.println();
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		
	}
	
	
	public static class UANode{
		
		private ArrayList<UANode> adjecent = new ArrayList<>();
		private UANode predecesor;
		private boolean white;
		private boolean path;
		
		public ArrayList<UANode> getAdjecent(){
			return adjecent;
		}
		
		public boolean isWhite() {
			return white;
		}
		
		public void setWhite(boolean white) {
			this.white = white;
		}
		
		public UANode getPredecesor() {
			return predecesor;
		}
		
		public void setPredecesor(UANode predecesor) {
			this.predecesor = predecesor;
		}
		
		public boolean getPath() {
			return path;
		}
		
		public void setPath(boolean path) {
			this.path = path;
		}
		
		public String toString() {
			return " ";
		}
	}
	
	
}
