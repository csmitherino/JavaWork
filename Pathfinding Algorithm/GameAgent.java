import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.TreeMap;

import javax.swing.Timer;

class GameAgent {
	/*
	 * Problem Set 3
	 * 
	 * Student code should be contained within this class.
	 * 
	 */
	
	public TreeMap<String, Node> frontier = new TreeMap<String, Node>();
	public PriorityQueue<Node> q = new PriorityQueue(new MyCompare());
	public LinkedList<Node> path = new LinkedList<>();
	public float fastSpeed = Float.MAX_VALUE;
	
	
	public static class MyCompare implements Comparator<Node>{

		@Override
		public int compare(Node o1, Node o2) {
			
			
			
			if (o1.cost > o2.cost) {
				
				return 1;
			}
			
			if(o1.cost < o2.cost) {
				
				return -1;
			}
			
			
			return 0;
		}
		
	}
	
	public static class Node{
		
		public float cost;
		public String loc;
		public Node pre;
		public int x;
		public int y;
		
		public Node(float cost, float x, float y) {
			this.cost = cost;
			this.x = (int) x;
			this.y = (int) y;
			this.loc = x + "," + y;
			this.pre = null;
		}
		
		public int hashcode() {
			return (int)cost;
			
		}
		
		public boolean equals(Object o) {
			Node n = (Node)o;
			if(n.cost == this.cost) {
				return true;
			}
			
			return false;
		}
		
		
	}
	
	public float hueristic(float x, float y, float gx, float gy){
		
		
		
		
		return (float)(fastSpeed * Math.sqrt(Math.pow((gx-x), 2) + Math.pow((gy-y), 2)));
	}
	
