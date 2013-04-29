package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.view.ControlBox;

public class JoinListener implements ActionListener{
	private ControlBox cb;
	
	public JoinListener(ControlBox cb) {
		this.cb = cb;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		cb.getClientFrame().setJoin(true);
		
	}

}
