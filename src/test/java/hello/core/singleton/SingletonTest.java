package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("스프링없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        // 1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회: 또 객체 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른가: 다르다 => 객체가 많이 생성된다.
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }
}
