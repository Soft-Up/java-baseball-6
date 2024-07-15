package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game {
    private final Player player;
    private final List<Integer> computer;

    public Game(Player player) {
        this.player = player;
        this.computer = new ArrayList<>();
        while (computer.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
    }

    private void promptInput() {
        System.out.println("숫자를 입력해주세요 : ");
        player.inputGame();
    }

    // java에선 multiple return이 안 돼서 map으로 꼼수,,
    private HashMap<String, Object> evaluateInput() {
        long contains = player.getGameInput().stream().filter(computer::contains).count();
        long strikes = player.getGameInput().stream().filter((number) ->
            player.getGameInput().indexOf(number) == computer.indexOf(number)
        ).count();
        long balls = contains - strikes;

        HashMap<String, Object> map = new HashMap<>();
        map.put("strikes", strikes);
        map.put("balls", balls);
        if (strikes == 3) {
            map.put("guessResult", GuessResult.CORRECT);
        } else if (strikes > 0) {
            map.put("guessResult", GuessResult.STRIKE);
        } else if (balls > 0) {
            map.put("guessResult", GuessResult.BALL);
        } else {
            map.put("guessResult", GuessResult.NOTHING);
        }
        return map;
    }

    private GameState printResult(HashMap<String, Object> map) {
        GuessResult guessResult = (GuessResult)map.get("guessResult");
        long strikes = (long)map.get("strikes");
        long balls = (long)map.get("balls");

        return switch (guessResult) {
            case CORRECT -> {
                System.out.println("3스트라이크");
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                yield GameState.END;
            }
            case STRIKE -> {
                System.out.println(balls + "볼 " + strikes + "스트라이크");
                yield GameState.PROCEEDING;
            }
            case BALL -> {
                System.out.println(balls + "볼");
                yield GameState.PROCEEDING;
            }
            case NOTHING -> {
                System.out.println("낫싱");
                yield GameState.PROCEEDING;
            }
        };
    }

    public GameState proceed() {
        promptInput();
        System.out.println(player.getGameInput());
        System.out.println(computer);

        HashMap<String, Object> map = evaluateInput();

        return printResult(map);
    }
}
