package baseball;

public class GameManager {
    private static GameManager gameManager;

    private final Player player;
    private boolean willProceed;

    private GameManager(Player player) {
        this.player = player;
        this.willProceed = true;
        System.out.println("숫자 야구 게임을 시작합니다.");
    }

    public static GameManager getGameManager(Player player) {
        if (gameManager == null) {
            gameManager = new GameManager(player);
        }
        return gameManager;
    }

    public boolean willProceed() {
        return willProceed;
    }

    public void promptInput() {
        if (player.getGameManagerInput().equals("first")) {
            player.setGameManagerInput("1");
            return;
        }

        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        player.inputGameManager();
    }

    public void startGame() {
        Game game = new Game(player);

        GameState gameState = GameState.PROCEEDING;
        while (gameState == GameState.PROCEEDING) {
            gameState = game.proceed();
        }
    }

    public void stopGame() {
        willProceed = false;
    }
}
