
import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author casmi
 */
public class GeneticAlgorithm {
   
    public static class MyCompare implements Comparator<Node>{

		@Override
		public int compare(Node o1, Node o2) {
			
			
			
			if (o1.fitness > o2.fitness) {
				
				return 1;
			}
			
			if(o1.fitness < o2.fitness) {
				
				return -1;
			}
			
			
			return 0;
		}
		
    }
    
    
    public static class Node{
        
        public boolean[] genes;
        public int fitness;
        
        public String toString(){
            String gene = "";
            
            for(boolean g : genes){
                if(g){
                    gene+="1";
                }else{
                    gene+="0";
                }
            }
         return gene;   
        }
        
    }
    
   //public HashMap<Integer, Node> population = new HashMap<>();
   public PriorityQueue<Node> population = new PriorityQueue<>(new MyCompare());
   public Node[] pop;
   
   public int highest = 0;
   
   public int[] weights;
   public int[] values;
   
   public int maxPop;
   public int mutationPercent;
   
   public int maxIterations;
   public int minimumFitness;
   
   public int capacity;
   String filename;
   
   public double mean;
   public double var;
   
   public GeneticAlgorithm(String filename, int capacity, int maxPop, int maxI, int mutation, int fitness){
       
       this.filename = filename;
       this.capacity = capacity;
       this.maxPop = maxPop;
       this.maxIterations = maxI;
       this.mutationPercent = mutation;
       this.minimumFitness = fitness;
       
       
        try {
            this.readFile();
        } catch (IOException ex) {
            Logger.getLogger(GeneticAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        generateFirstPopulation();
        
   }
   
   public void maxBaseIterate(){
       
       while(this.highest < this.minimumFitness && this.maxIterations > 0){
           baseIterate();
           this.maxIterations--;
       }
       
   }
   
   public Node getHighestNode(){
       return null;
   }
   
   public void resetArray(){
       
       Object[] pop = this.population.toArray();
       this.pop = new Node[pop.length];
       
       for(int  i = 0; i < pop.length; i ++){
           this.pop[i] = (Node)pop[i];
       }
       
   }
   
   public void readFile() throws IOException{
       
       BufferedReader br = new BufferedReader(new FileReader(this.filename));
       String input;
       LinkedList<Integer> weights = new LinkedList<>(); 
       LinkedList<Integer> values = new LinkedList<>(); 
       
       while((input = br.readLine()) != null){
           
           String[] vals = input.split(",");
           
           weights.add(Integer.parseInt(vals[0]));
           values.add(Integer.parseInt(vals[1]));
           
       }
       
       this.weights = new int[weights.size()];
       this.values = new int[weights.size()];
       
       for(int i = 0; i < weights.size(); i++){
           this.weights[i] = weights.get(i);
           this.values[i] = values.get(i);
       }
       
       
       
   }
   
   public void generateFirstPopulation(){
       
       Random rand = new Random();
       
       for (int i = 0; i < this.maxPop; i ++){
           
           Node n = new Node();
           
           n.genes = new boolean[this.weights.length];
           
           for (int j = 0; j<n.genes.length; j++){
               if(rand.nextInt(2) == 1){
                   n.genes[j] = true;
               }else{
                   n.genes[j] = false;
               }
           }
           
           n.fitness = calcFitness(n);
           //System.out.println(calcFitness(n));
           this.population.add(n);
           if(n.fitness > highest){
               highest = n.fitness;
           }
       }
       
       //System.out.println(this.population.size());
       
   }
   
   public int calcFitness(Node n){
       
       int fitness = 0;
       int weight = 0;
       
       for(int  i = 0; i<n.genes.length; i ++){
           
           if(n.genes[i]){
               //System.out.println("true");
               fitness+=this.values[i];
               weight+=this.weights[i];
           }
           
           
       }
       
       //System.out.println(weight);
       
       if(weight > this.capacity){
           fitness = 0;
       }
       
       //System.out.println(fitness);
       return fitness;
   }
   
   public void baseIterate(){
       
       baseNarrow();
       Random rand = new Random();
       Object[] pop = this.population.toArray();
       
       
       for(int i = 0; i < 3; i++){
           
           int first;
           int second;
           
           first = rand.nextInt(pop.length);
           second = rand.nextInt(pop.length);
           
           while(first == second){
               second = rand.nextInt(pop.length);
           }
           
           Node child = baseBreed((Node)pop[first], (Node)pop[second]);
           baseMutate(child);
           child.fitness = calcFitness(child);
           
           this.population.add(child);
           if(child.fitness > highest){
               highest = child.fitness;
           }
       }
       
       int total = 0;
       
       pop = this.population.toArray();
       
       for(Object n : pop){
           
           total+= ((Node)n).fitness;
           
       }
       
       this.mean = (total/pop.length);
       
       for(Object n : pop){
           
           total+= Math.pow((((Node)n).fitness - mean), 2);
           
       }
       
       this.var = total/pop.length;
       
       
       System.out.println("mean: " + this.mean + "\tvar: " + this.var);
       
       
       
   }
    
   public void baseNarrow(){
       
       for(int i = 0; i < 3; i ++){
           this.population.poll();
       }
       
       
   }
   
   public Node baseBreed(Node n, Node m){
       
       //System.out.print("Breeding: " + n.fitness + " and " + m.fitness );
       
       Random rand = new Random(1);
       Node c = new Node();
       c.genes = new boolean[n.genes.length];
       
       for(int i = 0; i < n.genes.length; i ++){
           if(rand.nextInt() == 0){
               c.genes[i] = n.genes[i];
           }else{
               //System.out.println(c.genes);
               //System.out.println(m.genes[i]);
               c.genes[i] = m.genes[i];
           }
       }
       
       
       return c;
       
   }
   
   public void baseMutate(Node n){
       
       Random rand = new Random();
       
       for(int i = 0; i < n.genes.length; i ++){
           if(rand.nextInt(100) <= this.mutationPercent){
               if(n.genes[i]){
                   n.genes[i] = false;
               }else{
                   n.genes[i]=true;
               }
           }
       }
       
       
   }
   
   public void customMutate(Node n){
       
       Random rand = new Random();
       
       for(int i = 0; i < n.genes.length; i ++){
           
           int perc = rand.nextInt(100);
           double prop = this.capacity/this.weights[i];
           
           if(prop < 1){
               if(n.genes[i]){
                   n.genes[i] = false;
               }
           }else{
               prop = this.capacity/(rand.nextInt(this.weights[i])+1);
               perc=(int)(perc/prop);
           }
           
           if(perc <= this.mutationPercent){
               if(n.genes[i]){
                   n.genes[i] = false;
               }else{
                   n.genes[i]=true;
               }
           }
       }
       
       
   }
   
   public void customIterate(){
       
       //System.out.println("Iterating");
       
       baseNarrow();
       Random rand = new Random();
       Object[] pop = this.population.toArray();
       
       
       for(int i = 0; i < 3; i++){
           
           int first;
           int second;
           
           first = rand.nextInt(pop.length);
           second = rand.nextInt(pop.length);
           
           while(first == second){
               second = rand.nextInt(pop.length);
           }
           
           Node child = baseBreed((Node)pop[first], (Node)pop[second]);
           customMutate(child);
           child.fitness = calcFitness(child);
           
           this.population.add(child);
           if(child.fitness > highest){
               highest = child.fitness;
           }
       }
       
       int total = 0;
       
       pop = this.population.toArray();
       
       for(Object n : pop){
           
           total+= ((Node)n).fitness;
           
       }
       
       this.mean = (total/pop.length);
       
       total = 0;
       
       for(Object n : pop){
           
           total+= Math.pow((((Node)n).fitness - mean), 2);
           
       }
       
       this.var = total/pop.length;
       
       System.out.println("mean: " + this.mean + "\tvar: " + this.var);
   }
   
   public void maxCustomIterate(){
       
       //System.out.println("Highest: " + this.highest);
      // System.out.println("Min: " + this.minimumFitness);
       //System.out.println("Iterations: " + this.maxIterations);
       
       while(this.highest < this.minimumFitness && this.maxIterations > 0){
           customIterate();
           //System.out.println("mean: " + this.mean);
           this.maxIterations--;
       }
       
   }
   
}


