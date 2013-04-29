package shared;

import java.io.Serializable;
import java.util.List;


public class Packet implements Serializable {
	private List<Player> players;
	private int gameStatus;
	/**
	 * This is what will be sent.
	 */
	private static final long serialVersionUID = -5967953452268446936L;
	public Packet(List<Player> snakes, int gameStatus){
		this.players = snakes;
		this.gameStatus = gameStatus;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	public void setSnakes(List<Player> snakes) {
		this.players = snakes;
	}
	public int getGameStatus() {
		return gameStatus;
	}
	public void setGameStatus(int gameStatus) {
		this.gameStatus = gameStatus;
	}
	public String toString() {
		return "Snakes: " + players + "gamestatus: " + gameStatus;
	}
	public String getScores(){
		String scores = "";
		for(Player p : players){
			scores.concat( "Name: " + p.getName() + "Score: " + p.getScore() + "\n"); 
		}
		return scores;
		
	}

}
