package client.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import client.SnakeInterface;
import client.TheClient;

public class MoveListener implements KeyListener, SnakeInterface {

	@Override
	public void keyPressed(KeyEvent e) {
		 int key = e.getKeyCode();  
		 TheClient client = (TheClient) e.getSource();
	     switch(key) {
	    	 case KeyEvent.VK_LEFT:
	    		 client.setSendThis(MOVE_LEFT);
	    		 break;
	    	 case KeyEvent.VK_UP:
	    		 client.setSendThis(MOVE_UP);
	    		 break;
	    	 case KeyEvent.VK_RIGHT:
	    		 client.setSendThis(MOVE_RIGHT);
	    		 break;
	    	 case KeyEvent.VK_DOWN:
	    		 client.setSendThis(MOVE_DOWN);
	    		 break;
	     }

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
