package client.controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import shared.SnakeInterface;

import client.view.ClientFrame;

//class ensures sockets are closed before closing
public class ExitListener implements WindowListener, SnakeInterface{
	ClientFrame cf;
	public ExitListener(ClientFrame clientFrame) {
		this.cf = clientFrame;
		

	}
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		int confirm = JOptionPane.showOptionDialog(null, "Are You Sure to Close Application?", "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm == 0) {
        	if(cf.isJoin()) {
        		try {
					cf.getSockHandler().sendMove(STATUS_LOSE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		try {
        			cf.getSockHandler().getOut().close();
        			cf.getSockHandler().getIn().close();
        			cf.getSockHandler().getSocket().close();
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        	}
        	
        	cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        if(confirm == 1) {
        	cf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
		
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
