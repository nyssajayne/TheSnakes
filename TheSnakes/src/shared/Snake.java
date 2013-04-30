package shared;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

public class Snake {
	
	private static final int START_SEGMENTS = 2;

	private LinkedList<Tile> segments;
	private Color color;
	private Point bounds;
	private volatile int a;
	
	private int dx, dy;
	
	public Snake(int x, int y, Color color, Point bounds)
	{
		segments = new LinkedList<Tile>();
		segments.addFirst(new Tile(x,y));
		growSnake(START_SEGMENTS - 1);
		this.color = color;
		this.bounds = bounds;
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
		/*
		 *this is some simple detection for the bounds of the board
		 */
		Point headpos = segments.getFirst().getPoint();
		if(headpos.x < 0) {
			headpos.x = bounds.x; 
		} else if(headpos.x >= bounds.x) {
			headpos.x = 0;
		}
		if(headpos.y < 0) {
			headpos.y = bounds.y;
		} else if(headpos.y >= bounds.y) {
			headpos.y = 0;
		}
		// then move the head segment by the direction 
		headpos.translate(dx, dy);
		/*
		 *  This checks for collisions for the snake to itself, 
		 *  somehow it needs to tell the calling object that a collision has happened.
		 */
		for(int i = segments.size() - 1 ; i >= 1; i++) {
			if(headpos.equals(segments.get(i).getPoint())) {
				return false;
			}
		}
		return true;
	}

	private void growSnake(int n) {
		while(n > 0) {
			// adds a new segment, growing it in the opposite direction of movement.
			Point p = new Point(segments.getLast().getPoint());
			p.translate(-dx,-dy);
			segments.add(new Tile(p));
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
