package client.view;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JFrame;

import shared.Packet;
import client.controller.ExitListener;
import client.controller.MoveListener;
import client.model.SnakeGame;
import client.model.SocketHandler;

@SuppressWarnings("serial")
public class ClientFrame extends JFrame implements shared.SnakeInterface
{
	
	/*
	 * This is just a test class I made for drawing the snake to see that it works
	 * I imagine we would have similar methods like run(), gameInit() and gameLoop() somewhere on the server
	 */
		private boolean join = false;
		private boolean create = false;
		private int windowWidth = 1000;
		private int windowHeight = 650;
		private SocketHandler sockHandler = new SocketHandler();
		private Grid grid = new Grid(50, 50);
		private String playerName = "none";
		private ControlBox cb = new ControlBox(this);
		private StatusPanel sp = new StatusPanel(this);


		public ClientFrame() {
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//this.addWindowListener(new ExitListener(this)); /could not test socket
			this.setLayout(new BorderLayout());
	        this.setSize(windowWidth, windowHeight);
	        this.setResizable(true);
	        this.setLocationRelativeTo(null);
	        grid.addKeyListener(new MoveListener());
	        
	        this.add(grid,BorderLayout.CENTER);
	        this.add(cb,BorderLayout.SOUTH);
	        this.add(sp, BorderLayout.EAST);
	        
	        this.setVisible(true);
	        // this enables double buffering
	        // sometime it doesn't like to work and throws an exception :(
	        //this.createBufferStrategy(2);
		}

		
		public Grid getGrid() {
			return grid;
		}

		public SocketHandler getSockHandler() {
			return sockHandler;
		}
		

		
		public StatusPanel getSp() {
			return sp;
		}


		public ControlBox getCb() {
			return cb;
		}


		public String getPlayerName() {
			return playerName;
		}


		public void setPlayerName(String playerName) {
			this.playerName = playerName;
		}
		
		
		public boolean isCreate() {
			return create;
		}


		public void setCreate(boolean create) {
			this.create = create;
		}


		public static void main(String args[]){
			SnakeGame thread = new SnakeGame(new ClientFrame());
			
		}


		public boolean isJoin() {
			// TODO Auto-generated method stub
			return join;
		}
		public void setJoin(Boolean b){
			this.join = b;
		}

}
