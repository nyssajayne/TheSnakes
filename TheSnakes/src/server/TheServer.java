package server;

import java.awt.Point;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import server.model.ClientListener;
import server.model.GameLogic;
import shared.Player;
import shared.SnakeInterface;

public class TheServer implements SnakeInterface {
	
	private final int PORT = 1985;
	
	private List<Player> players = new ArrayList<Player>();
	private List<Thread> clients = new ArrayList<Thread>();
	private List<ClientListener> clientRunnables = new ArrayList<ClientListener>();
	
	private Map<String,Integer> statusMap = new HashMap<String,Integer>();
	
	private int numPlayers;
	private Point bounds;
	final private GameLogic gameLogic;
	
	public TheServer(int numPlayers, Point bounds)
	{
		gameLogic = new GameLogic(bounds);
		this.numPlayers = numPlayers;
		try
		{
			ServerSocket s = new ServerSocket(PORT);
			System.out.println("New Snake Server at " + new Date());
			
			//i<2 means up to two players.
			//How many players should we accommodate?
			for (int i=0; i<numPlayers; i++) {
				String name = Integer.toString(i); 
				ClientListener client = new ClientListener(this,s.accept(),name);
				clients.add(new Thread(client));
				clientRunnables.add(client);
				players.add(new Player(name));
				statusMap.put(name, STATUS_WAIT);
				
				System.out.println("New Contestant: ");
			}
			//Once this loop is complete and there is enough players
			//The game can begin
			
			//Tell the GameLogic who the players are
			gameLogic.setPlayers(players);
			
			//Start each thread in the array.
			while(clients.iterator().hasNext())
				clients.iterator().next().start();

		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void run() {
		
		while(true) {
			/*
			 * What needs to happen in here is: 
			 * 
			 * Get the status from the clients (which is being inputed 
			 * by the ClientListener threads) into statusMap
			 * 
			 * Tell the GameLogic what the clients inputs are (translate them)
			 * 
			 * Get the results from the GameLogic on what has happened
			 * 	- snake movement
			 * 	- collisions with other snakes or food
			 */
			// translate the constants in SnakeInterface into moves
			
			
			// step the game forward one tick
			gameLogic.step();
			
			
			// retrieve game status 
			
			
			// send info to clients
			while(clientRunnables.iterator().hasNext())
				clientRunnables.iterator().next().sendInfo();

		}
	}
	
	
	public void setStatus(int status, String playerName) {
		statusMap.put(playerName,status);
	}
	
	public static void main(String args[])
	{
		new TheServer(4,new Point(20,20)).run();
	}

}
