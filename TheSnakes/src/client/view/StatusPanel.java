package client.view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class StatusPanel extends JPanel{
	
		private ClientFrame cf;
		private JLabel lbl_player = new JLabel("Player:");
		private JLabel lbl_curPlayer = new JLabel("none");
		private JLabel lbl_players = new JLabel("Players:");
		private JLabel lbl_amountPlayers = new JLabel("For now.. 1");
		private JLabel lbl_status = new JLabel("Status: ");
		private JLabel lbl_curStats = new JLabel("Idling");
		private JLabel lbl_colour = new JLabel("Colour: ");
		private JLabel lbl_curColour = new JLabel("none!");
		private String score = "Name: R Score 100"+"\n"+"Name:S Score 200";
		private int flags = 4;
		private JTextArea txtArea = new JTextArea(score, 4, 10);
		public StatusPanel(ClientFrame cf){
			this.cf = cf;
			
			txtArea.setEditable(false);
			this.add(lbl_player);
			this.add(lbl_curPlayer);
			this.add(lbl_players);
			this.add(lbl_amountPlayers);
			this.add(lbl_status);
			this.add(lbl_colour);
			this.add(lbl_curColour);
			this.add(lbl_curStats);
			this.add(txtArea);
			
			this.setSize(200,650);
			this.setLayout(new GridLayout(6
					,2));
			this.setVisible(true);
		}
		public void setLbl_curPlayer(String currentPlayer) {
			lbl_curPlayer.setText(currentPlayer);
		}
		public void setLbl_amountPlayers(String lbl_amountPlayers) {
			this.lbl_amountPlayers.setText(lbl_amountPlayers);
		}
		public void setFlags(int flags) {
			this.flags = flags;
		}
		public void setTxtArea(JTextArea txtArea) {
			this.txtArea = txtArea;
		}
		public void setLbl_Status(String s)
		{
			lbl_status.setText(s);
		}
		
		
		
}
