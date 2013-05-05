package server.model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import shared.Food;
import shared.Player;
import shared.Snake;
import shared.SnakeInterface;
import shared.Tile;

public class GameLogic implements SnakeInterface {
	
	private static Color COLOR_TOP_LEFT = Color.RED;
	private static Color COLOR_TOP_RIGHT = Color.MAGENTA;
	private static Color COLOR_BOT_LEFT = Color.BLUE;
	private static Color COLOR_BOT_RIGHT = Color.GREEN;
	
	private List<Player> players;
	private List<Food> foodItems;
	private Point bounds;
	
	private Map<String,Integer> statusMap;
	
	public GameLogic(Point bounds) {
		this.bounds = bounds;
		foodItems = new ArrayList<Food>();
	}
	/*
	 * Give all players a position, color and direction
	 */
	public void setPlayers(List<Player> players) {
		this.players = players;
		int dx = 0 , dy = 0;
		int x = 0, y = 0;
		Color c = Color.BLACK;
		for(Player p : players) {
			switch(p.getPosition()){
				case TOP_LEFT:
					dx = 1;
					x = 1;
					y = 1;
					c = COLOR_TOP_LEFT;
					break;
				case TOP_RIGHT:
					dx = -1;
					x = bounds.x - 2;
					y = 1;
					c = COLOR_TOP_RIGHT;
					break;
				case BOT_LEFT:
					x = 1;
					y = bounds.y - 2;
					dx = 1;
					c = COLOR_BOT_LEFT;
					break;
				case BOT_RIGHT:
					x = bounds.x - 2;
					y = bounds.y - 2;
					dx = -1;
					c = COLOR_BOT_RIGHT;
					break;
			}
			
			Snake snake = new Snake(x,y,c,bounds);
			snake.setDirection(dx, dy);
			p.setSnake(snake);
		}
	}
	/*
	 * returns the status of the players
	 * TODO: remove 
	 */
	public Map<String, Integer> getStatusMap() {
		return statusMap;
	}
	/*
	 * Goes through each status and update the players accordinly
	 */
	private void interpretStatus() {
		for(Player p: players) {
			String name = p.getName();
			switch(statusMap.get(name)){
				case MOVE_UP :
					setSnakeDirection(name,0,-1);
					break;
				case MOVE_DOWN:
					setSnakeDirection(name,0,1);
					break;
				case MOVE_RIGHT:
					setSnakeDirection(name,1,0);
					break;
				case MOVE_LEFT:
					setSnakeDirection(name,-1,0);
					break;
				case MOVE_NONE:
					break;
				case MOVE_FASTER:
					break;
				case MOVE_SLOWER:
					break;
				case STATUS_LOSE:
					/*
					 * This is done AFTER collision has set the status map,
					 * so that the server can tell any clients that they have 
					 * lost before they are removed permanently 
					 */
					break;
				
			/*
			 * TODO: add cases for removing from game
			 */
			}
			
		}
	}
	/*
	 * Steps the game forward one "tick"
	 */
	public void step() {
		interpretStatus();
		moveSnakes();
		checkCollisions();
		checkFood();
		spawnFood();
	}
	/*
	 * Moves all the snakes
	 * Checks for collisions of the snakes with themselves
	 */
	private void moveSnakes() {
		for(Player p : players) {
			if(!p.getSnake().move()) {
				System.out.println("Collision! " + p);
				//statusMap.put(p.getName(),STATUS_LOSE);
			}
			
		}
	}
	/*
	 * This checks for collisions with other players
	 */
	private void checkCollisions() {
		for(Player p: players) {
			Point headPos = p.getSnake().getHeadPos();
			for(Player k: players) {
				if(p != k){
					for(Tile t: k.getSnake().getSegments()) {
						if(t.getPoint().equals(headPos)){
							//statusMap.put(p.getName(),STATUS_LOSE);
							if(k.getSnake().getHeadPos().equals(headPos)){
								
							}
						}
					}	
				}
			}
		}
	}
	
	private void checkFood() {
		for(Player p: players) {
			Point headPos = p.getSnake().getHeadPos();
			Iterator<Food> iter = foodItems.iterator();
			while(iter.hasNext()){
				Food item = iter.next();
				if(item.getTile().getPoint().equals(headPos)) {
					System.out.println("Food is ate!");
					p.getSnake().growSnake(item.getGrowLevel());
					iter.remove();
				}
				
			}
		}
	}
	
	/*
	 * Spawns food at random locations on the board
	 * Maxmium number at any one time is Food.MAX_FOOD 
	 */
	private void spawnFood() {
		for(int i = foodItems.size(); i < Food.MAX_FOOD ; i++){
			int x, y;
			Random r = new Random();
			do {
				x = r.nextInt(bounds.x);
				y = r.nextInt(bounds.y);
			}while(isTileOccupied(x,y));
			
			int type = 0;
			switch(r.nextInt(3)) {
				case 0:
					type = Food.TYPE_APPLE;
					break;
				case 1:
					type = Food.TYPE_ORANGE;
					break;
				case 2:
					type = Food.TYPE_BANANA;
					break;
			}
			Food food = new Food(x, y,type);
			
			foodItems.add(food);
		}
	}
	/*
	 * Checks a position to see if it is occupied
	 * only used for spawning food, not collisions
	 */
	private boolean isTileOccupied(int x, int y) {
		for(Player p : players){
			for(Tile t : p.getSnake().getSegments()) {
				if(t.equals(new Point(x,y))){
					return true;
				}
			}
		}
		return false;
	}
	/*
	 * Sets a snakes direction
	 */
	private void setSnakeDirection(String name, int dx, int dy) {
		for(Player p: players) {
			if(p.getName().equals(name)){
				p.getSnake().setDirection(dx, dy);
			}
		}
	}
	
	public void setStatusMap(Map<String,Integer> statusMap) {
		this.statusMap = statusMap;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public List<Food> getFood() {
		return foodItems;
	}
}
