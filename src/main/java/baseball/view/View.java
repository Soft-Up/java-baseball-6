package baseball.view;

import baseball.domain.Answer;
import camp.nextstep.edu.missionutils.Console;

public class View {

    public static void init(){
        System.out.println("숫자 야구 게임을 시작합니다.");
    }

    public static String input(){
        System.out.println("숫자를 입력해주세요 : ");
        return Console.readLine();
    }

    public static void showAnswer(String statement){
        System.out.println(statement);
    }

    public static String showEnd(){
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        return Console.readLine();
    }
}
