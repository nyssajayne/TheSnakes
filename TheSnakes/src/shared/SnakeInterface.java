package shared;

import java.awt.Rectangle;

public interface SnakeInterface {
	public static int MOVE_NONE = 0;
	public static int MOVE_LEFT = 1;
	public static int MOVE_UP = 11;
	public static int MOVE_RIGHT = 2;
	public static int MOVE_DOWN = 22;
	public static int MOVE_FASTER = 3;
	public static int MOVE_SLOWER = 4;
	public static int MOVE_EXIT = 5;
	public static int NO_PLAYER = -1;
	public static int MOVE_ERROR = -128;
	public static int PIECE_SIZE = 10;
	public static int STATUS_WAIT = 6;
	public static int STATUS_PLAYING = 7;
	public static int STATUS_WIN = 8;
	public static int STATUS_LOSE = 9;
	
	public static Rectangle BOUNDS = new Rectangle(0,0,PIECE_SIZE,PIECE_SIZE);

}
