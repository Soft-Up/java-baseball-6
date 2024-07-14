package baseball.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Computer {
    private List<Integer> answerNumbers;

    public Computer(){
        this.answerNumbers = makeRandomNumbers();
    }

    private List<Integer> makeRandomNumbers(){
        List<Integer> numbers = new ArrayList<>();
        while (numbers.size() < BaseballNumber.BASEBALL_LENGTH) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }
        return numbers;
    }

    public Answer calculate(BaseballNumber baseballNumber){
        List<Integer> playerNumbers = baseballNumber.getNumbers();
        return new Answer(answerNumbers, playerNumbers);
    }
}
