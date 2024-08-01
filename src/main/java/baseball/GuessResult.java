package baseball;

public record GuessResult(
	long strikes,
	long balls,
	Guess guess
) {
}