package baseball.util;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class NumberGeneratorCamp implements NumberGenerator {

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
}
