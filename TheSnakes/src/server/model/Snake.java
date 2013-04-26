package server.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Snake {
	
	//private static final int START_SEGMENTS = 2;

	private LinkedList<SnakeBody> segments;
	private Color color;
	// bounds of the board the snake is on
	private Rectangle bounds;
		
	public Snake(int x, int y, Color color, Rectangle bounds, int intitalSegments)
	{
		segments = new LinkedList<SnakeBody>();
		segments.addFirst(new SnakeBody(x,y));
		growSnake(intitalSegments);
		this.color = color;
		this.bounds = bounds;
	}
		
	public void move(int dx, int dy)
	{
		// starting at the last snake, loop through each one 
		for(int n = segments.size()-1; n >= 1; n--) {
			// set the pos of the segment to the segment in front of it
			// ( except for the head)
			segments.get(n).setPos(segments.get(n-1).getPos());
		}
		/*
		 this is some simple detection for the bounds of the board
		 doesn't fully work atm as the test method i had for drawing
		 actually alters the snake's position somewhat
		 collision detection regarding other snakes would go some where else in the code
		*/
		Point headpos = segments.getFirst().getPos();
		if(headpos.x < 0) {
			headpos.x = bounds.width; 
		} else if(headpos.x >= bounds.width) {
			headpos.x = 0;
		}
		if(headpos.y < 0) {
			headpos.y = bounds.height;
		} else if(headpos.y >= bounds.height) {
			headpos.y = 0;
		}
		// then move the head segment by the direction 
		headpos.translate(dx, dy);
	}
	/*
	 * this grows the snake a certain number of segments
	 * perhaps make this so it only grows 1 segment at a time instead of all at once
	 * so each "tick" of the server will only add 1 segment at a time
	 */
	private void growSnake (int n) {
		while(n > 0) {
			// adds a new segment, giving it the position of the last segment
			segments.add(new SnakeBody(segments.getLast().getPos()));
	        n--;
	    }
	}
	/*
	 * just a testing drawing method
	 */
	public void draw(Graphics g) {
	    for(int n = 0; n < segments.size(); n++) {
	           Point p = segments.get(n).getPos();
	           g.setColor(color);
	           // if you take out the "*15" factor for the x and y you will be able to see the boundary collision detection
	           g.fillRect(p.x*15, p.y*15, 15, 15);
	           g.setColor(Color.BLACK);
	           // this extra call just gives each square a border, might be useful
	           g.drawRect(p.x*15, p.y*15, 15, 15);
	       }
	}
}
