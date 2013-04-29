package client;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JFrame;
import server.model.Packet;
import client.controller.MoveListener;
import client.model.SocketHandler;

@SuppressWarnings("serial")
public class TheClient extends JFrame implements SnakeInterface
{
	
	/*
	 * This is just a test class I made for drawing the snake to see that it works
	 * I imagine we would have similar methods like run(), gameInit() and gameLoop() somewhere on the server
	 */


		//private Snake test_player;
		//private Snake test_player2;
		
		private int windowWidth = 800;
		private int windowHeight = 600;
		private SocketHandler sockHandler;
		private ObjectInputStream in;
		private DataOutputStream out;
		private int gameStatus = STATUS_WAIT;
		private Packet info;
		Grid g1 = new Grid(50, 50);
		ControlBox cb = new ControlBox(this);
		public TheClient() {
			this.setLayout(new BorderLayout());
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.setSize(windowWidth, windowHeight);
	        this.setResizable(false);
	        this.setLocationRelativeTo(null);
	        g1.addKeyListener(new MoveListener());
	        this.add(g1,BorderLayout.CENTER);
	        this.setVisible(true);
	        this.add(cb,BorderLayout.SOUTH);
	        // this enables double buffering
	        // sometime it doesn't like to work and throws an exception :(
	        this.createBufferStrategy(2);

			initGame();
		}

		private void initGame() {
			//This is where we will initialise a connection with the server
			try 
			{
				sockHandler.initConnection("localhost");
				try {
					info = (Packet) in.readObject();
					System.out.println(info);
					
					out.writeUTF(cb.getPlayerName() + cb.getPlayers());
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				}
				
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		public void gameStart() throws IOException, ClassNotFoundException {
			Packet pack;
			
			while(gameStatus != STATUS_LOSE || gameStatus != STATUS_WIN) {
				pack = 	(Packet)sockHandler.getIn().readObject();
				for(int i=0; i<pack.getPlayers().size(); i++)
				{
					g1.setSnake(pack.getPlayers().get(i).getSnake().getSegments());
				}
				gameStatus = pack.getGameStatus();
			}
		}
		
		public SocketHandler getSockHandler() {
			return sockHandler;
		}
		
		public static void main(String args[]){
			new TheClient();
		}

}
