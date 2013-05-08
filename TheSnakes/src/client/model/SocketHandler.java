package client.model;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketHandler {
	private Socket socket;
	private ObjectInputStream in;
	private DataOutputStream out;
	
	public SocketHandler(){
		
	}
	
	public boolean initConnection(String ip, int port) throws UnknownHostException, 
	IOException {
		socket = new Socket(ip, port);
		in = new ObjectInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		
		
		return true;
	}
	
	public void sendMove(int move) throws IOException
	{
		out.writeInt(move);
		out.flush();
	}
	
	public void closeConnection() throws IOException{
		socket.close();
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public DataOutputStream getOut() {
		return out;
	}

	public Socket getSocket() {
		return socket;
	}
	public boolean isAlive(){
		return(socket != null);
			
	}
	public void sendName(String name) throws IOException
	{
		out.writeUTF(name);
	}
	
}
