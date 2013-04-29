package server.model;

import java.awt.Rectangle;
import java.util.List;

public class GameLogic {
	
	List<Player> players;
	private Rectangle bounds;
	
	
	
	public GameLogic()
	{
		
	}
	
	public void setPlayers(List<Player> players)
	{
		this.players = players;
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
