package baseball;

import baseball.game.Game;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        boolean end = false;
        while (!end) {
            Game game = new Game();
            game.start();
            end = game.end();
        }
    }
}