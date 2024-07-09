package baseball.util;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class NumberGeneratorCamp implements NumberGenerator {

    @Override
    public List<Integer> generateUniqueThreeDigitNumber() {
        List<Integer> uniqueThreeDigitNumber = new ArrayList<>();
        while (uniqueThreeDigitNumber.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!uniqueThreeDigitNumber.contains(randomNumber)) {
                uniqueThreeDigitNumber.add(randomNumber);
            }
        }
        return uniqueThreeDigitNumber;
    }

    @Override
    public List<Integer> enterUniqueThreeDigitNumber() {
        System.out.print("숫자를 입력해주세요 : ");
        String input = Console.readLine();
        NumberValidator.isValidNumberInput(input);
        return integerStringToIntegerList(input);
    }

    private List<Integer> integerStringToIntegerList(String input) {
        return input.chars()
                .mapToObj(Character::getNumericValue)
                .toList();
    }
}
