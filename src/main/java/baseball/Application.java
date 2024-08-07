package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Application {
    public static final int NUM_LENGTH = 3;

    public static void main(String[] args) {
        int option;
        System.out.println("숫자 야구 게임을 시작합니다.");

        do {
            List<Integer> computerNumbers = generateComputerNumbers();
            playGame(computerNumbers);
            option = getOption();
        } while (option == 1);
    }

    private static List<Integer> generateComputerNumbers () {
        List<Integer> computerNumbers = new ArrayList<>();

        while (computerNumbers.size() < NUM_LENGTH) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);

            if (!computerNumbers.contains(randomNumber)) {
                computerNumbers.add(randomNumber);
            }
        }

        return computerNumbers;
    }

    private static void playGame(List<Integer> computerNumbers) {
        while (true) {
            List<Integer> userNumbers = inputNumber();

            int strike = countStrike(computerNumbers, userNumbers);
            int ball = countBall(computerNumbers, userNumbers);

            System.out.println(getResult(strike, ball));

            if (isSuccess(strike)) {
                break;
            }

        }

    }

    private static List<Integer> inputNumber () {
        System.out.print("숫자를 입력해주세요 : ");
        String num = Console.readLine();

        if (num.length() != NUM_LENGTH) {
            throw new IllegalArgumentException(NUM_LENGTH + "자리의 숫자를 입력하여야 합니다.");
        }

        List<Integer> userNumbers = new ArrayList<>();

        for (int i =0; i < NUM_LENGTH; i ++) {
            char ch = num.charAt(i);

            if (Character.isDigit(ch)) {
                userNumbers.add(Character.getNumericValue(ch));
            } else {
                throw new IllegalArgumentException("숫자만 입력해야 합니다.");
            }
        }
        return userNumbers;
    }

    private static int countStrike (List<Integer> computerNumbers, List<Integer> userNumbers) {
        int strike = 0;

        for (int i = 0; i < NUM_LENGTH; i ++) {
            if (computerNumbers.get(i).equals(userNumbers.get(i))) {
                strike++;
            }
        }

        return strike;
    }

    private static int countBall (List<Integer> computerNumbers, List<Integer> userNumbers) {
        int ball = 0;

        for (int i = 0; i < NUM_LENGTH; i ++) {
            int userNum = userNumbers.get(i);
            if (computerNumbers.contains(userNum) && !computerNumbers.get(i).equals(userNum)) {
                ball++;
            }
        }

        return ball;
    }

    private static String getResult(int strike, int ball) {

        if (strike == 0 && ball == 0) {
            return "낫싱";
        }
        else if (strike == 0) {
            return ball + "볼";
        }
        else if (ball == 0) {
            return strike + "스트라이크";
        }
        else {
            return ball + "볼 " + strike + "스트라이크";
        }
    }

    private static boolean isSuccess (int strike) {

        if (strike == NUM_LENGTH) {
            System.out.println(NUM_LENGTH + "개의 숫자를 모두 맞히셨습니다! 게임 종료");
            return true;
        }

        return false;
    }

    private static int getOption() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");

        int option = Integer.parseInt(Console.readLine());

        if (option != 1 && option != 2) {
            throw new IllegalArgumentException("option은 1 또는 2여야 합니다.");
        }

        return option;
    }

}