package server.controller;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import shared.Packet;
import shared.Player;
/*
 * This class should be in server.controller
 */
public class ClientListener implements Runnable {
	
	private Socket socket;
	private TheServer server;
	private DataInputStream in;
	private ObjectOutputStream out;
	private String playerName;
	//private GameLogic gameLogic;
	
	public ClientListener(TheServer server, Socket socket, String playerName) throws IOException
	{
		this.socket = socket;
		this.server = server;
		this.playerName = playerName;
		in = new DataInputStream(socket.getInputStream());
		out = new ObjectOutputStream(socket.getOutputStream());
	}
	
	public String getPlayerName() {
		return playerName;
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

	public void sendInfo(List<Player> snakes, int gameStatus) {
		sendPacket(new Packet(snakes, gameStatus));
	}
	private void sendPacket(Packet p1){
		   try{
			   out.writeObject(p1);
			   System.out.println(p1);
		   }catch(Exception e){
			   System.out.println(e.getStackTrace().toString());
		   }
	   }
	
	@Override
	public void run() {
		getMoves();		
	}

}
