package baseball.util;

import java.util.List;
import java.util.regex.Pattern;

public class NumberValidator {

    private static final String REGEXP_NUMBER = "[1-9]+";

    public static void isValidNumberInput(String input) {
        isIntegerType(input);
        isInteger3Digit(input);
        isUniqueIntegerCombination(input);
    }

    public static void isValidNumberChoice(String input){
        isIntegerType(input);
        isIntegerInExample(input);
    }

    private static void isIntegerType(String input) {
        if (!Pattern.matches(REGEXP_NUMBER, input)) {
            throw new IllegalArgumentException("입력 값은 반드시 1부터 9까지의 숫자로 작성해야합니다.");
        }
    }

    private static void isInteger3Digit(String input) {
        if (input.length() != 3) {
            throw new IllegalArgumentException("입력 값은 반드시 숫자 3자리로 구성되어야 합니다.");
        }
    }

    private static void isUniqueIntegerCombination(String input) {
        if (input.charAt(0) == input.charAt(1) || input.charAt(0) == input.charAt(2) || input.charAt(1) == input.charAt(2) ) {
            throw new IllegalArgumentException("입력 값은 반드시 증복되지 않은 숫자 3개여야 합니다.");
        }
    }

    private static void isIntegerInExample(String input) {
        List<String> example = List.of("1", "2");
        if (!example.contains(input)) {
            throw new IllegalArgumentException("입력 값은 1 또는 2여야 합니다.");
        }
    }

}
