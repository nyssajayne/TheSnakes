package client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class TheClient extends JFrame implements KeyListener
{
	
	/*
	 * This is just a test class I made for drawing the snake to see that it works
	 * I imagine we would have similar methods like run(), gameInit() and gameLoop() somewhere on the server
	 */


		//private Snake test_player;
		//private Snake test_player2;
		
		private int windowWidth = 800;
		private int windowHeight = 600;
		
		private int dx,dy;
		
		private DataInputStream in;
		private DataOutputStream out;
		
		public TheClient() {
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.setSize(windowWidth, windowHeight);
	        this.setResizable(false);
	        this.setLocationRelativeTo(null);
	        this.setVisible(true);
	        // this enables double buffering
	        // sometime it doesn't like to work and throws an exception :(
	        this.createBufferStrategy(2);
	        this.addKeyListener(this);
			initGame();
		}

		private void initGame() {
			//This is where we will initialise a connection with the server
			Rectangle bounds = new Rectangle(windowWidth,windowHeight);
			
			try 
			{
				Socket socket = new Socket("localhost", 1985);
				
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			/* This code starts a new snake
			 * but what we actually need to do is work with the protocol
			 * to use something like out.writeUTF(all the snake gear);
			 * 
			 * test_player = new Snake(10,10,Color.BLUE,bounds,10);
			 * test_player2 = new Snake(20,20,Color.RED,bounds,10);
			 * dx = 0;
			 * dy = 0;
			 */
		}
		private void run() {
			while(true) {
				gameLoop();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		private void gameLoop() {
			/* Need to send gear to the server here
			 * Something like out.writeInt();
			 * Would use Rob's balling Protocol/SnakeInterface
			 * test_player.move(dx, dy);
			 */
			drawFrame();
		}
		/*
		 * I've found this is quite a good drawing loop for swing/awt, it uses double buffering so it looks smooth
		 */
		private void drawFrame() {
			BufferStrategy bf = this.getBufferStrategy();
	        Graphics g = null;   
	        try {
	            g = bf.getDrawGraphics();
	            g.setColor(Color.GRAY);
	            g.fillRect(0, 0, windowWidth, windowHeight);
	           
	            drawSnake(g);
	            
	        } finally {
	            g.dispose();
	        }
	        // this swaps the buffers to display the image
	        bf.show();
	        /*
	         * I'm not entirely sure what this method does, but apparently it's useful for animation, which is kinda what is being done
	         */
	        Toolkit.getDefaultToolkit().sync();
		}
		
		 private void drawSnake (Graphics g) {
		    /* This is where you should receive stuff from the server I imagine.
		     * so in.readInt(); or what have you.
		     * test_player.draw(g);
		     * test_player2.draw(g);
		     */
		 }
		@Override
		public void keyPressed(KeyEvent e) {
			 int key = e.getKeyCode();       
		     switch(key) {
		    	 case 37:
		    		 dx = -1;
		    		 dy = 0;
		    		 break;
		    	 case 38:
		    		 dx = 0;
		    		 dy = -1;
		    		 break;
		    	 case 39:
		    		 dx = 1;
		    		 dy = 0;
		    		 break;
		    	 case 40:
		    		 dx = 0;
		    		 dy = 1;
		    		 break;
		     }	 
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
		}
		@Override
		public void keyTyped(KeyEvent arg0) {
		
		}
		public static void main(String[] args) {
		        new TheClient().run();
		}

}
