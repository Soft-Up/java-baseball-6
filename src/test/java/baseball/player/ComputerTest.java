package baseball.player;

import static org.junit.jupiter.api.Assertions.*;

import baseball.util.NumberGenerator;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class ComputerTest {

    private static Computer computer;

    static class FakeNumberGenerator implements NumberGenerator {
        @Override
        public List<Integer> generateUniqueThreeDigitNumber() {
            return List.of(1, 2, 3);
        }
    }

    @BeforeAll
    static void computerTestInit() {
        computer = new Computer(new FakeNumberGenerator());
    }

    @Test
    @DisplayName("숫자 3개가 정답에 모두 포함되며, 위치까지 일치하는 경우")
    void compareWithAnswerTestOne() {
        // Given
        List<Integer> submitted = List.of(1, 2, 3);
        String expectedValue = "3스트라이크";

        // When
        computer.generateAnswer();
        String returnValue = computer.compareWithAnswer(submitted);

        // Then
        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("숫자 3개가 정답에 모두 포함되지만, 위치는 1개만 일치하는 경우")
    void compareWithAnswerTestTwo() {
        // Given
        List<Integer> submitted = List.of(1, 3, 2);
        String expectedValue = "2볼 1스트라이크";

        // When
        computer.generateAnswer();
        String returnValue = computer.compareWithAnswer(submitted);

        // Then
        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("숫자 3개가 정답에 모두 포함되지만, 위치가 모두 다른 경우")
    void compareWithAnswerTestThree() {
        // Given
        List<Integer> submitted = List.of(3, 1, 2);
        String expectedValue = "3볼";

        // When
        computer.generateAnswer();
        String returnValue = computer.compareWithAnswer(submitted);

        // Then
        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("숫자 3개 중 2개만 정답에 포함되며, 위치는 2개가 같은 경우")
    void compareWithAnswerTestFour() {
        // Given
        List<Integer> submitted = List.of(1, 2, 4);
        String expectedValue = "2스트라이크";

        // When
        computer.generateAnswer();
        String returnValue = computer.compareWithAnswer(submitted);

        // Then
        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("숫자 3개 중 2개만 정답에 포함되며, 위치는 2개 모두 다른 경우")
    void compareWithAnswerTestFive() {
        // Given
        List<Integer> submitted = List.of(2, 1, 4);
        String expectedValue = "2볼";

        // When
        computer.generateAnswer();
        String returnValue = computer.compareWithAnswer(submitted);

        // Then
        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("숫자 3개가 모두 포함되지 않는 경우")
    void compareWithAnswerTestSix() {
        // Given
        List<Integer> submitted = List.of(4, 5, 6);
        String expectedValue = "낫싱";

        // When
        computer.generateAnswer();
        String returnValue = computer.compareWithAnswer(submitted);

        // Then
        assertEquals(expectedValue, returnValue);
    }
}