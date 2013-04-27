package client;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;


public  class SnakeTest {
	
	public static void main(String args[]){
	Grid g1 = new Grid(100, 100);
	ArrayList<Tile> snake = new ArrayList<Tile>();
	g1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	g1.setSize(1500,1500);

	snake.add(new Tile(0,1));
	snake.add(new Tile(0,2));
	snake.add(new Tile(0,3));
	snake.add(new Tile(0,4));
	snake.get(0).setFilled(Color.green);
	snake.get(1).setFilled(Color.green);
	snake.get(2).setFilled(Color.green);
	snake.get(3).setFilled(Color.green);
	g1.setSnake(snake);
	g1.setVisible(true);
	
	 
	}
}
