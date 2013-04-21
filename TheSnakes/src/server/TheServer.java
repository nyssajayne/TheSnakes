package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TheServer {
	
	private DataInputStream input;
	private DataOutputStream output;
	
	public TheServer()
	{
		try
		{
			ServerSocket serverSocket = new ServerSocket(1985);
			System.out.println("New Server at " + new Date());
			
			//This would accept just one snake
			Socket socket = serverSocket.accept();
			
			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());
			
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String args[])
	{
		new TheServer();
	}

}
