package client.view;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class RightSideBar extends JPanel{
	private ClientFrame cf;
	private StatusPanel sp;
	
	public RightSideBar(ClientFrame cf)
	{
		this.cf = cf;
		sp = new StatusPanel(cf);
		
		this.add(sp);
		this.setLayout(new GridLayout(3,1));
	}

	public StatusPanel getSp() {
		return sp;
	}
	
}
