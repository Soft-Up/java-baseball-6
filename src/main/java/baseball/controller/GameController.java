package baseball.controller;

import baseball.player.Computer;
import baseball.player.User;
import baseball.util.NumberGenerator;
import baseball.util.NumberValidator;
import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.Objects;

public class GameController {

    private static Computer computer;
    private static User user;

    private static final String GAME_START_COMMENT = "숫자 야구 게임을 시작합니다.";
    private static final String SET_END_RESULT = "3스트라이크";
    private static final String SET_END_COMMENT = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";
    private static final String SET_END_CHOICE_COMMENT = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";
    private static final String RESTART_CHOICE = "1";

    private static GameController instance;

    private GameController(NumberGenerator numberGenerator) {
        computer = new Computer(numberGenerator);
        user = new User(numberGenerator);
    }

    public static GameController getInstance(NumberGenerator numberGenerator) {
        if (instance == null) {
            instance = new GameController(numberGenerator);
        }
        return instance;
    }

    public void startGame() {
        System.out.println(GAME_START_COMMENT);
        do {
            computer.generateAnswer();
            String result;

            do {
                result = startGameSet();
                System.out.println(result);
            } while (checkGameFinished(result));

            System.out.println(SET_END_COMMENT);
            System.out.println(SET_END_CHOICE_COMMENT);
        } while (askRestartGame());
    }

    private Boolean checkGameFinished(String result) {
        return !Objects.equals(result, SET_END_RESULT);
    }

    private String startGameSet() {
        List<Integer> submittedAnswer = user.enterNumber();
        return computer.compareWithAnswer(submittedAnswer);
    }

    private Boolean askRestartGame() {
        String input = Console.readLine();
        NumberValidator.isValidNumberChoice(input);
        return Objects.equals(input, RESTART_CHOICE);
    }

}
