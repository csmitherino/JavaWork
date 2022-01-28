package edu.uafs.test;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

// FINAL VERSION DATA SERVER 2

public class DataServer2 implements Runnable {
	

	Random rand = new Random();
	Socket UAServerSocket;
	String filename;
	
	public DataServer2(Socket socket) {
		UAServerSocket = socket;
	}
	
	public static void main(String[] args) {
		try {
			
			ServerSocket server = new ServerSocket(32002);
			System.out.println("DataServer2: Waiting for master server...");
			
			while(true) {
				
				Socket socket = server.accept();
				Thread t = new Thread(new DataServer2(socket));
				t.start();
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void run() {
		System.out.println("DataServer2: A thread from MasterServer has connected...");
		
		byte[] data = new byte[1024]; //e.g. 1024, 2048, etc.
		InputStream in;
		
		try {
			
			in = UAServerSocket.getInputStream();
	
			DataInputStream d = new DataInputStream(in);
			filename = d.readUTF();
			
			System.out.println(filename);
			
			int bytesRecieved = in.read(data, 0, data.length );
			
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filename)); //figure out how to change this name
			//BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("even"+rand.nextInt(1000)+".txt")); //figure out how to change this name
			
			out.write(data, 0, data.length);
			out.close();
			//UAServerSocket.close(); //I don't think we want this to close
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("DataServer2: Thread ended.");
	}

}
