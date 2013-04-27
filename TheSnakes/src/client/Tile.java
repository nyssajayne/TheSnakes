package client;
import java.awt.Color;
import java.awt.Point;


public class Tile {
	private Point p;
	private Color filled;

	public Tile(int x, int y){
		p = new Point(x,y);
		filled = Color.red;
	}
	public Tile(Point p1){
		p = new Point(p1);
	}
	public Point getPoint() {
		return p;
	}

	public void setPoint(Point p1){
		p = p1;
	}
	
	public void setPoint(int x,int y){
		p.x = x;
		p.y = y;
	}
	public int getX(){
		return p.x;
	}
	public int getY(){
		return p.y;
	}
	public Color getFilled(){
		return filled;
	}
	
	public void setFilled(Color color){
		this.filled = color;
	}

	@Override
	public String toString() {
		return "Tile [x=" + p.x + ", y=" + p.y + "]";
	}
	

}
