package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms; // Randoms API 사용

public class Car {
    private static final int MOVE_THRESHOLD = 4; // 전진 조건
    private static final int RANDOM_MIN_VALUE = 0;
    private static final int RANDOM_MAX_VALUE = 9;

    private final String name;
    private int position;

    public Car(String name) {
        // 이름 유효성 검사는 InputView에서 이미 처리했습니다.
        this.name = name;
        this.position = 0;
    }

    // 자동차가 스스로 움직일지 결정합니다.
    public void move() {
        int randomNumber = Randoms.pickNumberInRange(RANDOM_MIN_VALUE, RANDOM_MAX_VALUE);
        if (randomNumber >= MOVE_THRESHOLD) {
            this.position++;
        }
    }

    // View가 사용할 getter
    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}