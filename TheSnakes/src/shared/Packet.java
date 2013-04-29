package shared;

import java.io.Serializable;
import java.util.ArrayList;


public class Packet implements Serializable {
	ArrayList<Player> players;
	int gameStatus;
	/**
	 * This is what will be sent.
	 */
	private static final long serialVersionUID = -5967953452268446936L;
	public Packet(ArrayList<Player> players, int gameStatus){
		this.players = players;
		this.gameStatus = gameStatus;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	public void setSnakes(ArrayList<Player> snakes) {
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

}
