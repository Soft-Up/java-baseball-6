package baseball;

public class Application {
    public static void main(String[] args) {
        Player player = new Player();
        GameManager gameManager = GameManager.getGameManager(player);

        while (gameManager.willProceed()) {
            gameManager.promptInput();
            switch (player.getGameManagerInput()) {
                case "1" -> gameManager.startGame();
                case "2" -> gameManager.stopGame();
            }
        }
    }
}
