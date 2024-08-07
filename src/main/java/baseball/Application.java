package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Application {
    public static void main(String[] args) {
        int option = 0;
        System.out.println("숫자 야구 게임을 시작합니다.");
        do {
            // 랜덤 숫자 생성
            List<Integer> computer = new ArrayList<>();

            while (computer.size() < 3) {
                int randomNumber = Randoms.pickNumberInRange(1, 9);

                if (!computer.contains(randomNumber)) {
                    computer.add(randomNumber);
                }
            }

            // System.out.println(computer);

            // 숫자를 맞출 때까지 반복
            while (true) {
                int strike = 0;
                int ball = 0;

                // 숫자 입력
                System.out.print("숫자를 입력해주세요 : ");
                String num = Console.readLine();

                if (num.length() != 3)
                    throw new IllegalArgumentException("3자리의 숫자를 입력하여야 합니다.");

                // 배열에 숫자 배열에 저장
                List<Integer> numList = new ArrayList<>(3);

                for (int i =0; i < 3; i ++) {
                    char ch = num.charAt(i);

                    if (Character.isDigit(ch)) {
                        numList.add(Character.getNumericValue(ch));
                    } else {
                        throw new IllegalArgumentException("숫자만 입력해야 합니다.");
                    }
                }

                // System.out.println("num: " + numList);

                for (int i = 0; i < 3; i ++) {
                    int checkingNum = numList.get(i);
                    if (Objects.equals(checkingNum, computer.get(i))) {
                        strike ++;
                    }
                    else if (computer.contains(checkingNum)) {
                        ball ++;
                    }
                }

                if (strike == 3) {
                    System.out.println(strike + "스트라이크");
                    System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                    break;
                }
                else if (strike == 0 && ball == 0) {
                    System.out.println("낫싱");
                }
                else if (strike == 0) {
                    System.out.println(ball + "볼");
                }
                else if (ball == 0) {
                    System.out.println(strike + "스트라이크");
                }
                else {
                    System.out.println(ball + "볼 " + strike + "스트라이크");
                }
            }

            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            option = Integer.parseInt(Console.readLine());

            if (option != 1 && option != 2)
                throw new IllegalArgumentException("option은 1 또는 2여야 합니다.");

        } while (option == 1);
    }
}