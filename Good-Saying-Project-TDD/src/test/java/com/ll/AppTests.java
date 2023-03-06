package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AppTests {
    @Test
    @DisplayName("스캐너에 키보드가 아닌 문자열을 입력으로 설정")
    public void t0() {
        Scanner sc = TestUtil.genScanner("안녕");

        String input = sc.nextLine().trim();
        assertThat(input).isEqualTo("안녕");
    }

    @Test
    @DisplayName("출력을 모니터가 아닌 문자열로 얻기")
    public void t1() {
        // System.out 에 대한 화면출력 금지 시작
        ByteArrayOutputStream outputStream = TestUtil.setOutToByteArray();

        System.out.print("안녕");

        // 그 동안 출력되지 않던 내용들을 문자열로 반환
        String output = outputStream.toString();

        // System.out 에 대한 화면출력 금지 끝
        TestUtil.clearSetOutToByteArray(outputStream);

        assertThat(output).isEqualTo("안녕");
    }

    @Test
    @DisplayName("명언 앱 타이틀 출력 및 커맨드 라인 출력")
    public void t2() {
        String rs = AppTestRunner.run("");
        assertThat(rs)
                .contains("== 명언 앱 ==")
                .contains("명령) ")
                .contains("프로그램이 종료되었습니다.")
                .doesNotContain("올바르지 않은 명령입니다.");
    }

    @Test
    @DisplayName("잘못된 명령어 입력에 대한 처리")
    public void t3() {
        String rs = AppTestRunner.run("종료2");

        assertThat(rs)
                .contains("올바르지 않은 명령입니다.");
    }
}
