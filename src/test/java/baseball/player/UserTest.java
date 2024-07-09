package baseball.player;

import static org.junit.jupiter.api.Assertions.*;

import baseball.util.NumberGenerator;
import baseball.util.NumberValidator;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserTest {
    private static User user;

    private static class FakeNumberGenerator implements NumberGenerator {
        private String input;

        @Override
        public List<Integer> generateUniqueThreeDigitNumber() {
            return null;
        }

        @Override
        public List<Integer> enterUniqueThreeDigitNumber() {
            NumberValidator.isValidNumberInput(input);
            return integerStringToIntegerList(input);
        }

        private List<Integer> integerStringToIntegerList(String input) {
            return input.chars().mapToObj(Character::getNumericValue).toList();
        }

        public void setInput(String input) {
            this.input = input;
        }
    }

    @BeforeAll
    static void userTestInit() {
        user = new User(new FakeNumberGenerator());
    }

    @ParameterizedTest
    @DisplayName("유저가 올바른 숫자를 입력하는 경우")
    @ValueSource(strings = {"123", "456", "185", "843"})
    void enterUniqueThreeDigitNumberTestSuccess() {
        // Given
        String input = "123";
        List<Integer> expectedResult = List.of(1, 2, 3);
        FakeNumberGenerator numberGenerator = new FakeNumberGenerator();
        numberGenerator.setInput(input);

        // When
        List<Integer> actualResult = numberGenerator.enterUniqueThreeDigitNumber();

        // Then
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @DisplayName("유저가 1부터 9까지의 숫자가 아닌 값을 입력하는 경우")
    @ValueSource(strings = {"abcd", "12a", "a23", "안녕요", "asdg", "012"})
    void enterUniqueThreeDigitNumberTestOne(String input) {
        // Given
        FakeNumberGenerator numberGenerator = new FakeNumberGenerator();
        numberGenerator.setInput(input);

        // When & Then
        assertThrows(IllegalArgumentException.class,
                () -> numberGenerator.enterUniqueThreeDigitNumber(),
                "입력 값은 반드시 1부터 9까지의 숫자로 작성해야합니다.");
    }

    @ParameterizedTest
    @DisplayName("유저가 숫자를 입력하였지만, 3자리가 아닌 경우")
    @ValueSource(strings = {"1234", "12", "3", "12346"})
    void enterUniqueThreeDigitNumberTestTwo(String input) {
        // Given
        FakeNumberGenerator numberGenerator = new FakeNumberGenerator();
        numberGenerator.setInput(input);

        // When & Then
        assertThrows(IllegalArgumentException.class,
                () -> numberGenerator.enterUniqueThreeDigitNumber(),
                "입력 값은 반드시 숫자 3자리로 구성되어야 합니다.");
    }

    @ParameterizedTest
    @DisplayName("유저가 숫자를 3자리 입력하였지만, 중복되는 숫자가 존재하는 경우")
    @ValueSource(strings = {"111", "112", "131", "818"})
    void enterUniqueThreeDigitNumberTestThree(String input) {
        // Given
        FakeNumberGenerator numberGenerator = new FakeNumberGenerator();
        numberGenerator.setInput(input);

        // When & Then
        assertThrows(IllegalArgumentException.class,
                () -> numberGenerator.enterUniqueThreeDigitNumber(),
                "입력 값은 반드시 증복되지 않은 숫자 3개여야 합니다.");
    }
}