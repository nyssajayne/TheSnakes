package server.model;

import java.awt.Point;
import java.util.List;

public class GameLogic {
	
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
	
	private void checkPosition(int moves)
	{
		//Check to see if that snake can make that move.
		//Once the all clear is given
		moveSnakes(moves);
	}
	
	public void moveSnakes(int moves)
	{
		for(Player p : players)
		{
			//DataOutputStream out = p.getSnake().getOut();
			//then something like out.writeSnakeMoves();
			//or whatever we end up passing.
		}
	}
	public List<Player> getPlayers() {
		return null;
		
	}
}
