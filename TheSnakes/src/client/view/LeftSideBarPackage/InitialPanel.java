package client.view.LeftSideBarPackage;
import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.controller.InitialListener;
import client.view.LeftSideBar;


public class InitialPanel extends JPanel{
	
	private JLabel lbl_name = new JLabel("Enter Name(no spaces!):");
	private JTextField name = new JTextField(20);
	private JLabel lbl_join = new JLabel("Join a server:");
	private JButton btn_join = new JButton("Join");
	private JLabel lbl_create = new JLabel("Create a server:");
	private JButton btn_create= new JButton("Create");
	private LeftSideBar lsb;
	
	public InitialPanel(LeftSideBar lsb){
		this.lsb = lsb;
		this.add(lbl_name);
		this.add(name);
		this.add(lbl_create);
		this.add(btn_create);
		this.add(lbl_join);
		this.add(btn_join);
		btn_join.addActionListener(new InitialListener(this,lsb));
		btn_create.addActionListener(new InitialListener(this,lsb));
		this.setLayout(new GridLayout(5,2));
		
	}
	public JButton getJoin() {
		// TODO Auto-generated method stub
		return btn_join;
	}
	public JButton getCreate() {
		// TODO Auto-generated method stub
		return btn_create;
	}
	public void disableAll(){
		btn_join.setEnabled(false);
		btn_create.setEnabled(false);
		name.setEnabled(false);
	}
	
	public void enableJoin(){
		btn_join.setEnabled(true);
	}
	public String getPlayerName() {
		return name.getText();
	}
	
}
