package baseball.game;

import baseball.domain.Answer;
import baseball.domain.BaseballNumber;
import baseball.domain.Computer;
import baseball.domain.EndNumber;
import baseball.view.View;

public class Game {
    private Computer computer;

    public Game() {
        this.computer = new Computer();
    }

    public void start() {
        View.init();
        Answer answer;
        do {
            BaseballNumber baseballNumber = new BaseballNumber(View.input());
            answer = computer.calculate(baseballNumber);
            View.showAnswer(answer.getString());
        } while (answer.isStatusWRONG());
    }

    public boolean end() {
        EndNumber endNumber = new EndNumber(View.showEnd());
        return endNumber.isEnd();
    }
}
