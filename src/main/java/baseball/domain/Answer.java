package baseball.domain;

import java.util.List;
import java.util.stream.IntStream;

public class Answer {
    private AnswerStatus status = AnswerStatus.WRONG;
    private Long strike;
    private Long ball;

    public Answer(List<Integer> answerNumbers, List<Integer> playerNumbers) {
        strike = countStrike(answerNumbers, playerNumbers);
        ball = countContain(answerNumbers, playerNumbers) - strike;
        if (Long.valueOf(BaseballNumber.LENGTH).equals(strike)) {
            this.status = AnswerStatus.RIGHT;
        }
    }

    private Long countContain(List<Integer> answerNumbers, List<Integer> playerNumbers) {
        return answerNumbers.stream().filter(playerNumbers::contains).count();
    }

    private Long countStrike(List<Integer> answerNumbers, List<Integer> playerNumbers) {
        return IntStream.range(0, BaseballNumber.LENGTH)
                .filter(i -> answerNumbers.get(i).equals(playerNumbers.get(i)))
                .count();
    }

    public boolean isStatusWRONG() {
        return status.equals(AnswerStatus.WRONG);
    }

    public String getString() {
        String statement = "";
        if (ball > 0) {
            statement = statement.concat(String.format("%d볼 ", ball));
        }
        if (strike > 0) {
            statement = statement.concat(String.format("%d스트라이크", strike));
        }
        if (statement.isEmpty()) {
            statement = statement.concat("낫싱");
        }
        return statement;
    }
}
