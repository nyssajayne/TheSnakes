package server.model;

public class Player {
		private String name;
		private int score;
		private Thread thread;
	public Player(Thread thread){
		this.thread = thread;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Thread getSnake() {
		return thread;
	}
	public void setSnake(Thread snake) {
		this.thread = snake;
	}
	

}
