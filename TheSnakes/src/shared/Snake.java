package shared;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.util.LinkedList;

public class Snake implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1348350780452014999L;

	private static final int START_SEGMENTS = 8;

	private LinkedList<Tile> segments;
	private Color color;
	private Point bounds;
	private volatile int dx, dy;
	
	public Snake(int x, int y, Color color, Point bounds)
	{
		this.color = color;
		this.bounds = bounds;
		segments = new LinkedList<Tile>();
		segments.addFirst(new Tile(x,y,color));
		growSnake(START_SEGMENTS - 1);
	}
	
	public String toString() {
		return segments.toString();
	}
	
	public void setDirection(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public Point getHeadPos() {
		return segments.getFirst().getPoint();
	}
		
	public boolean move()
	{
		// starting at the last segment, loop through each one 
		for(int n = segments.size()-1; n >= 1; n--) {
			// set the pos of the segment to the segment in front of it
			// ( except for the head)
			segments.get(n).setPoint(segments.get(n-1).getPoint());
		}
		Point headpos = segments.getFirst().getPoint();
		headpos.translate(dx, dy);
		checkBounds(headpos);
		/*
		 *  This checks for collisions for the snake to itself
		 */
		for(int i = segments.size() - 1 ; i >= 1; i--) {
			if(headpos.equals(segments.get(i).getPoint())) {
				return false;
			}
		}
		return true;
	}
	/*
	 *this is some simple detection for the bounds of the board
	 */
	private void checkBounds(Point p) {
		if(p.x < 0) {
			p.x = bounds.x-1; 
		} else if(p.x >= bounds.x) {
			p.x = 0;
		}
		if(p.y < 0) {
			p.y = bounds.y-1;
		} else if(p.y >= bounds.y) {
			p.y = 0;
		}
	}
	
	public void growSnake(int n) {
		while(n > 0) {
			// adds a new segment, growing it in the opposite direction of movement.
			Point p = new Point(segments.getLast().getPoint());
			p.translate(-dx,-dy);
			checkBounds(p);
			segments.add(new Tile(p,this.color));
	        n--;
	    }
	}
	/*
	 * This is only used for testing! 
	 */
	public void draw(Graphics g) {
	    for(int n = 0; n < segments.size(); n++) {
	           Point p = segments.get(n).getPoint();
	           g.setColor(color);
	           g.fillRect(p.x, p.y, 10, 10);
	           g.setColor(Color.BLACK);
	           g.drawRect(p.x*15, p.y*15, 15, 15);
	       }
	    
	}

	public LinkedList<Tile> getSegments() {
		return segments;
	}

}
