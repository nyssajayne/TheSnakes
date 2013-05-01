package client.view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;

import shared.Snake;
import shared.Tile;


public class Grid extends JPanel {
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

	//note objects cannot be colour black. else will not appear.
	public void paint(Graphics g){
		super.paint(g);
		int x = 30;
		int y = 30;
		drawBoard(g);
		for(int i=0;i < length;i++){
			for(int j=0; j < width;j++){

				g.setColor(tiles.get(i).get(j).getFilled());
				if(g.getColor() != Color.BLACK)
					g.fillRect(x,y,TILE_LENGTH, TILE_WIDTH);




				x += 10;
			}
			x=30;
			y +=10;
		}
	}
	public void drawBoard(Graphics g){

		int x = 30;
		int y = 30;
		for(int i=0;i < length;i++){
			for(int j=0; j < width;j++){
				g.setColor(Color.BLACK);  
				g.drawRect(x, y, TILE_LENGTH, TILE_WIDTH);


				x += 10;
			}
			x=30;
			y +=10;
		}
	}

	public void setSnake(LinkedList<Tile> linkedList){
		int x;
		int y;
		for(Tile piece: linkedList){
			x = piece.getX();
			y = piece.getY();
			tiles.get(x).get(y).setFilled(piece.getFilled());
			this.repaint();
		}

	}

	public void initTiles(int x, int y){
		for(int i=0;i < x;i++){
			ArrayList<Tile> a1 = new ArrayList<Tile>();
			for(int j=0; j < y;j++){
				a1.add(j,new Tile(i,j,null));
				tiles.add(i,a1);

			}
		}
	} 
}
