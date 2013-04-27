package client;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;


public class Grid extends JFrame {
	private static int TILE_LENGTH = 10;
	private static int TILE_WIDTH = 10;
	private int length;
	private int width;
	private ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>();
	
	public Grid(int length, int width){

		this.length = length;
		this.width = width;
		initTiles(length,width);

	}

		  
		  public void paint(Graphics g){
			  super.paint(g);
		  int x = 30;
		  int y = 30;
		  for(int i=0;i < length;i++){
			  for(int j=0; j < width;j++){

			  g.setColor(tiles.get(i).get(j).getFilled());
		  	  g.fillRect(x,y,TILE_LENGTH, TILE_WIDTH);
			  g.setColor(Color.BLACK);  
			  g.drawRect(x, y, TILE_LENGTH, TILE_WIDTH);
		  	  
		  	  
		  	  x += 10;
			  }
			  x=30;
			  y +=10;
		  		}
		  }
	  
		  public void setSnake(ArrayList<Tile> snake){
			  int x;
			  int y;
			  for(Tile piece: snake){
				  x = piece.getX();
				  y = piece.getY();
				  tiles.get(x).get(y).setFilled(piece.getFilled());
				  this.repaint();
			  }
			  
		  }
		  
		  public void initTiles(int x, int y){
			  for(int i=0;i < x;i++){
				  System.out.println("X is " + x + " Y is: " + y);
				  ArrayList<Tile> a1 = new ArrayList<Tile>();
				  for(int j=0; j < y;j++){
					  a1.add(j,new Tile(i,j));
					  tiles.add(i,a1);
				  
			  }
	  }
	 } 
}
