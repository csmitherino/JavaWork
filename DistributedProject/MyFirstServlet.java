package edu.uafs.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyFirstServlet
 */
@WebServlet("/MyFirstServlet")
public class MyFirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Socket UAServerSocket;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyFirstServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public MyFirstServlet(Socket socket) {
    	super();
		UAServerSocket = socket;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ServerSocket server = new ServerSocket(31999); //NOT SURE IF THIS WORKS
		
		//send username + password to data server
		Socket clientSocket = new Socket("localhost",32000);
		OutputStream out;
		out = clientSocket.getOutputStream();
		DataOutputStream d = new DataOutputStream(out);
		
		String uname = request.getParameter("txtUsername");
		String pword = request.getParameter("txtPassword");
		String ipaddr = request.getLocalAddr();
		
        d.writeUTF(uname+","+pword+","+ipaddr);
        System.out.println("THIS IS A TEST");
        
        
        Socket socket = server.accept();
        MyFirstServlet newServlet = new MyFirstServlet(socket);
        
        System.out.println(newServlet.UAServerSocket);
        
        
        
        
        
       //receive information back from master server BELOW CODE HAS NOT BEEN TESTED
        byte[] data = new byte[1024]; //e.g. 1024, 2048, etc.
		InputStream in;
        try {
			
			in = UAServerSocket.getInputStream();
			
			DataInputStream dataInputStream = new DataInputStream(in);
			String infoFromServer = dataInputStream.readUTF();
			System.out.println(infoFromServer);
			//loginInfo = d.readUTF();
			
			//TO-DO: check to see if user exists before following if structure
			
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
        
        //end of experimental code
        
        
        
		//if true, proceed to next page.
		
		//if false, return to login screen. 
		
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String fname = request.getParameter("txtUsername");
		response.getWriter().append("<br><h1>" + fname + "</h1>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//send data to master server
		
		
		response.getWriter().append("You called POST");
	}

}
