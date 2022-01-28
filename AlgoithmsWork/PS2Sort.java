
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author CALE
 */
public class PS2Sort {
    
    private static UASong[] songs = new UASong[0];
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        if (args.length == 0){
            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SortGUI().setVisible(true);
            }
        });
        }else{
            double time= -1;
            loadArray(args[0]);
            
            int sortOn = -1;
            if(args[2].equalsIgnoreCase("song")){
                sortOn = 0;
            }else if (args[2].equalsIgnoreCase("runtime")){
                sortOn = 1;
            }
            
            if(args[1].equalsIgnoreCase("bsort")){
               time = bubbleSort(sortOn);
            }else if (args[1].equalsIgnoreCase("isort")){
                time = insertionSort(sortOn);
            }else if (args[1].equalsIgnoreCase("msort")){
                time = mergeSort(sortOn);
            }
            
            printArray();
            System.out.println();
            System.out.println("Time Elapsed: " + time + " milliseconds");
            
        }
    }
    
    public static void loadArray(String fileName) throws FileNotFoundException, IOException{
        
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        
        ArrayList<UASong> al = new ArrayList<>();
        String input;
        while((input = br.readLine()) != null){
            String[] val = input.split(",");
            al.add(new UASong(val[0], val[1], Integer.parseInt(val[2])));
        }
        
        songs = new UASong[al.size()];
        int i = 0;
        for (UASong s: al){
            songs[i] = s;
            i++;
        }
        
        //System.out.println(songs.length);
        
        //System.out.println("ADDED");
        
        
        
    }
    
    public static double bubbleSort(int sortOn){
       long startTime = System.nanoTime();
       
       if(sortOn == 0){
           for(int i = 0; i < songs.length; i++){
               for(int j=0; j<songs.length-1; j++){
                   if(songs[j].getsName().compareToIgnoreCase(songs[j+1].getsName()) > 0){
                       UASong s = songs[j];
                       songs[j] = songs[j+1];
                       songs[j+1] = s;
                   }
               }
           }
       }else if(sortOn == 1){
           for(int i = 0; i < songs.length; i++){
               for(int j=0; j<songs.length-1; j++){
                   if(songs[j].getRuntime() > songs[j+1].getRuntime()){
                       UASong s = songs[j];
                       songs[j] = songs[j+1];
                       songs[j+1] = s;
                   }
               }
           }
       }
       
       
       
       long endTime = System.nanoTime();
       long totTime = endTime-startTime;
        
        return totTime/1000000;
    }

    public static UASong[] getSongs() {
        return songs;
    }

    public static void setSongs(UASong[] songs) {
        PS2Sort.songs = songs;
    }
    
    public static double insertionSort(int sortOn){
       //System.out.println("INSERTION");
       long startTime = System.nanoTime();
       int i = 0;
       if(sortOn == 0){
           
           while(i < songs.length){
               UASong key = songs[i];
               int j = i-1;
               
               while(j>=0 && songs[j].getsName().compareToIgnoreCase(key.getsName()) > 0){
                  songs[j+1] = songs[j];
                  j--;
               }
               songs[j+1] = key;
               i++;
           }
           
       } else if(sortOn == 1){
           while(i < songs.length){
               UASong key = songs[i];
               int j = i-1;
               
               while(j>=0 && songs[j].getRuntime() > key.getRuntime()){
                  songs[j+1] = songs[j];
                  j--;
               }
               songs[j+1] = key;
               i++;
           }
       }
       
        long endTime = System.nanoTime();
        long totTime = endTime-startTime;
        
        return totTime/1000000;
    }
    
    public static double mergeSort(int sortOn){
        long startTime = System.nanoTime();
        mergeSort(sortOn, 0, (songs.length-1));
        long endTime = System.nanoTime();
        long totTime = endTime-startTime;
        
        return totTime/1000000;
    }
    
    public static void mergeSort(int sortOn, int p, int r){
        //System.out.println("MERGE");
        if(p < r){
			int q = (p+r)/2;
			
			
			mergeSort(sortOn, p, q);
			mergeSort(sortOn, q+1, r);
			merge(sortOn, p, q, r);
		}
        
    }
    
    public static void merge(int sortOn, int p, int q, int r){
     
        
		int s1 = q-p+1;
		int s2= r-q;
		
		UASong[] L = new UASong[s1+1];
		UASong[] R = new UASong[s2+1];
		
		for (int i = 0; i<s1; i++){
			L[i] = songs[p+i];
		}
		
		for (int i = 0; i < s2; i++){
			R[i] = songs[q+i+1];
		}

                if(sortOn == 0){
                    L[s1] = new UASong("","zzzzzzzzzzzzzzzzzzzzzz",0);
                    R[s2] = new UASong("","zzzzzzzzzzzzzzzzzzzzzz",0);
                    
                    int i = 0;
                    int j = 0;
		
                    for (int k = p; k<=r; k++){
                            //System.out.println(L[i].getsName()+ " vers " + R[j].getsName()+" - i: " + i + " j: " + j);
                            //System.out.println("Right size: " + R.length + " Left Size: " + L.length);
                            if(L[i].getsName().compareToIgnoreCase(R[j].getsName()) <=  0){
                                    songs[k] = L[i];
                                    i++;
                                    
                            }else{
                                    songs[k] = R[j];
                                    j++;
                            }
                    }
                    
                }else if(sortOn == 1){
                    L[s1] = new UASong("","",Integer.MAX_VALUE);
                    R[s2] = new UASong("","",Integer.MAX_VALUE);
                    
                    int i = 0;
                    int j = 0;
		
                    for (int k = p; k<=r; k++){
                            if(L[i].getRuntime() <= R[j].getRuntime()){
                                    songs[k] = L[i];
                                    i++;
                            }else{
                                    songs[k] = R[j];
                                    j++;
                            }
                    }
                    
                }
                
                
		
		
        
        
    }
    
    public static void printArray(){
        for(int i = 0; i < songs.length; i++){
            System.out.printf("%-30s %-50s %6d\n", songs[i].getaName(), songs[i].getsName(), songs[i].getRuntime());
        }
    }
    
}
