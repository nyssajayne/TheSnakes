package client.view;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JFrame;

import shared.Packet;
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
	
		private int windowWidth = 800;
		private int windowHeight = 600;
		private SocketHandler sockHandler;
		private Grid grid = new Grid(50, 50);
		private ControlBox cb = new ControlBox(this);

		public ClientFrame() {
			this.setLayout(new BorderLayout());
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.setSize(windowWidth, windowHeight);
	        this.setResizable(false);
	        this.setLocationRelativeTo(null);
	        grid.addKeyListener(new MoveListener());
	        this.add(grid,BorderLayout.CENTER);
	        this.setVisible(true);
	        this.add(cb,BorderLayout.SOUTH);
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
		
		public static void main(String args[]){
			new ClientFrame();
		}
		
		public ControlBox getCb() {
			return cb;
		}

}
