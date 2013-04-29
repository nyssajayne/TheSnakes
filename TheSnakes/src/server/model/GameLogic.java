package server.model;

import java.awt.Point;
import java.util.List;

import shared.Player;

public class GameLogic {
	
	// may be more appropriate to use a map here
	List<Player> players;
	private Point bounds;
	
	public GameLogic(Point bounds)
	{
		
	}
	
	public void setPlayers(List<Player> players)
	{
		this.players = players;
		// give all players a color and position
		
		
		
	}
	// Steps the game forward one "tick" 
	public void step() {
		
		moveSnakes();
		
		
	}
	
	private void moveSnakes() {
		for(Player p : players) {
			p.getSnake().move();
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
