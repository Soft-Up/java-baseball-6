package baseball;

import baseball.controller.GameController;
import baseball.util.NumberGeneratorCamp;

public class Application {
    public static void main(String[] args) {
        GameController gameController = GameController.getInstance(new NumberGeneratorCamp());
        gameController.startGame();
    }
}
