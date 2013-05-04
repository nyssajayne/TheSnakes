package server.model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import shared.Food;
import shared.Player;
import shared.Snake;
import shared.SnakeInterface;
import shared.Tile;

public class GameLogic implements SnakeInterface {
	
	private static List<Color> playerColors = new ArrayList<Color>();
	
	private List<Player> players;
	private List<Food> foodItems;
	private Point bounds;
	
	private Map<String,Integer> statusMap;
	
	public GameLogic(Point bounds) {
		this.bounds = bounds;
		playerColors.add(Color.RED);
		playerColors.add(Color.BLUE);
		playerColors.add(Color.GREEN);
		playerColors.add(Color.DARK_GRAY);
		foodItems = new ArrayList<Food>();
	}
	/*
	 * Give all players a position, color and direction
	 */
	public void setPlayers(List<Player> players) {
		this.players = players;
		int dx = 0 , dy = 0;
		int x = 0, y = 0;
		int colorIndex = 0;
		for(Player p : players) {
			switch(p.getPosition()){
				case TOP_LEFT:
					dx = 1;
					x = 1;
					y = 1;
					break;
				case TOP_RIGHT:
					dx = -1;
					x = bounds.x - 2;
					y = 1;
					break;
				case BOT_LEFT:
					x = 1;
					y = bounds.y - 2;
					dx = 1;
					break;
				case BOT_RIGHT:
					x = bounds.x - 2;
					y = bounds.y - 2;
					dx = -1;
					break;
			}
			
			Snake snake = new Snake(x,y,playerColors.get(colorIndex),bounds);
			snake.setDirection(dx, dy);
			p.setSnake(snake);
			colorIndex++;
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
					statusMap.remove(name);
					players.remove(p);
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
				statusMap.put(p.getName(),STATUS_LOSE);
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
							
							System.out.println("Collision! " 
							+ headPos + " | " + t.getPoint());
							statusMap.put(p.getName(),STATUS_LOSE);
						}
					}	
				}
			}
		}
	}
	/*
	 * Spawns food at random locations on the board
	 * Maxmium number at any one time is Food.MAX_FOOD 
	 */
	private void spawnFood() {
		
		
		
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
