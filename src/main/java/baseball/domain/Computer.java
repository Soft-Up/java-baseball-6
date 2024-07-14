package baseball.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Computer {
    private List<Integer> answerNumbers;

    public Computer(){
        List<Integer> computer = new ArrayList<>();
        while (computer.size() < BaseballNumber.LENGTH) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
        this.answerNumbers = computer;
    }

    public Answer calculate(BaseballNumber baseballNumber){
        List<Integer> playerNumbers = baseballNumber.getNumbers();
        return new Answer(answerNumbers, playerNumbers);
    }
}
