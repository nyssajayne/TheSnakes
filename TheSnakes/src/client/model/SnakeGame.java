package client.model;

import java.io.IOException;
import client.view.ClientFrame;
import shared.Packet;
import shared.SnakeInterface;

public class SnakeGame extends Thread implements SnakeInterface{

	private ClientFrame clientFrame;
	private Packet info;
	private int gameStatus = STATUS_WAIT;
	
	public SnakeGame(ClientFrame clientFrame)
	{
		this.clientFrame = clientFrame;
	}
	
	//TODO Connect to the host and get ready to start a game.
	private void initGame() {
		try 
		{
			clientFrame.getSockHandler().initConnection("localhost");
			try {
				info = (Packet) clientFrame.getSockHandler().getIn().readObject();
				System.out.println(info);
				
				clientFrame.getSockHandler().getOut().writeUTF(clientFrame.getCb().getPlayerName() + clientFrame.getCb().getPlayers());
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
			
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Constantly run while a game is in progress.
	public void gameStart() throws IOException, ClassNotFoundException {
		Packet pack;
		
		while(gameStatus != STATUS_LOSE || gameStatus != STATUS_WIN) {
			pack = 	(Packet)clientFrame.getSockHandler().getIn().readObject();
			for(int i=0; i<pack.getPlayers().size(); i++)
			{
				clientFrame.getGrid().setSnake(pack.getPlayers().get(i).getSnake().getSegments());
			}
			gameStatus = pack.getGameStatus();
		}
		//TODO Do something when a game is over (win or lose)
		
	}
	
	public void run()
	{
		try {
			gameStart();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
