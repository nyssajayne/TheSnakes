package client.view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

import shared.Snake;
import shared.Tile;


public class Grid extends JPanel {
	private static int TILE_LENGTH = 10;
	private static int TILE_WIDTH = 10;
	private int length;
	private int width;
	private ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>();
	private ArrayList<Snake> s = new ArrayList<Snake>();
	int count = 0;
	private boolean snakes;
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
		if(snakes)
			drawSnake(g);
//		for(int i=0;i < length;i++){
//			for(int j=0; j < width;j++){
//
//				g.setColor(tiles.get(i).get(j).getFilled());
//				if(g.getColor() != Color.BLACK)
//					g.fillRect(x,y,TILE_LENGTH, TILE_WIDTH);
//				tiles.get(i).get(j).setFilled(null);
//
//
//
//				x += 10;
//			}
//			x=30;
//			y +=10;
//		}

			}
	
	public void drawSnake(Graphics g){
		
		System.out.println("Drawin snakes!");
		int i = 0;
	//for(int i = 0;i < s.size();i++)
    for(int n = 0; n < 2; n++) {
    //	Color color = s.get(i).getSegments().getFirst().getFilled();

           Point p = s.get(i).getSegments().get(n).getPoint();
           System.out.println(p.toString());
           
           g.setColor(Color.blue);
           g.fillRect(p.x*10 + 30, p.y*10+ 30, 10, 10);
       }
	snakes = false;
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

	public void addSnake(Snake snake) {
		s.add(count++,snake);
		snakes = true;
		//repaint();
		
	} 
	public void clearSnakes(){
		this.s.clear();
	}
}
