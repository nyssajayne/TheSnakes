package server.model;

public class Player {
		private String name;
		private int score;
		private Thread thread;
		private SnakeRunnable snake;
		
	public Player(Thread thread, SnakeRunnable snake){
		this.thread = thread;
		this.snake = snake;
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
	public Thread getThread() {
		return thread;
	}
	public void setThread(Thread thread) {
		this.thread = thread;
	}
	public SnakeRunnable getSnake()
	{
		return snake;
	}

}
