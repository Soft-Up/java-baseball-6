package baseball.domain;

import java.util.regex.Pattern;

public class EndNumber {
    public static final Integer LENGTH = 1;
    private final String REGEXP_PATTERN_FORMAT = String.format("[1-2]{%d}",LENGTH);
    private Integer number;

    public EndNumber(String input){
        validate(input);
        this.number = Integer.parseInt(input);
    }

    private void validate(String input){
        isCorrectLength(input);
        isCorrectNumber(input);
    }

    private void isCorrectLength(String input){
        if (input.length() != LENGTH) {
            throw new IllegalArgumentException("입력의 길이가 알맞지 않습니다.");
        }
    }

    private void isCorrectNumber(String input){
        if (!Pattern.matches(REGEXP_PATTERN_FORMAT, input)) {
            throw new IllegalArgumentException("입력값이 형식을 만족하지 않습니다.");
        }
    }

    public boolean isEnd(){
        return this.number == 2;
    }
}
