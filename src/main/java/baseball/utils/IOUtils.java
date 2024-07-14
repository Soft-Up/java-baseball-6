package baseball.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IOUtils {
    public static void validateInput(String input) {
        if (input.length() != 3) {
            throw new IllegalArgumentException();
        }
        Set<Character> charSet = new HashSet<>();
        for (char ch : input.toCharArray()) {
            if (!Character.isDigit(ch) || ch == '0' || !charSet.add(ch)) {
                throw new IllegalArgumentException();
            }
        }
    }

    public static Boolean getResult(List<Integer> computerNumbers, List<Integer> userNumbers) {
        int strikes = 0;
        int balls = 0;
        String result = "";

        for (int i = 0; i < 3; i++) {
            if (userNumbers.get(i).equals(computerNumbers.get(i))) {
                strikes++;
            } else if (computerNumbers.contains(userNumbers.get(i))) {
                balls++;
            }
        }

        if (strikes == 3) {
            result = "3스트라이크";
            System.out.println(result);
            return true;
        }

        if (strikes == 0 && balls == 0) {
            result = "낫싱";
        } else {
            result = balls + "볼 " + strikes + "스트라이크";
        }
        System.out.println(result);
        return false;
    }
}
