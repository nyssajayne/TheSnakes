package client;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JWindow;

import server.model.Snake;
import server.model.SnakeInterface;


public  class SnakeTest implements SnakeInterface{
	
	public static void main(String args[]){
	JFrame frame = new JFrame();
	frame.setLayout(new BorderLayout());
	Grid g1 = new Grid(50, 50);
	ControlBar cb = new ControlBar();

	Snake snake = new Snake(0,0,Color.YELLOW,BOUNDS);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(750,750);
	//needs to get snakes from server, to add them.

	g1.setSnake(snake.getSegments());
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
