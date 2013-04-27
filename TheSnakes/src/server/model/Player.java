package server.model;

public class Player {
		private String name;
		private int score;
		private Thread snake;
	public Player(String name){
		this.name = name;
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
		return snake;
	}
	public void setSnake(Thread snake) {
		this.snake = snake;
	}
	

}
