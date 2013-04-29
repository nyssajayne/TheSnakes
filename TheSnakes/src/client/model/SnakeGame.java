package client.model;

import java.awt.Point;
import java.io.IOException;
import client.view.ClientFrame;
import server.TheServer;
import shared.Packet;
import shared.SnakeInterface;

public class SnakeGame extends Thread implements SnakeInterface{

	private ClientFrame clientFrame;
	private Packet info;
	private int gameStatus = STATUS_WAIT;
	private String playerName;
	private int pId;
	private boolean create;
	public SnakeGame(ClientFrame clientFrame)
	{
		this.clientFrame = clientFrame;

		while(!clientFrame.isCreate() || clientFrame.isJoin()){
			System.out.println("not Ready!!");
		}
		if(clientFrame.isCreate())
			create = true;
		initGame(create);
	}

	//TODO Connect to the host and get ready to start a game.
	private void initGame(Boolean create) {
		try 
		{

			clientFrame.getSockHandler().initConnection("localhost");
			if(create){
				Point p = new Point(Integer.parseInt(clientFrame.getCb().getField_len().toString()),Integer.parseInt(clientFrame.getCb().getField_width().toString()));
				CreateServer(clientFrame.getCb().getCbx_pos().getSelectedIndex()+2,p);

			}
			try {
				clientFrame.getSockHandler().getOut();
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

	public void CreateServer(int position, Point bounds){
		new TheServer(position, bounds);
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
