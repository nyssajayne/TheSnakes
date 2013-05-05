package server.controller;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import shared.model.Food;
import shared.model.Packet;
import shared.model.Player;

public class ClientListener implements Runnable {
	
	private Socket socket;
	private TheServer server;
	private DataInputStream in;
	private ObjectOutputStream out;
	private String playerName;
	
	public ClientListener(TheServer server, Socket socket) throws IOException
	{
		this.socket = socket;
		this.server = server;
		in = new DataInputStream(socket.getInputStream());
		out = new ObjectOutputStream(socket.getOutputStream());
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public void getMoves()
	{
		try
		{
			while(true)
			{
				//read in from client.
				int moves = in.readInt();
				//Then move Snake with information in message.
				server.setStatus(moves, playerName);
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void sendInfo(List<Player> list, List<Food> food, int gameStatus) {
		sendPacket(new Packet(list, food, gameStatus));
	}
	private void sendPacket(Packet p1){
		   try{
			   out.reset();
			   out.writeObject(p1);
			   out.flush();
		   }catch(Exception e){
			   System.out.println(e.getStackTrace());
		   }
	   }
	
	public void sendBounds(Point p)
	{
		sendPacket(new Packet(p));
	}
	/*
	 * Closes connection to client
	 */
	public void close() throws IOException {
		out.close();
		in.close();
		socket.close();
	}
	
	public DataInputStream getIn()
	{
		return in;
	}
	
	public ObjectOutputStream getOut()
	{
		return out;
	}
	
	@Override
	public void run() {
		getMoves();		
	}

}
