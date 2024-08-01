package baseball;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;

public class Player {
	private String gameManagerInput;
	private List<Integer> gameInput;

	public Player() {
		this.gameManagerInput = "first";
	}

	public void setGameManagerInput(String input) {
		gameManagerInput = input;
	}

	public void inputGameManager() {
		String input = Console.readLine();

		Validator validator = new Validator();
		validator.isValidGameManagerInput(input);

		gameManagerInput = input;
	}

	public void inputGame() {
		String input = Console.readLine();

		Validator validator = new Validator();
		validator.isValidGameInput(input);

		gameInput = Arrays.stream(input.split("")).map(Integer::parseInt).toList();
	}

	public String getGameManagerInput() {
		return gameManagerInput;
	}

	public List<Integer> getGameInput() {
		return gameInput;
	}
}
