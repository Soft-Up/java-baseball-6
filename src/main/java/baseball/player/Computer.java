package baseball.player;

import baseball.util.NumberGenerator;
import java.util.List;

public class Computer {
    private final NumberGenerator numberGenerator;
    private List<Integer> answerNumber;

    public Computer(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public void generateAnswer() {
        this.answerNumber = numberGenerator.generateUniqueThreeDigitNumber();
    }

    public String compareWithAnswer(List<Integer> submittedNumber) {
        String result = "";
        long containCount = submittedNumber.stream()
                .filter(answerNumber::contains)
                .count();

        long strikeCount = submittedNumber.stream()
                .filter(answerNumber::contains)
                .filter(number -> submittedNumber.indexOf(number) == answerNumber.indexOf(number))
                .count();

        long ballCount = containCount - strikeCount;

        if (ballCount > 0) {
            result += "%d볼 ".formatted(ballCount);
        }

        if (strikeCount > 0) {
            result += "%d스트라이크".formatted(strikeCount);
        }

        if (strikeCount == 0 && ballCount == 0) {
            result += "낫싱";
        }

        return result.trim();
    }
}