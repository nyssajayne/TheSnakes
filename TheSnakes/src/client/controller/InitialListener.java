package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.view.LeftSideBar;
import client.view.LeftSideBarPackage.InitialPanel;

public class InitialListener implements ActionListener {
	private InitialPanel ip;
	private LeftSideBar lsb;
	
	public InitialListener(InitialPanel ip,LeftSideBar lsb)
	{
		this.ip = ip;
		this.lsb = lsb;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(!ip.getPlayerName().isEmpty() && ip.getPlayerName().matches("[a-zA-Z]+")){
		
		if(arg0.getActionCommand() == ip.getJoin().getText()){
			lsb.setPName(ip.getPlayerName());
			lsb.setJoinPanel();
			ip.disableAll();

		}
		else if(arg0.getActionCommand() == ip.getCreate().getText()){
			lsb.setPName(ip.getPlayerName());
			lsb.setCreatePanel();
			ip.disableAll();
		}
		else
			System.out.println("No button?!");
		}
		System.out.println("invalid name.");

	}

}
