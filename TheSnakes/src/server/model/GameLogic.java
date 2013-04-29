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
	List<Player> players;
	private Point bounds;
	
	private Map<String,Integer> statusMap;
	
	public GameLogic(Point bounds)
	{
		
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
	public Map getStatusMap() {
		return statusMap;
	}
	
	/*
	 * Steps the game forward one "tick"
	 */
	public void step() {
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
	
	public void setSnakeDirection(String name, int dx, int dy) {
		// would be more efficent with a map
		for(Player p: players) {
			if(p.getName().equals(name)){
				p.getSnake().setDirection(dx, dy);
			}
		}
		
	}
	
	public List<Player> getPlayers() {
		return null;
		
	}
}
