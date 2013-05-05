package server.model;


import java.awt.Point;

public class BodySegment {

	private Point position;

	public BodySegment(int x, int y) {
		position = new Point(x,y);
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
	
}
