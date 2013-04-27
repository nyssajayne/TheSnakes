package server.model;

import java.awt.Point;

/*
 * At the moment all this holds is a Point but could easily hold other stuff. As we discussed it's pretty similar to Tile so 
 * this can be changed easily
 */

public class SnakeBody {
	
	private Point position;
	
	public SnakeBody(int x, int y)
	{
		position = new Point(x,y);
	}
	public SnakeBody(Point pos)
	{
		position = new Point(pos);
	}
	public void setPos(Point position)
	{
		this.position.setLocation(position);
	}
	
	public Point getPos()
	{
		return position;
	}
	
	public String toString()
	{
		return "POS> " + position;
	}

}
