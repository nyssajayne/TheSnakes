package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import server.model.ClientListener;
import server.model.GameLogic;
import server.model.Packet;
import server.model.Player;
import server.model.SnakeInterface;

public class TheServer implements SnakeInterface {
	
	private final int PORT = 1985;
	
	private List<Player> players = new ArrayList<Player>();
	private List<Thread> clients = new ArrayList<Thread>();
	private List<ClientListener> clientRunnables = new ArrayList<ClientListener>();
	
	private Map<String,Integer> statusMap = new HashMap<String,Integer>();
	
	private int numPlayers;
	final private GameLogic gameLogic;
	
	public TheServer(int numPlayers)
	{
		gameLogic = new GameLogic();
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
			// send the status of the players to the game
			
			// get the result of the moves/collisions etc
			// check if someone has won, if so stop the game
			
			
			
			
			
			// send snake information to each client
			while(clientRunnables.iterator().hasNext())
				clientRunnables.iterator().next().sendInfo();

		}
	}
	
	
	public void setStatus(int status, String playerName) {
		statusMap.put(playerName,status);
	}
	
	public static void main(String args[])
	{
		new TheServer(4).run();
	}

}
