package client.model;

import java.awt.Point;
import java.io.IOException;

import server.controller.TheServer;
import shared.Packet;
import shared.SnakeInterface;
import client.view.ClientFrame;

public class SnakeGame extends Thread implements SnakeInterface{

	private ClientFrame clientFrame;
	private Packet info;
	private int gameStatus = STATUS_WAIT;
	private String playerName;
	private int pId;
	public SnakeGame(ClientFrame clientFrame)
	{
		this.clientFrame = clientFrame;

		while(!clientFrame.isCreate() && !clientFrame.isJoin()){
			System.out.println("not Ready!!");
		}
		if(clientFrame.isCreate()) {
			System.out.println("creating..");
			createGame();
		}  
			while(!clientFrame.isJoin()) {
				System.out.println("join me!");
			}
			initGame();
		
	}
	
	private void createGame(){

			System.out.println("starting..");

				//Point p = new Point(Integer.parseInt(clientFrame.getCb().getField_len().getText()),Integer.parseInt(clientFrame.getCb().getField_width().getText()));
				
				CreateServer(clientFrame.getCb().getPlayers(),null);
				System.out.println("created");

	}

	//TODO Connect to the host and get ready to start a game.
	private void initGame() {
		try 
		{
			

			
			clientFrame.getSockHandler().initConnection("localhost");
			try {
				clientFrame.getSockHandler().getOut().writeUTF(clientFrame.getCb().getPlayerName() + clientFrame.getCb().getPlayers());
				
				
				info = (Packet) clientFrame.getSockHandler().getIn().readObject();
				while(info.getGameStatus() != STATUS_PLAYING){

				info = (Packet) clientFrame.getSockHandler().getIn().readObject();
				System.out.println("Info " +info);
				
				}
				

//				for(int i = 0; i < info.getPlayers().size(); i++){
//					if(playerName == info.getPlayers().get(i).getName())
//						pId = i;
//				}
				gameStart();
					//clientFrame.getSp().getLbl_curColour().setText(info.getPlayer(pId).getColor().toString());
					
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
		System.out.println("Made it!");
		while(gameStatus != STATUS_LOSE && gameStatus != STATUS_WIN) {
			pack = 	(Packet)clientFrame.getSockHandler().getIn().readObject();
			System.out.println("working!");
			for(int i=0; i<pack.getPlayers().size(); i++)
			{
				clientFrame.getGrid().setSnake(pack.getPlayers().get(i).getSnake().getSegments());
				clientFrame.repaint();
			}
			gameStatus = pack.getGameStatus();
		}
		//TODO Do something when a game is over (win or lose)

	}

	public void CreateServer(int players, Point bounds){
		TheServer server = new TheServer(players, bounds);
		new Thread(server).start();
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
