package server.model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import shared.Player;
import shared.Snake;
import shared.SnakeInterface;
import shared.Tile;

public class GameLogic implements SnakeInterface {
	
	private List<Player> players;
	private Point bounds;
	
	private static List<Color> playerColors = new ArrayList<Color>();
	
	private Map<String,Integer> statusMap;
	
	public GameLogic(Point bounds)
	{
		this.bounds = bounds;
		playerColors.add(Color.RED);
		playerColors.add(Color.BLUE);
		playerColors.add(Color.GREEN);
		playerColors.add(Color.DARK_GRAY);
	}
	
	public void setPlayers(List<Player> players) {
		this.players = players;
		// give all players a color, position and direction
		int dx = 0 , dy = 0;
		int x = 0, y = 0;
		int colorIndex = 0;
		for(Player p : players) {
			String name = p.getName();
			switch(statusMap.get(name)){
				case TOP_LEFT:
					dx = 1;
					x = 1;
					y = 1;
					break;
				case TOP_RIGHT:
					dx = -1;
					x = bounds.x - 1;
					y = 1;
					break;
				case BOT_LEFT:
					x = 1;
					y = bounds.y - 1;
					dx = 1;
					break;
				case BOT_RIGHT:
					x = bounds.x - 1;
					y = bounds.y - 1;
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
		
		for(Player p: players) {
			Point headPos = p.getSnake().getHeadPos();
			for(Player k: players) {
				if(p != k){
					for(Tile t: p.getSnake().getSegments()) {
						if(t.getPoint().equals(headPos)){
							statusMap.put(p.getName(),STATUS_LOSE);
						}
					}	
				}
			}
		}
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
	/*
	 * Sets the status of the players in the game
	 * called by server class every tick
	 */
	public void setStatus(int status, String playerName) {
		statusMap.put(playerName,status);
	}
	
	public List<Player> getPlayers() {
		return null;
	}
}
