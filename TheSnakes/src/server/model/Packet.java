package server.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Packet implements Serializable {
	ArrayList<Player> snakes;
	int gameStatus;
	/**
	 * This is what will be sent.
	 */
	private static final long serialVersionUID = -5967953452268446936L;
	public Packet(ArrayList<Player> snakes, int gameStatus){
		this.snakes = snakes;
		this.gameStatus = gameStatus;
	}
	
	public ArrayList<Player> getSnakes() {
		return snakes;
	}
	public void setSnakes(ArrayList<Player> snakes) {
		this.snakes = snakes;
	}
	public int getGameStatus() {
		return gameStatus;
	}
	public void setGameStatus(int gameStatus) {
		this.gameStatus = gameStatus;
	}
	public String toString() {
		return "Snakes: " + snakes + "gamestatus: " + gameStatus;
	}

}
