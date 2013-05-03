package server.controller;

import java.awt.Point;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import server.model.GameLogic;
import shared.Player;
import shared.SnakeInterface;

public class TheServer implements SnakeInterface, Runnable {
	
	private final int PORT = 1985;
	
	private List<Player> players = new ArrayList<Player>();
	private List<Thread> clients = new ArrayList<Thread>();
	private List<ClientListener> clientRunnables = new ArrayList<ClientListener>();
	
	private int numPlayers = 2;
	private ServerSocket serverSocket;
	private Point bounds = new Point(50,50);
	
	private Map<String,Integer> statusMap = new HashMap<String,Integer>();
	
	final private GameLogic gameLogic;
	
	public TheServer(int numPlayers, Point bounds)
	{
		//this.bounds = bounds;
		gameLogic = new GameLogic(this.bounds);
		this.numPlayers = numPlayers;
		try{
			serverSocket = new ServerSocket(PORT);
			System.out.println("New Snake Server at " + new Date());
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	/*
	 * TODO: need an end condition for this loop
	 */
	public void run() {
		
		for (int i=0; i<numPlayers; i++) {
			
			ClientListener client;
			try {				
				client = new ClientListener(this,serverSocket.accept());
				clients.add(new Thread(client));
				clientRunnables.add(client);
				
				//get position from client
				String message = client.getIn().readUTF();
				
				//Set player's name
				String playerName = message.substring(0, message.length()-2);
				System.out.println("Player name: " + playerName);
				client.setPlayerName(playerName);
				
				//Set player's position
				int position = Integer.parseInt(message.substring(message.length()-2));
				int status = setPosition(position);
				if(status==STATUS_NOT_VALID) {
					client.sendInfo(null,null,status);
				}
				
				Player player = new Player(playerName);
				player.setPosition(position);
				players.add(player);
				System.out.println("Player position: " + position);

				
				statusMap.put(playerName, status);
				System.out.println("New Contestant: ");
				client.sendInfo(gameLogic.getPlayers(),null, status);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Once this loop is complete and there is enough players
		//The game can begin
		
		for(Player p : players)
            statusMap.put(p.getName(), STATUS_PLAYING);
		
		//Start each thread in the array.
		try{
			Iterator<Thread> c1 = clients.iterator();
		while(c1.hasNext() ) {	
			c1.next().start();
			System.out.println("Weeeee!");
		}
		}catch(IllegalThreadStateException e){
			System.out.println("He's dead jim!");
			e.printStackTrace();
		}
		
		//Tell the GameLogic who the players are
		gameLogic.setPlayers(players);
		gameLogic.setStatusMap(statusMap);
		
		while(true) {
			// step the game forward one tick
			gameLogic.step();
			
			// retrieve game status 
			//statusMap = gameLogic.getStatusMap();
			
			// send info to clients
			for(ClientListener client: clientRunnables) {
				System.out.println("Sending client info .... " + new Date());
				System.out.println(gameLogic.getPlayers());
				client.sendInfo(gameLogic.getPlayers(), gameLogic.getFood(), statusMap.get(client.getPlayerName()));
			
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/*
	 * This will set the status of the players, 
	 * this is called from each ClientListener	
	 */
	public void setStatus(int status, String playerName) {
		statusMap.put(playerName,status);
	}
	
	public int setPosition(int tryPosition)
	{
		int status = STATUS_WAIT;
		
		for(Player p : players)
		{
			if(p.getPosition()==tryPosition)
				status = STATUS_NOT_VALID;
		}
		
		return status;
	}
	
	public static void main(String args[])
	{
		new TheServer(2,new Point(50,50)).run();
	}

}
