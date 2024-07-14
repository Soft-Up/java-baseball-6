package baseball;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Validator {
    public void isValidGameManagerInput(String input) {
        List<String> list = List.of("1", "2");
        if (!list.contains(input)) {
            throw new IllegalArgumentException("입력 값은 1이거나 2여야 합니다.");
        }
    }

    public void isValidGameInput(String input) {
        isValidNumber(input);
        isValidSize(input);
        isDistinct(input);
    }

    private void isValidNumber(String input) {
        if (!Pattern.matches("[1-9]+", input)) {
            throw new IllegalArgumentException("입력 값은 1에서 9사이의 숫자여야 합니다.");
        }
    }

    private void isValidSize(String input) {
        if (input.length() != 3) {
            throw new IllegalArgumentException("입력 값은 3자리여야 합니다.");
        }
    }

    private void isDistinct(String input) {
        List<Integer> originalList = Arrays.stream(input.split("")).map(Integer::parseInt).toList();
        List<Integer> distinctList = originalList.stream().distinct().toList();
        if (!originalList.equals(distinctList)) {
            throw new IllegalArgumentException("입력 값은 증복되지 않은 숫자여야 합니다.");
        }
    }
}
