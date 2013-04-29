package client.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import shared.SnakeInterface;
import client.controller.CreateListener;
import client.controller.JoinListener;
import client.controller.StoreInfoListener;

public class ControlBox extends JPanel implements SnakeInterface{
	private ClientFrame cf;
	private JLabel lbl_name = new JLabel("Enter Name(no spaces!):");
	private JTextField name = new JTextField(20);
	private JLabel lbl_players = new JLabel("Players");
	private String players[] = {"2","3","4"};
	private JComboBox cbx_players = new JComboBox(players);

	private JLabel lbl_pos = new JLabel("Pick a position:");
	private JComboBox cbx_pos = new JComboBox(POSITIONS);
	private JLabel lbl_confirm = new JLabel("Press when done:");
	private JButton btn_ok = new JButton("OK");
	private JLabel lbl_join = new JLabel("Join a server:");
	private JButton btn_join = new JButton("Join");
	private JLabel lbl_create = new JLabel("Create a server:");
	private JButton btn_create= new JButton("Create");
	private JLabel lbl_len = new JLabel("Select Size(length:");
	private JTextField field_len = new JTextField(10);
	private JLabel lbl_width = new JLabel("Enter width:");
	private JTextField field_width = new JTextField(10);

	public ControlBox(ClientFrame clientFrame){
		this.cf = clientFrame;
		this.setLayout(new GridLayout(2,8));
		btn_join.setEnabled(false);
		btn_create.setEnabled(false);
		btn_join.addActionListener(new JoinListener(this));
		btn_create.addActionListener(new CreateListener(this));
		btn_ok.addActionListener(new StoreInfoListener(this));
		this.add(lbl_name);
		this.add(name);
		this.add(lbl_players);
		this.add(cbx_players);
		this.add(lbl_pos);
		this.add(cbx_pos);
		this.add(lbl_confirm);
		this.add(btn_ok);
		this.add(lbl_join);
		this.add(btn_join);
		this.add(lbl_create);
		this.add(btn_create);
		this.add(lbl_len);
		this.add(field_len);
		this.add(lbl_width);
		this.add(field_width);



	}

	public String getPlayerName(){
		return name.getText();
	}
	public int getPlayers(){
		return cbx_players.getSelectedIndex();
	}
	public ClientFrame getClientFrame(){
		return cf;
	}

	public JButton getBtn_ok() {
		return btn_ok;
	}

	public JButton getBtn_join() {
		return btn_join;
	}

	public JButton getBtn_create() {
		return btn_create;
	}
	public JComboBox getCbx_players(){
		return cbx_players;
	}
	public JComboBox getCbx_pos(){
		return cbx_pos;
	}

	public JTextField getField_len() {
		return field_len;
	}

	public JTextField getField_width() {
		return field_width;
	}
	


}
