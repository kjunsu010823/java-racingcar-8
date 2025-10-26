package racingcar.controller;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

// NsTest를 상속받아야 합니다.
class GameControllerTest extends NsTest {

    // camp.nextstep.edu.missionutils.Randoms 유틸리티의 기준
    private static final int MOVING_FORWARD = 4; // 4 이상이면 전진
    private static final int STOP = 3;           // 3 이하이면 정지

    @Test
    @DisplayName("정상적인 게임 흐름: pobi가 단독 우승한다")
    void run_NormalGameFlow_SingleWinner() {
        // assertRandomNumberInRangeTest: 랜덤 값을 고정시킵니다.
        assertRandomNumberInRangeTest(
                () -> {
                    // run(): NsTest의 메서드로, 입력을 시뮬레이션하고 runMain()을 실행합니다.
                    run("pobi,woni", "1"); // 1. 자동차 이름 입력, 2. 시도 횟수 입력

                    // output(): NsTest의 메서드로, System.out.print/println 출력을 캡처합니다.
                    assertThat(output()).contains(
                            "실행 결과",
                            "pobi : -",  // pobi는 전진 (MOVING_FORWARD)
                            "woni :",    // woni는 정지 (STOP)
                            "최종 우승자 : pobi"
                    );
                },
                // 1라운드 랜덤 값 (pobi, woni 순서)
                MOVING_FORWARD, STOP
        );
    }

    @Test
    @DisplayName("공동 우승자가 발생하는 경우: 쉼표로 구분하여 출력한다")
    void run_GameWithJointWinners() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("cat,dog", "2");

                    assertThat(output()).contains(
                            "실행 결과",
                            "cat : -",
                            "dog : -",   // 1라운드: 둘 다 전진
                            "cat : --",
                            "dog : --",  // 2라운드: 둘 다 전진
                            "최종 우승자 : cat, dog" // 공동 우승
                    );
                },
                // 1라운드 (cat, dog)
                MOVING_FORWARD, MOVING_FORWARD,
                // 2라운드 (cat, dog)
                MOVING_FORWARD, MOVING_FORWARD
        );
    }

    @Test
    @DisplayName("입력 유효성 검사: 자동차 이름이 5자를 초과하면 [ERROR]를 출력한다")
    void run_ThrowsException_WhenCarNameIsInvalid() {
        // assertSimpleTest: 랜덤 값이 필요 없는 단순 입출력 테스트에 사용합니다.
        assertSimpleTest(
                () -> {
                    // runException(): NsTest의 메서드로, 예외 발생을 테스트합니다.
                    // InputView에서 예외가 발생하고 프로그램이 종료될 것을 기대합니다.
                    runException("pobi,longname", "1"); // "longname"이 5자 초과

                    // InputView가 [ERROR]로 시작하는 메시지를 출력하는지 확인합니다.
                    assertThat(output()).contains("[ERROR]");
                }
        );
    }

    // NsTest의 run() 메서드는 내부적으로 이 runMain()을 호출합니다.
    // 테스트 대상이 되는 메인 로직을 여기에 작성해야 합니다.
    @Override
    protected void runMain() {
        // 사용자가 제공한 GameController를 생성하고 run()을 호출합니다.
        // (만약 Application.java의 main에서 실행한다면 Application.main(new String[]{})을 호출)
        GameController gameController = new GameController();
        gameController.run();
    }
}