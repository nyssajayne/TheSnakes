package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.view.ControlBox;

public class CreateListener implements ActionListener {
	private ControlBox cb;
	private String name;
	private int pos;
	private int players;
	
	public CreateListener(ControlBox cb) {
		this.cb = cb;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//+2 due to 2 equalling index 0. 3=1, 4=2
//		pos = cb.getCbx_pos().getSelectedIndex()+2;
//		name = cb.getPlayerName();
//		players = cb.getPlayers();
		cb.getClientFrame().setCreate(true);

		//cb.getClientFrame().
	}

}
