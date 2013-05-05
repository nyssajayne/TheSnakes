package shared.controller;
/*
 * Interface for interpreting integers sent over the network that represent state
 */
public interface SnakeInterface {
	
	public static final int MOVE_NONE = 0;
	public static final int MOVE_LEFT = 1;
	public static final int MOVE_RIGHT = 2;
	public static final int MOVE_FASTER = 3;
	public static final int MOVE_SLOWER = 4;
	public static final int MOVE_EXIT = 5;
	public static final int NO_PLAYER = -1;
	public static final int MOVE_ERROR = -128;
	public static final int PIECE_SIZE = 10;
	public static final int STATUS_WAIT = 6;
	public static final int STATUS_NOT_VALID = 66;
	public static final int STATUS_PLAYING = 7;
	public static final int STATUS_WIN = 8;
	public static final int STATUS_LOSE = 9;
	
	public static final int TOP_LEFT = 40;
	public static final int TOP_RIGHT = 41;
	public static final int BOT_LEFT = 42;
	public static final int BOT_RIGHT = 43;
	
	public static final Integer POSITIONS[] = {TOP_LEFT, TOP_RIGHT, BOT_LEFT, BOT_RIGHT}; 

}
