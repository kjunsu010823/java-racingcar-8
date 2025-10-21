package racingcar.domain;

import java.util.List;
import java.util.stream.Collectors;

public class RacingGame {

    private final List<Car> cars;

    // 1. 생성자: 이름 목록(String)을 Car 객체 목록으로 변환합니다.
    public RacingGame(List<String> carNames) {
        this.cars = carNames.stream()
                .map(Car::new) // carName -> new Car(carName)
                .collect(Collectors.toList());
    }

    // 2. 1라운드를 진행합니다: 모든 차를 움직이게 합니다.
    public void playRound() {
        for (Car car : cars) {
            car.move();
        }
    }

    // 3. 우승자를 찾습니다.
    public List<String> getWinners() {
        // 3-1. 가장 멀리 간 위치(최대값)를 찾습니다.
        int maxPosition = findMaxPosition();

        // 3-2. 최대값과 동일한 위치를 가진 차들의 이름을 수집합니다.
        return cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    private int findMaxPosition() {
        return cars.stream()
                .mapToInt(Car::getPosition) // car -> car.getPosition()
                .max()
                .orElse(0); // 차가 없는 경우(예외상황) 0을 반환
    }

    // 4. View가 현재 차 목록을 가져갈 수 있도록 getter를 제공합니다.
    public List<Car> getCars() {
        return cars;
    }
}