	public void aStarSearch(float gx, float gy, GameModel m) {
		
		if(fastSpeed == Float.MAX_VALUE) {
			for(int i = 0; i<= m.XMAXIMUM; i +=10) {
				for(int j = 0; j <= m.YMAXIMUM; j +=10) {
					
					if(fastSpeed > 1/m.getSpeedOfTravel(i, j)) {
						fastSpeed = 1/m.getSpeedOfTravel(i, j);
					}
					
					
				}
			}
		}
		
		
		frontier = new TreeMap<String, Node>();
		path = new LinkedList<>();
		q = new PriorityQueue<Node>(new MyCompare());
		
		m.getRobots().get(0).x = Math.round(m.getX()/10.0) * 10;
		m.getRobots().get(0).y = Math.round(m.getY()/10.0) * 10;
		
		gx = Math.round(gx/10.0) * 10;
		gy = Math.round(gy/10.0) * 10;
		
		
		
		Node start = new Node(0, m.getX(), m.getY());
		q.add(start);
		
		boolean goal = false;
		Node g = null;
		
		while(!q.isEmpty() && !goal) {
			Node c = q.remove();
			
			int x = c.x;
			int y = c.y;
			
			
			//North
			if(!frontier.containsKey(x + "," + (y-10)) && y-10 >= 0){
				
				
				Node n = new Node(c.cost + 1/m.getSpeedOfTravel(x, y-10) + hueristic(x, y-10, gx, gy), x, y-10);
				//System.out.println("C is: " + c.loc + "\nN is : " + n.loc);
				if(!n.loc.equals(c.loc)) {
					n.pre = c;
				}
				q.add(n);
				frontier.put(n.x+","+n.y, n);
				
				m.getController().view.getGraphics().setColor(Color.yellow);
				m.getController().view.getGraphics().drawOval(n.x, n.y, 10, 10);
				
				if(n.x == gx && n.y == gy) {
					goal = true;
					g = n;
				}
				
			}
			//Northeast
			
			if(!frontier.containsKey((x + 10) + "," + (y-10)) && x+10 <= m.XMAXIMUM && y-10 >= 0){
				
				Node n = new Node((float)(c.cost + 1/m.getSpeedOfTravel(x+10, y-10) * Math.sqrt(2) + hueristic(x+10, y-10, gx, gy)), x+10, y-10);
				//System.out.println("C is: " + c.loc + "\nN is : " + n.loc);
				if(!n.loc.equals(c.loc)) {
					n.pre = c;
				}
				q.add(n);
				frontier.put(n.x+","+n.y, n);
				
				m.getController().view.getGraphics().setColor(Color.yellow);
				m.getController().view.getGraphics().drawOval(n.x, n.y, 10, 10);
				
				if(n.x == gx && n.y == gy) {
					goal = true;
					g = n;
				}
				
			}
			
			//East
			
			if(!frontier.containsKey((x + 10) + "," + y) && x+10 <= m.XMAXIMUM){
				
				Node n = new Node(c.cost + 1/m.getSpeedOfTravel(x+10, y) + hueristic(x+10, y, gx, gy), x+10, y);
				//System.out.println("C is: " + c.loc + "\nN is : " + n.loc);
				if(!n.loc.equals(c.loc)) {
					n.pre = c;
				}
				q.add(n);
				frontier.put(n.x+","+n.y, n);
				
				m.getController().view.getGraphics().setColor(Color.yellow);
				m.getController().view.getGraphics().drawOval(n.x, n.y, 10, 10);
				
				if(n.x == gx && n.y == gy) {
					goal = true;
					g = n;
				}
				
			}
			
			//Southeast
			
			if(!frontier.containsKey((x + 10) + "," + (y+10)) && x+10 <= m.XMAXIMUM && y+10 <= m.YMAXIMUM){
				
				Node n = new Node((float)(c.cost + 1/m.getSpeedOfTravel(x+10, y+10) * Math.sqrt(2) + hueristic(x+10, y+10, gx, gy)), x+10, y+10);
				//System.out.println("C is: " + c.loc + "\nN is : " + n.loc);
				if(!n.loc.equals(c.loc)) {
					n.pre = c;
				}
				q.add(n);
				frontier.put(n.x+","+n.y, n);
				
				m.getController().view.getGraphics().setColor(Color.yellow);
				m.getController().view.getGraphics().drawOval(n.x, n.y, 10, 10);
				
				if(n.x == gx && n.y == gy) {
					goal = true;
					g = n;
				}
				
			}
			
			//South
			
			if(!frontier.containsKey(x + "," + (y + 10)) && y+10 <= m.YMAXIMUM){
				
				Node n = new Node(c.cost + 1/m.getSpeedOfTravel(x, y+10) + hueristic(x, y+10, gx, gy), x, y+10);
				//System.out.println("C is: " + c.loc + "\nN is : " + n.loc);
				if(!n.loc.equals(c.loc)) {
					n.pre = c;
				}
				q.add(n);
				frontier.put(n.x+","+n.y, n);
				
				m.getController().view.getGraphics().setColor(Color.YELLOW);
				m.getController().view.getGraphics().drawOval(n.x, n.y, 10, 10);
				
				if(n.x == gx && n.y == gy) {
					goal = true;
					g =n;
				}
				
			}
			
			//Southwest
			
			if(!frontier.containsKey((x - 10) + "," + (y+10)) && x-10 >= 0 && y+10 <= m.YMAXIMUM){
				
				Node n = new Node((float)(c.cost + 1/m.getSpeedOfTravel(x-10, y+10) * Math.sqrt(2) + hueristic(x-10, y+10, gx, gy)), x-10, y+10);
				//System.out.println("C is: " + c.loc + "\nN is : " + n.loc);
				if(!n.loc.equals(c.loc)) {
					n.pre = c;
				}
				q.add(n);
				frontier.put(n.x+","+n.y, n);
				
				m.getController().view.getGraphics().setColor(Color.yellow);
				m.getController().view.getGraphics().drawOval(n.x, n.y, 10, 10);
				
				if(n.x == gx && n.y == gy) {
					goal = true;
					g = n;
				}
				
			}
			
			//West
			
			if(!frontier.containsKey((x-10) + "," + y) && x-10 >= 0){
				
				Node n = new Node(c.cost + 1/m.getSpeedOfTravel(x-10, y) + hueristic(x-10, y, gx, gy), x-10, y);
				//System.out.println("C is: " + c.loc + "\nN is : " + n.loc);
				
				if(!n.loc.equals(c.loc)) {
					n.pre = c;
				}
				
				
				q.add(n);
				frontier.put(n.x+","+n.y, n);
				
				m.getController().view.getGraphics().setColor(Color.yellow);
				m.getController().view.getGraphics().drawOval(n.x, n.y, 10, 10);
				
				if(n.x == gx && n.y == gy) {
					goal = true;
					g = n;
				}
				
			}
			
			//Northwest
			
			if(!frontier.containsKey((x - 10) + "," + (y-10)) && x-10 >= 0 && y-10 >= 0){
				
				Node n = new Node((float)(c.cost + 1/m.getSpeedOfTravel(x-10, y-10) * Math.sqrt(2) + hueristic(x-10, y-10, gx, gy)), x-10, y-10);
				//System.out.println("C is: " + c.loc + "\nN is : " + n.loc);
				if(!n.loc.equals(c.loc)) {
					n.pre = c;
				}
				q.add(n);
				frontier.put(n.x+","+n.y, n);
				
				m.getController().view.getGraphics().setColor(Color.yellow);
				m.getController().view.getGraphics().drawOval(n.x, n.y, 10, 10);
				
				if(n.x == gx && n.y == gy) {
					goal = true;
					g = n;
				}
				
			}
			
			
			if(goal) {
				//System.out.println("Found it!: " + g.loc);
			}
			
		}
		
		
		start.pre = null;
		
		
		for(String k : frontier.keySet()) {
			//System.out.println(k);
		}
		
		if(g.equals(g.pre)) {
			//System.out.println("OOPS: " + g.loc);
		}
		
		path.add(g);
		while(g.pre != null) {
			//System.out.println("Adding: " + g.pre.loc);
			path.add(g.pre);
			g = g.pre;
		}
		
		
	}
	
