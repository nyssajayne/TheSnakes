package client.model;

import java.awt.Point;
import java.io.IOException;

import server.controller.TheServer;
import shared.controller.SnakeInterface;
import shared.model.Packet;
import client.view.ClientFrame;

public class SnakeGame extends Thread implements SnakeInterface{

	private ClientFrame clientFrame;
	private Packet info;
	private int gameStatus = STATUS_WAIT;
	
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

				Point p = new Point(Integer.parseInt(clientFrame.getCb().getField_len().getText()),Integer.parseInt(clientFrame.getCb().getField_width().getText()));
				
				CreateServer(clientFrame.getCb().getPlayers(),p);
				System.out.println("created");
				initGame();
	}

	//TODO Connect to the host and get ready to start a game.
	private void initGame() {
		try 
		{
			
			
			
			clientFrame.getSockHandler().initConnection("localhost");
			try {
				Packet p = (Packet) clientFrame.getSockHandler().getIn().readObject();
				Point point = p.getPoint();
				clientFrame.setGrid(point.x, point.y);
				clientFrame.getSockHandler().getOut().writeUTF(clientFrame.getCb().getPlayerName() + (clientFrame.getCb().getCbx_pos().getSelectedItem()));
				clientFrame.getSockHandler().getOut().flush();
				info = (Packet) clientFrame.getSockHandler().getIn().readObject();
				while(info.getGameStatus() != STATUS_PLAYING){
				System.out.println("waiting..");
				info = (Packet) clientFrame.getSockHandler().getIn().readObject();
				}
				this.start();
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

		System.out.println(gameStatus);
		while(gameStatus != STATUS_LOSE && gameStatus != STATUS_WIN) {
			pack = 	(Packet)clientFrame.getSockHandler().getIn().readObject();
			for(int i=0; i<pack.getPlayers().size(); i++)
			{
				clientFrame.getGrid().addSnake(pack.getPlayers().get(i).getSnake().getSegments());
			}
			clientFrame.getGrid().addFood(pack.getFood());
			clientFrame.repaint();
			gameStatus = pack.getGameStatus();
			try{
				Thread.sleep(75);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
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
