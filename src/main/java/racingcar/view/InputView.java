package racingcar.view;

import camp.nextstep.edu.missionutils.Console; // Console API 사용
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

    private static final String INPUT_CAR_NAMES_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String INPUT_TRY_COUNT_MESSAGE = "시도할 횟수는 몇 회인가요?";
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String INVALID_CAR_NAME_LENGTH_MESSAGE = "자동차 이름은 5자 이하만 가능합니다.";
    private static final String INVALID_TRY_COUNT_MESSAGE = "시도 횟수는 숫자여야 합니다.";
    private static final int MAX_CAR_NAME_LENGTH = 5;
    private static final String DELIMITER = ",";

    public List<String> readCarNames() {
        System.out.println(INPUT_CAR_NAMES_MESSAGE);
        String input = Console.readLine();

        List<String> carNames = Arrays.asList(input.split(DELIMITER));
        validateCarNames(carNames);

        return carNames.stream()
                .map(String::trim) // 이름 좌우 공백 제거
                .collect(Collectors.toList());
    }

    private void validateCarNames(List<String> carNames) {
        for (String name : carNames) {
            if (name.trim().isEmpty() || name.trim().length() > MAX_CAR_NAME_LENGTH) {
                throw new IllegalArgumentException(ERROR_PREFIX + INVALID_CAR_NAME_LENGTH_MESSAGE);
            }
        }
    }

    public int readTryCount() {
        System.out.println(INPUT_TRY_COUNT_MESSAGE);
        String input = Console.readLine();

        validateTryCount(input);
        return Integer.parseInt(input);
    }

    private void validateTryCount(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_PREFIX + INVALID_TRY_COUNT_MESSAGE);
        }
    }
}