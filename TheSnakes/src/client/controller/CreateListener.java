package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.view.ControlBox;

public class CreateListener implements ActionListener {
	private ControlBox cb;

	
	public CreateListener(ControlBox cb) {
		this.cb = cb;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		cb.getClientFrame().setCreate(true);
	}

}
