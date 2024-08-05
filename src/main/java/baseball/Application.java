package baseball;

public class Application {
	public static void main(String[] args) {
		Player player = new Player();
		GameManager gameManager = new GameManager(player);

		gameManager.start();
	}
}