	public void uniformCostSearch(float gx, float gy, GameModel m) {
		
		frontier = new TreeMap<String, Node>();
		path = new LinkedList<>();
		q = new PriorityQueue<Node>(new MyCompare());
		
		m.getRobots().get(0).x = Math.round(m.getX()/10.0) * 10;
		m.getRobots().get(0).y = Math.round(m.getY()/10.0) * 10;
		
		gx = Math.round(gx/10.0) * 10;
		gy = Math.round(gy/10.0) * 10;
		
		
		
		Node start = new Node(0, m.getX(), m.getY());
		q.add(start);
		
		boolean goal = false;
		Node g = null;
		
		while(!q.isEmpty() && !goal) {
			Node c = q.remove();
			
			int x = c.x;
			int y = c.y;
			
			
			//North
			if(!frontier.containsKey(x + "," + (y-10)) && y-10 >= 0){
				
				
				Node n = new Node(c.cost + 1/m.getSpeedOfTravel(x, y-10), x, y-10);
				//System.out.println("C is: " + c.loc + "\nN is : " + n.loc);
				if(!n.loc.equals(c.loc)) {
					n.pre = c;
				}
				q.add(n);
				frontier.put(n.x+","+n.y, n);
				
				m.getController().view.getGraphics().setColor(Color.yellow);
				m.getController().view.getGraphics().drawOval(n.x, n.y, 10, 10);
				
				if(n.x == gx && n.y == gy) {
					goal = true;
					g = n;
				}
				
			}
			//Northeast
			
			if(!frontier.containsKey((x + 10) + "," + (y-10)) && x+10 <= m.XMAXIMUM && y-10 >= 0){
				
				Node n = new Node((float)(c.cost + 1/m.getSpeedOfTravel(x+10, y-10) * Math.sqrt(2)), x+10, y-10);
				//System.out.println("C is: " + c.loc + "\nN is : " + n.loc);
				if(!n.loc.equals(c.loc)) {
					n.pre = c;
				}
				q.add(n);
				frontier.put(n.x+","+n.y, n);
				
				m.getController().view.getGraphics().setColor(Color.yellow);
				m.getController().view.getGraphics().drawOval(n.x, n.y, 10, 10);
				
				if(n.x == gx && n.y == gy) {
					goal = true;
					g = n;
				}
				
			}
			
			//East
			
			if(!frontier.containsKey((x + 10) + "," + y) && x+10 <= m.XMAXIMUM){
				
				Node n = new Node(c.cost + 1/m.getSpeedOfTravel(x+10, y), x+10, y);
				//System.out.println("C is: " + c.loc + "\nN is : " + n.loc);
				if(!n.loc.equals(c.loc)) {
					n.pre = c;
				}
				q.add(n);
				frontier.put(n.x+","+n.y, n);
				
				m.getController().view.getGraphics().setColor(Color.yellow);
				m.getController().view.getGraphics().drawOval(n.x, n.y, 10, 10);
				
				if(n.x == gx && n.y == gy) {
					goal = true;
					g = n;
				}
				
			}
			
			//Southeast
			
			if(!frontier.containsKey((x + 10) + "," + (y+10)) && x+10 <= m.XMAXIMUM && y+10 <= m.YMAXIMUM){
				
				Node n = new Node((float)(c.cost + 1/m.getSpeedOfTravel(x+10, y+10) * Math.sqrt(2)), x+10, y+10);
				//System.out.println("C is: " + c.loc + "\nN is : " + n.loc);
				if(!n.loc.equals(c.loc)) {
					n.pre = c;
				}
				q.add(n);
				frontier.put(n.x+","+n.y, n);
				
				m.getController().view.getGraphics().setColor(Color.yellow);
				m.getController().view.getGraphics().drawOval(n.x, n.y, 10, 10);
				
				if(n.x == gx && n.y == gy) {
					goal = true;
					g = n;
				}
				
			}
			
			//South
			
			if(!frontier.containsKey(x + "," + (y + 10)) && y+10 <= m.YMAXIMUM){
				
				Node n = new Node(c.cost + 1/m.getSpeedOfTravel(x, y+10), x, y+10);
				//System.out.println("C is: " + c.loc + "\nN is : " + n.loc);
				if(!n.loc.equals(c.loc)) {
					n.pre = c;
				}
				q.add(n);
				frontier.put(n.x+","+n.y, n);
				
				m.getController().view.getGraphics().setColor(Color.YELLOW);
				m.getController().view.getGraphics().drawOval(n.x, n.y, 10, 10);
				
				if(n.x == gx && n.y == gy) {
					goal = true;
					g =n;
				}
				
			}
			
			//Southwest
			
			if(!frontier.containsKey((x - 10) + "," + (y+10)) && x-10 >= 0 && y+10 <= m.YMAXIMUM){
				
				Node n = new Node((float)(c.cost + 1/m.getSpeedOfTravel(x-10, y+10) * Math.sqrt(2)), x-10, y+10);
				//System.out.println("C is: " + c.loc + "\nN is : " + n.loc);
				if(!n.loc.equals(c.loc)) {
					n.pre = c;
				}
				q.add(n);
				frontier.put(n.x+","+n.y, n);
				
				m.getController().view.getGraphics().setColor(Color.yellow);
				m.getController().view.getGraphics().drawOval(n.x, n.y, 10, 10);
				
				if(n.x == gx && n.y == gy) {
					goal = true;
					g = n;
				}
				
			}
			
			//West
			
			if(!frontier.containsKey((x-10) + "," + y) && x-10 >= 0){
				
				Node n = new Node(c.cost + 1/m.getSpeedOfTravel(x-10, y), x-10, y);
				//System.out.println("C is: " + c.loc + "\nN is : " + n.loc);
				
				if(!n.loc.equals(c.loc)) {
					n.pre = c;
				}
				
				
				q.add(n);
				frontier.put(n.x+","+n.y, n);
				
				m.getController().view.getGraphics().setColor(Color.yellow);
				m.getController().view.getGraphics().drawOval(n.x, n.y, 10, 10);
				
				if(n.x == gx && n.y == gy) {
					goal = true;
					g = n;
				}
				
			}
			
			//Northwest
			
			if(!frontier.containsKey((x - 10) + "," + (y-10)) && x-10 >= 0 && y-10 >= 0){
				
				Node n = new Node((float)(c.cost + 1/m.getSpeedOfTravel(x-10, y-10) * Math.sqrt(2)), x-10, y-10);
				//System.out.println("C is: " + c.loc + "\nN is : " + n.loc);
				if(!n.loc.equals(c.loc)) {
					n.pre = c;
				}
				q.add(n);
				frontier.put(n.x+","+n.y, n);
				
				m.getController().view.getGraphics().setColor(Color.yellow);
				m.getController().view.getGraphics().drawOval(n.x, n.y, 10, 10);
				
				if(n.x == gx && n.y == gy) {
					goal = true;
					g = n;
				}
				
			}
			
			
			if(goal) {
				//System.out.println("Found it!: " + g.loc);
			}
			
		}
		
		
		start.pre = null;
		
		
		for(String k : frontier.keySet()) {
			//System.out.println(k);
		}
		
		if(g.equals(g.pre)) {
			//System.out.println("OOPS: " + g.loc);
		}
		
		path.add(g);
		while(g.pre != null) {
			//System.out.println("Adding: " + g.pre.loc);
			path.add(g.pre);
			g = g.pre;
		}
		
		
	}

