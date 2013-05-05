package client.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import shared.model.Food;
import shared.model.Snake;
import shared.model.Tile;


@SuppressWarnings("serial")
public class Grid extends JPanel {
	private static int TILE_LENGTH = 10;
	private static int TILE_WIDTH = 10;
	private int length;
	private int width;
	private ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>();
	private ArrayList<Snake> s = new ArrayList<Snake>();
	int count = 0;
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
		Color c;
		drawBoard(g);
		for(int i=0;i < length;i++){
			for(int j=0; j < width;j++){
				if((c =tiles.get(j).get(i).getFilled()) != Color.black)
					g.setColor(c);
					if(c == null)
						g.setColor(Color.black);
				if(g.getColor() != Color.BLACK)
					g.fillRect(x,y,TILE_LENGTH, TILE_WIDTH);
					tiles.get(j).get(i).setFilled( null);



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



	public void initTiles(int x, int y){

		for(int i=0;i < x;i++){
			ArrayList<Tile> a1 = new ArrayList<Tile>();
			for(int j=0; j < y;j++){
				a1.add(j,new Tile(i,j,null));
				tiles.add(i,a1);

			}
		}
	}

	public void addSnake(LinkedList<Tile> linkedList) {
		int x;
		int y;
		for(Tile piece: linkedList){
			x = piece.getX();
			y = piece.getY();
			tiles.get(x).get(y).setFilled(piece.getFilled());
		}		
	} 
	
	public void addFood(List<Food> foodItems){
		for(Food f : foodItems) {
			int x = f.getTile().getX();
			int y = f.getTile().getY();
			tiles.get(x).get(y).setFilled(f.getColor());
		}
	}
	
	public void clearSnakes(){
		this.s.clear();
	}
	
	public boolean isFocusable()
	{
	  return true;
	}
}
