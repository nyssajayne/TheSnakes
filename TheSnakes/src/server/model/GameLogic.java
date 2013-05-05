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
	 * Goes through each status and update the players accordingly
	 */
	private void interpretStatus() {
		
		Iterator<Player> iter = players.iterator();
		while(iter.hasNext()) {
			Player p = iter.next();
			String name = p.getName();
			Snake s = p.getSnake();
			Point dir = new Point(p.getSnake().getDirection());
			switch(statusMap.get(name)){
				// change the direction of the snake relative to it's current direction
				case MOVE_RIGHT:
					s.setDirection(dir.y  * (-1), dir.x);
					statusMap.put(name, MOVE_NONE);
					break;
				case MOVE_LEFT:
					s.setDirection(dir.y, dir.x * (-1));
					statusMap.put(name, MOVE_NONE);
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
					System.out.println("Player " + p.getName() + " has died!");
					statusMap.remove(p.getName());
					iter.remove();
					break;
				case STATUS_WIN:
					System.out.println("Player " + p.getName() + " has won!");
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
				statusMap.put(p.getName(),STATUS_LOSE);
			}
		}
	}
	/*
	 * This checks for collisions with other players
	 */
	private void checkCollisions() {
		// Loop through all the players
		for(Player p: players) {
			// get the head position
			Point p_headPos = p.getSnake().getHeadPos();
			// for each other player
			if(statusMap.get(p.getName()) != STATUS_LOSE) {
				for(Player k: players) {
					if(p != k){	
						Point kDir = k.getSnake().getDirection();
						Point pDir = p.getSnake().getDirection();
						// check each segment for collisions
						Point k_headPos = k.getSnake().getHeadPos();
						for(Tile t: k.getSnake().getSegments()) {
							if(t.getPoint().equals(p_headPos)) {		
								// Collision has occurred 
								if(k_headPos.equals(p_headPos)) {
									//  if Head on collision
									System.out.println("Direct head on collision!");
									compareSize(p,k);
								} else {
									// otherwise it is a side on collision
									System.out.println("Not head on collision! Player " 
											+ p.getName() + " dies.");
									statusMap.put(p.getName(),STATUS_LOSE);
								}
							}
						}
						// check for special case of collision
						if(compareDirections(pDir, kDir)){		
							if(p_headPos.x == k_headPos.x || p_headPos.y == k_headPos.y) {
								System.out.println(p_headPos.distance(k_headPos));
								Point checkPos = new Point(p_headPos);
								checkPos.translate(pDir.x, pDir.y);
								if(checkPos.equals(k_headPos)){
									System.out.println("Special case!");
									compareSize(p,k);
								}
							}			
						}
					}
				}
			}
		}
	}
	/*
	 * Compares the sizes of two snakes and 
	 * will set the status of them accordingly
	 */
	private void compareSize(Player p, Player k){
		
		int p_snake_len = p.getSnake().getLength();
		int k_snake_len = k.getSnake().getLength();
		
		if(p_snake_len == k_snake_len) {
			// If they are the same size, one of them randomly looses
			Random r = new Random();
			switch(r.nextInt(2)) {
			case 0:
				System.out.println("P WINS LOL");
				statusMap.put(k.getName(), STATUS_LOSE);
				break;
			case 1:
				System.out.println("K WINS LOL");
				statusMap.put(p.getName(), STATUS_LOSE);
				break;
			}
		} else if(p_snake_len > k_snake_len) {
			// if p is greater than k, p wins
			statusMap.put(k.getName(), STATUS_LOSE);		
		} else {
			// Otherwise k must be larger so k wins
			statusMap.put(p.getName(), STATUS_LOSE);
		}
		
	}
	/*
	 * Compares the directions of two points (treated as velocity vectors)
	 */
	private boolean compareDirections(Point p, Point k) {
		if(p.y == (-1) * k.y){
			System.out.println("Heading in opposing y directions!");
			return true;
		}else if(p.x == (-1) * k.x){
			System.out.println("Heading in opposing x directions!");
			return true;
		}
		return false;
	}
	/*
	 * Checks for food collisions 
	 */
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
	 * Maximum number at any one time is Food.MAX_FOOD 
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
