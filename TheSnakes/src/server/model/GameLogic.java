package server.model;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shared.Player;
import shared.SnakeInterface;
import shared.Tile;

public class GameLogic implements SnakeInterface {
	
	// may be more appropriate to use a map here
	private List<Player> players;
	private Point bounds;
	
	private Map<String,Integer> statusMap;
	
	public GameLogic(Point bounds)
	{
		this.bounds = bounds;
	}
	
	public void setPlayers(List<Player> players)
	{
		this.players = players;
		// give all players a color and position
		
	}
	
	/*
	 * returns the status of the players
	 * 
	 */
	public Map<String, Integer> getStatusMap() {
		return statusMap;
	}
	/*
	 * Goes through each status and updates the players accordinly
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
			/*
			 * TODO: add more cases for slower/faster snake movement
			 * and removing them from the game
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
	
	public void setStatus(int status, String playerName) {
		statusMap.put(playerName,status);
	}
	
	public List<Player> getPlayers() {
		return null;
		
	}
}
