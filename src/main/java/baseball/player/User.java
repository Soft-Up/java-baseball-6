package baseball.player;

import baseball.util.NumberGenerator;
import java.util.List;

public class User {
    private final NumberGenerator numberGenerator;

    public User(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public List<Integer> enterNumber(){
        return numberGenerator.enterUniqueThreeDigitNumber();
    }
}
