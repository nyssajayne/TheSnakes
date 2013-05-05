package shared;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

public class Food implements Serializable {
	
	private static final long serialVersionUID = 3698965268892031485L;
	
	public static final int TYPE_ORANGE = 1;
	public static final int TYPE_APPLE = 2;
	public static final int TYPE_BANANA = 3;
	
	public static final int MAX_FOOD = 4;
	
	private static final Color APPLE_COLOR = Color.PINK;
	private static final Color ORANGE_COLOR = Color.ORANGE;
	private static final Color BANANA_COLOR = Color.YELLOW;

	private Tile tile;
	private int type;
	private int grow_level;
	
	public Food(int x, int y, int type) {
		this(new Point(x,y),type);
	}
	public Food(Point p, int type) {
		this.type = type;
		Color color;
		switch(type) {
			case TYPE_ORANGE:
				color = ORANGE_COLOR;
				grow_level = 1;
				break;
			case TYPE_APPLE:
				color = APPLE_COLOR;
				grow_level = 2;
				break;
			case TYPE_BANANA:
				color = BANANA_COLOR;
				grow_level = 3;
				break;
			default:
				color = Color.YELLOW;
				break;
		}
		
		this.tile = new Tile(p,color);
	}
	
	public String toString() { return "Pos: " + tile + " Type: " + type; }

	public int getGrowLevel() { return grow_level; }
	
	public int getType() { return type; }
	
	public Tile getTile() { return tile; }
	
	public Color getColor() { return tile.getFilled(); }
	
}
