package client;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class ControlBar extends JPanel{
	private JLabel lbl1 = new JLabel("Press to Start");
	private JButton start = new JButton("Start");
	private JLabel lbl2 = new JLabel("Press to Quit");
	private JButton stop = new JButton("Stop");
	private JLabel lbl3 = new JLabel("Players");
	private String players[] = {"1","2","3","4"};
	private JComboBox dbx = new JComboBox(players);



	public ControlBar(){
		this.setLayout(new FlowLayout());
		this.add(lbl1);
		this.add(start);
		this.add(lbl2);
		this.add(stop);
		this.add(lbl3);
		this.add(dbx);
		
		
	}

}
