package client;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JWindow;


public  class SnakeTest {
	
	public static void main(String args[]){
	JFrame frame = new JFrame();
	frame.setLayout(new BorderLayout());
	Grid g1 = new Grid(50, 50);
	ControlBar cb = new ControlBar();

	ArrayList<Tile> snake = new ArrayList<Tile>();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(750,750);

	snake.add(new Tile(0,1));
	snake.add(new Tile(0,2));
	snake.add(new Tile(0,3));
	snake.add(new Tile(0,4));
	snake.get(0).setFilled(Color.green);
	snake.get(1).setFilled(Color.green);
	snake.get(2).setFilled(Color.green);
	snake.get(3).setFilled(Color.green);
	g1.setSnake(snake);
	frame.add(g1, BorderLayout.CENTER);
	frame.add(cb,BorderLayout.SOUTH);
	frame.setVisible(true);
	
	//needs a get player, to show this or a simple confirm dialog box.
	JOptionPane.showConfirmDialog(null,  cb,
            "Ready? ",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE);
	 //alternates
/*	JOptionPane.showMessageDialog(frame,
		    "Waiting...","Waiting for other players",JOptionPane.PLAIN_MESSAGE);
	JOptionPane.showMessageDialog(frame,
		    "Winner!!!","You Have Won!",JOptionPane.PLAIN_MESSAGE);
	JOptionPane.showMessageDialog(frame,
		    "LOSER!!","You lost.",JOptionPane.PLAIN_MESSAGE);*/
	}
}
