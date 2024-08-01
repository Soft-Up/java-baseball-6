package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Game {
	private final Player player;
	private final List<Integer> computer;

	public Game(Player player) {
		this.player = player;
		this.computer = generateRandomNumbers();
	}

	private List<Integer> generateRandomNumbers() {
		Set<Integer> randomNumbers = new LinkedHashSet<>();
		while (randomNumbers.size() < 3) {
			randomNumbers.add(Randoms.pickNumberInRange(1, 9));
		}
		return new ArrayList<>(randomNumbers);
	}

	private void promptInput() {
		System.out.println("숫자를 입력해주세요 : ");
		player.inputGame();
	}

	private GuessResult calculateInput() {
		long contains = player.getGameInput().stream().filter(computer::contains).count();
		long strikes = player.getGameInput().stream().filter((number) ->
			player.getGameInput().indexOf(number) == computer.indexOf(number)
		).count();
		long balls = contains - strikes;

		if (strikes == 3) {
			return new GuessResult(strikes, balls, Guess.CORRECT);
		} else if (strikes > 0) {
			return new GuessResult(strikes, balls, Guess.STRIKE);
		} else if (balls > 0) {
			return new GuessResult(strikes, balls, Guess.BALL);
		} else {
			return new GuessResult(strikes, balls, Guess.NOTHING);
		}
	}

	private void printResult(GuessResult guessResult) {
		long strikes = guessResult.strikes();
		long balls = guessResult.balls();
		Guess guess = guessResult.guess();

		switch (guess) {
			case CORRECT -> {
				System.out.println("3스트라이크");
				System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
			}
			case STRIKE -> System.out.println(balls + "볼 " + strikes + "스트라이크");
			case BALL -> System.out.println(balls + "볼");
			case NOTHING -> System.out.println("낫싱");
		}
	}

	private GameState evaluateResult(Guess guess) {
		return switch (guess) {
			case CORRECT -> GameState.END;
			case STRIKE, BALL, NOTHING -> GameState.PROCEEDING;
		};
	}

	public GameState proceed() {
		promptInput();
		System.out.println(player.getGameInput());
		// System.out.println(computer);

		GuessResult guessResult = calculateInput();
		printResult(guessResult);

		return evaluateResult(guessResult.guess());
	}
}
