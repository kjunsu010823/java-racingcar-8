package racingcar.view;

import racingcar.domain.Car; // Car 객체를 사용
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String EXECUTION_RESULT_MESSAGE = "\n실행 결과";
    private static final String WINNER_ANNOUNCEMENT_PREFIX = "최종 우승자 : ";
    private static final String POSITION_MARK = "-";
    private static final String NAME_POSITION_SEPARATOR = " : ";
    private static final String WINNER_DELIMITER = ", ";

    public void printExecutionResultMessage() {
        System.out.println(EXECUTION_RESULT_MESSAGE);
    }

    // 각 라운드 결과를 출력 (예: pobi : --)
    public void printRoundResult(List<Car> cars) {
        for (Car car : cars) {
            String positionDisplay = POSITION_MARK.repeat(car.getPosition());
            System.out.println(car.getName() + NAME_POSITION_SEPARATOR + positionDisplay);
        }
        System.out.println(); // 라운드 구분을 위한 빈 줄
    }

    // 최종 우승자 출력
    public void printWinners(List<String> winners) {
        String winnerNames = String.join(WINNER_DELIMITER, winners);
        System.out.println(WINNER_ANNOUNCEMENT_PREFIX + winnerNames);
    }
}