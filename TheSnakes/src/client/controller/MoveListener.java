package client.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import client.view.ClientFrame;

public class MoveListener implements KeyListener, shared.SnakeInterface {
	
	ClientFrame client;
	
	public MoveListener(ClientFrame client)
	{
		this.client = client;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		 int key = e.getKeyCode();  
		 //ClientFrame client = (ClientFrame) e.getSource();
		 try{
			 switch(key) {
			 	case KeyEvent.VK_LEFT:
			 		System.out.println("Go left!");
			 		client.getSockHandler().sendMove(MOVE_LEFT);
			 		break;
			 	case KeyEvent.VK_UP:
			 		System.out.println("Go up!");
			 		client.getSockHandler().sendMove(MOVE_UP);
			 		break;
			 	case KeyEvent.VK_RIGHT:
			 		System.out.println("Go right!");
			 		client.getSockHandler().sendMove(MOVE_RIGHT);
			 		break;
			 	case KeyEvent.VK_DOWN:
			 		System.out.println("Go down!");
			 		client.getSockHandler().sendMove(MOVE_DOWN);
			 		break;
			 }
		 }catch(IOException ex){
	    	 ex.printStackTrace();
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
}
