package server.model;

import shared.Player;
/*
 * I'm not sure if i need this yet. 
 * TODO : find out if i need this yet
 */
public class CollisionException extends Exception {

	private Player player;
	
	public CollisionException(Player player) {
		this.player = player;
	}
	public Player getPlayer() {
		return player;
	}
}
