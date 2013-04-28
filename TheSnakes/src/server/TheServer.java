package server;

import java.awt.Color;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import server.model.GameLogic;
import server.model.Player;
import server.model.SnakeRunnable;

public class TheServer {
	
	List<Player> players = new ArrayList<Player>();
	final private GameLogic gameLogic;
	
	public TheServer()
	{
		
		gameLogic = new GameLogic();
		
		try
		{
			ServerSocket s = new ServerSocket(1985);
			System.out.println("New Snake Server at " + new Date());
			
			//i<2 means up to two players.
			//How many players should we accommodate?
			for (int i=0; i<2; i++){
				SnakeRunnable snake = new SnakeRunnable(s.accept(), gameLogic, 10, 10, Color.BLUE, bounds);
				players.add(new Player(new Thread(snake), snake));
				players.get(i).setName(Integer.toString(i));
			}
			//Once this loop is complete and there is enough players
			//The game can begin
			
			//Tell the GameLogic who the players are
			gameLogic.setPlayers(players);
			
			//Start each thread in the array.
			while(players.iterator().hasNext())
				players.iterator().next().getSnake().start();

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