	public void drawPlan(Graphics g, GameModel m) {
		g.setColor(Color.red);
		//g.drawLine((int)m.getX(), (int)m.getY(), (int)m.getDestXValue(), (int)m.getDestYValue());
		Node lastn = null;
		if(!path.isEmpty()) {
			lastn = path.getFirst();
		}
		
		for(Node n: path) {
			
			g.setColor(Color.red);
			g.drawLine((int)lastn.x, (int)lastn.y, (int)n.x, (int)n.y);
			
			lastn = n;
		}
		
		if(!path.isEmpty() && m.getDistanceToDest(0) == 0) {
			
			Node n = path.removeLast();
			m.setDest(n.x, n.y);
			
		}
		Node[] a = new Node[q.size()];
		for(Node n: q.toArray(a)) {
			n = (Node)n;
			g.drawOval(n.x, n.y, 10, 10);
		}
				
		
		
	}

	public void update(GameModel m)
	{
		GameController c = m.getController();
		while(true)
		{
			MouseEvent e = c.nextMouseEvent();
			if(e == null)
				break;
			
			if(e.getButton() == MouseEvent.BUTTON1) {
				uniformCostSearch(e.getX(), e.getY(), m);
			}else {
				aStarSearch(e.getX(), e.getY(), m);
			}
			
			
			
			
		}
	}

	public static void main(String[] args) throws Exception
	{
		GameController c = new GameController();
		c.initialize();

		// This will instantiate a new instance of JFrame.  Each will spawn in another thread to generate events
		//and keep the entire program running until the JFrame is terminated.
		c.view = new GameView(c, c.model);
		
		
		// this will create an ActionEvent at fairly regular intervals.   Each of the events are handled by
		// GameView.actionPerformed()
		new Timer(20, c.view).start(); 
	}
}
