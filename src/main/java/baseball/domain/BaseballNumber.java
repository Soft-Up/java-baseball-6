package baseball.domain;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BaseballNumber {
    public static final Integer LENGTH = 3;
    private final String REGEXP_PATTERN_INTEGER = String.format("[1-9]{%d}", LENGTH);
    private List<Integer> numbers;

    public BaseballNumber(String input) {
        validate(input);
        this.numbers = Stream.of(input.split("")).mapToInt(Integer::parseInt).boxed().toList();
    }

    private void validate(String input) {
        isCorrectLength(input);
        isInteger(input);
        isDuplicate(input);
    }

    private void isCorrectLength(String input) {
        if (input.length() != LENGTH) {
            throw new IllegalArgumentException("입력의 길이가 알맞지 않습니다.");
        }
    }

    private void isInteger(String input) {
        if (!Pattern.matches(REGEXP_PATTERN_INTEGER, input)) {
            throw new IllegalArgumentException("입력이 정수로 이루어지지 않았습니다.");
        }
    }

    private void isDuplicate(String input) {
        if (Stream.of(input.split("")).collect(Collectors.toSet()).size() < LENGTH) {
            throw new IllegalArgumentException("입력 중 중복이 있습니다.");
        }
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(this.numbers);
    }
}