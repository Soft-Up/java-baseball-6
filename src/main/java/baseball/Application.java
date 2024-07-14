package baseball;

import baseball.game.Game;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        boolean end = false;
        do {
            Game game = new Game();
            game.start();
            end = game.end();
        } while (!end);
    }
}