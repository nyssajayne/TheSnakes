package shared;
import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;


public class Tile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3460295869996439155L;
	private Point p;
	private Color filled;

	public String toString() {
		return p.toString();
	}
	
	public Tile(int x, int y, Color filled){
		p = new Point(x,y);
		this.filled = filled;
	}
	public Tile(Point p1, Color filled){
		p = new Point(p1);
		this.filled = filled;
	}
	public Point getPoint() {
		return p;
	}

	public void setPoint(Point p1){
		p.setLocation(p1);
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



}
