package baseball;
import camp.nextstep.edu.missionutils.Console;

import java.util.List;

import static baseball.utils.DataUtils.convertToIntList;
import static baseball.utils.DataUtils.generateRandomNumbers;
import static baseball.utils.IOUtils.getResult;
import static baseball.utils.IOUtils.validateInput;

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
}
