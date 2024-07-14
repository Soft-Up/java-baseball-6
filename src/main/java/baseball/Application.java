package baseball;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

public class Application {

    static int strike;
    static int ball;
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        boolean condition = true;
        while (condition) {
            System.out.println("숫자 야구 게임을 시작합니다.");
            ArrayList<Integer> computer = setComputer();

            boolean findEx = true;
            do {
                try {
                    
                    System.out.printf("숫자를 입력해주세요 : ");
                    String userNum = Console.readLine();
                    findEx(userNum);

                    ArrayList<Integer> user = StoI(userNum);
                    getResult(computer, user);

                    if (strike == 3){
                        System.out.println("3스트라이크");
                        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");

                        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
                        String choice = Console.readLine();
                        if (choice.equals("2")){
                            condition = false;
                            break;
                        }
                        break;
                    }
                    else if (strike == 0 && ball ==0 )
                        System.out.println("낫싱");
                    else if (strike == 0)
                        System.out.println(ball + "볼");
                    else if (ball == 0)
                        System.out.println(strike + "스트라이크");
                    else
                        System.out.println(ball + "볼 " + strike + "스트라이크");

                    
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    condition = false;
                    break;
                }
            } while (findEx);

            
            
        }
    }

    public static ArrayList<Integer> setComputer(){
        ArrayList<Integer> computer = new ArrayList<>();
        while (computer.size() < 3 ) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
        return computer;
    }

    public static void findEx(String userNum){
        if (userNum.length() != 3)
        throw new IllegalArgumentException("세 자리가 아님");

        Set<Character> uniqueDigits = new HashSet<>();
        for (char c : userNum.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("숫자가 아닌 타입이 포함됨");
            }
        uniqueDigits.add(c);
        }

        if (uniqueDigits.size() != 3){
            throw new IllegalArgumentException("중복된 숫자가 포함됨");
        }
    }

    public static ArrayList<Integer> StoI(String userNum){
        ArrayList<Integer> user = new ArrayList<>();
        for (char c : userNum.toCharArray()){
            user.add(Character.getNumericValue(c));
        }
        return user;
    }

    public static void getResult(ArrayList<Integer> computer, ArrayList<Integer> user){
        strike = 0; ball = 0;

        for (int i = 0; i < 3; i++){
            if (user.get(i).equals(computer.get(i)))
                strike++;
            else if (computer.contains(user.get(i)))
                ball++;
        }
    }
    
}

