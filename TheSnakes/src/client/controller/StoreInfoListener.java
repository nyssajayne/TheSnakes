package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.view.ControlBox;

public class StoreInfoListener implements ActionListener {
	ControlBox cb;
	
	public StoreInfoListener(ControlBox cb) {
		this.cb = cb;
	}

	//pressing ok disables its self if name == valid, enable join/create
	//disable the combo boxes to prevent tampering. (Lock them in).
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(!cb.getPlayerName().isEmpty() && cb.getPlayerName().matches("[a-zA-Z]+")
				&& cb.getField_len().getText().matches("[0-9]+") && cb.getField_width().getText().matches("[0-9]+")){
			cb.getClientFrame().setPlayerName(cb.getPlayerName());
			cb.getBtn_create().setEnabled(true);
			
			cb.getBtn_join().setEnabled(true);
			cb.getBtn_ok().setEnabled(false);
			cb.getCbx_players().setEnabled(false);
			cb.getCbx_pos().setEnabled(false);
			cb.getField_len().setEditable(false);
			cb.getField_width().setEditable(false);
			
			//set name in status bar.
			cb.getClientFrame().getSp().setLbl_curPlayer(cb.getPlayerName());
			
		}
		

	}

}
