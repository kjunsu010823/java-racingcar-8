package racingcar.controller;

import racingcar.domain.RacingGame;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class GameController {

    private final InputView inputView;
    private final OutputView outputView;

    public GameController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        List<String> carNames = inputView.readCarNames();
        int tryCount = inputView.readTryCount();

        RacingGame racingGame = new RacingGame(carNames);

        outputView.printExecutionResultMessage();

        for (int i = 0; i < tryCount; i++) {
            racingGame.playRound();
            outputView.printRoundResult(racingGame.getCars());
        }

        List<String> winners = racingGame.getWinners();
        outputView.printWinners(winners);
    }
}