package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TheServer {
	
	List<Thread> snakesAlive = new ArrayList<Thread>();
	
	public TheServer()
	{
		try
		{
			ServerSocket s = new ServerSocket(1985);
			System.out.println("New Snake Server at " + new Date());
			
			//i<2 means up to two players.
			//How many players should we accommodate?
			for (int i=0; i<2; i++)
				snakesAlive.add(new Thread(new SnakeRunnable(s.accept())));
			
			//Once this loop is complete and there is enough players
			//The game can begin
			//Start each thread in the array.
			for(Thread t : snakesAlive)
				t.start();

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
