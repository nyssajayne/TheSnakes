package server.model;


import java.awt.Color;
import java.awt.Point;

public class BodySegment {

	private Point position;
	private Color filled;
	
	public BodySegment(int x, int y) {
		position = new Point(x,y);
		filled = Color.red;
	}
	public BodySegment(Point pos) {
		position = new Point(pos);
	}
	public void setPos(Point position) {
		this.position.setLocation(position);
	}
	
	public Point getPos() { return position; }
	public String toString() {
		return "POS> " + position;
	}
	public void setFilled(Color color){
		this.filled = color;
	}
	
}
