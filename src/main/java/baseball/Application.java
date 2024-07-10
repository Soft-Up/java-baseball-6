package baseball;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Application {
    public static void main(String[] args) {
        boolean playAgain = true;

        while (playAgain) {
            List<Integer> computerNumbers = generateRandomNumbers();
            boolean gameOver = false;

            System.out.println("숫자 야구 게임을 시작합니다.");

            while (!gameOver) {
                System.out.print("숫자를 입력해주세요 : ");
                String userInput = Console.readLine();

                validateInput(userInput);
                List<Integer> userNumbers = convertToIntList(userInput);
                Boolean is3Strike = getResult(computerNumbers, userNumbers);
                if (is3Strike) {
                    gameOver = true;
                    System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                }
            }

            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            String choice = Console.readLine();
            if (choice.equals("2")) {
                playAgain = false;
            }
        }
    }

    private static List<Integer> generateRandomNumbers() {
        List<Integer> computer = new ArrayList<>();
        while (computer.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
        return computer;
    }

    private static void validateInput(String input) {
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

    private static List<Integer> convertToIntList(String input) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            numbers.add(input.charAt(i) - '0');
        }
        return numbers;
    }

    private static Boolean getResult(List<Integer> computerNumbers, List<Integer> userNumbers) {
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
