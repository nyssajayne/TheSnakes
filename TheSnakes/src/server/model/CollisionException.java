package server.model;

import shared.Player;

public class CollisionException extends Exception {

	private Player player;
	
	public CollisionException(Player player) {
		this.player = player;
	}
	public Player getPlayer() {
		return player;
	}
}
