package edu.uafs.test;
//MASTER SERVER FINAL PROJECT VERSION

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class UAServer implements Runnable {
	
	Socket UAServerSocket;
	File threadFile;
	
	
	static int serverCount = 0; //the only 2 things static in this program. Is not altered after config file is read (which is in a single thread environment) Therefore, these are still thread safe. 
	static ArrayList<String> serverList = new ArrayList();
	static HashMap<String,String> userMap = new HashMap<>();
	
	public UAServer(Socket socket) {
		UAServerSocket = socket;
	}
	
	public UAServer(File f) { //NOT SURE IF I NEED TO REMOVE THIS.
		this.threadFile = f;
	}

	public static void main(String[] args) {
		try {
			System.out.println("FINAL VERSION");
			
			System.out.println(args[0]+args[1]);
			
			
			//Read from the config file///////////////////////
			String config = args[0];
			BufferedReader br1 = new BufferedReader(new FileReader(config));
			String line;
			String serverName;
			String ipAddress;

			while((line=br1.readLine())!=null){
				serverList.add(line);
				serverCount++;
			}
			////////////////////////////////////////////////////
			
			//Read from the user file///////////////////////
			String userVarification = args[1];
			BufferedReader br2 = new BufferedReader(new FileReader(userVarification));
			String line2;
			String[] lineData;
			String username;
			String password;

			while((line2=br2.readLine())!=null){
				lineData = line2.split(" ");
				username = lineData[0];
				password = lineData[1];
				userMap.put(username, password);
			}
			////////////////////////////////////////////////////
			
			
			
			//String x = serverList.get(1);

			
			ServerSocket server = new ServerSocket(32000);
			System.out.println("MasterServer is running");
			
			
			// Listen for connections and create a new thread for each connection.
			while(true) {
				System.out.println("Master Server: Waiting for a response.");
				Socket socket = server.accept();
				
				//make new thread
				Thread t = new Thread(new UAServer(socket));
				t.start();
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void run() {
		System.out.println("MasterServer: Thread started");
		
		// check login information
		byte[] loginData = new byte[1024]; //e.g. 1024, 2048, etc.
		InputStream in;
		String loginInfo;
		
		try {
			
			in = UAServerSocket.getInputStream();
			
			DataInputStream d = new DataInputStream(in);
			loginInfo = d.readUTF();
			
			String[] loginSplit = loginInfo.split(",");
			String username = loginSplit[0];
			String password = loginSplit[1];
			String ipAddress = loginSplit[2];
			
			System.out.println(username+password);
			
			
			//TO-DO: check to see if user exists before following if structure
			
			if(password.contentEquals(userMap.get(username))){
				System.out.println("correct password");
				//send some kind of signal to servlet
				
				Socket socketSendToClient = new Socket(ipAddress,31999);
				//Socket socket2 = new Socket("10.183.240.42",32002); //cale's vm
				
				//What is needed to send filename///////////
				OutputStream out;
				out = socketSendToClient.getOutputStream();
				DataOutputStream o = new DataOutputStream(out);
		        o.writeUTF("rightPassword");
		        
				
			}else {
				System.out.println("incorrect password");
			}
			
			//read through login data structure
			
			
			
			/*
			int bytesRecieved = in.read(data, 0, data.length );
			
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filename)); //figure out how to change this name
			//BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("even"+rand.nextInt(1000)+".txt")); //figure out how to change this name
			
			out.write(data, 0, data.length);
			out.close();
			*/
			//UAServerSocket.close(); //I don't think we want this to close
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//compare username and password, see if they match
		
		
		//if they do, continue to file select page. maintain connection. 
		
		//if not, back out and return user to the login page. close connection. . 
		
		
		
		
		String filename = threadFile.getName();
		String numOnly = filename.replaceAll("[^0-9]", "");
		int num = Integer.parseInt(numOnly);
		
		int result = (num % serverCount);
		int portnum = 32000+result; 
		String ipAddress = serverList.get(result);
		
		String[] x = ipAddress.split(" "); 
		ipAddress = x[1];
		
		
		System.out.println("RESULT: "+result);   //file1 goes to 1, file 2 goes to 2, file3 goes to 0, and file 4 goes to 1
		System.out.println(ipAddress);
		System.out.println(filename);
		System.out.println();
		
		
		
		//send file name + file data	
		try {
				
			Socket socket2 = new Socket(ipAddress,portnum);
			//Socket socket2 = new Socket("10.183.240.42",32002); //cale's vm
			
			//What is needed to send filename///////////
			OutputStream out;
			out = socket2.getOutputStream();
			DataOutputStream d = new DataOutputStream(out);
	        d.writeUTF(filename);
	        ////////////////////////////////////////////
			
			
			byte[] data = new byte[ (int) threadFile.length() ];
			
			//step 1 read file to send
			BufferedInputStream bis;
			try {
				bis = new BufferedInputStream(new FileInputStream(threadFile));
				bis.read(data,0,data.length);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Step 2: send file
			try {
				OutputStream out2;
				out2 = socket2.getOutputStream();
				out2.write(data, 0, data.length);
				out2.flush();
				socket2.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	public boolean checkLogin(String username, String password){
		
		return false;
	}
	
}