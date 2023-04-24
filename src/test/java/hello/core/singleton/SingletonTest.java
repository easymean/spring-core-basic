package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

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
        assertThat(memberService1).isNotSameAs(memberService2);
    }

//    public static void main(String[] args) {
//        SingletonService singletonService = new SingletonService(); // private으로 선언해서 호출 불가
//    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        assertThat(singletonService1).isSameAs(singletonService2); // 인스턴스 비교는 isSameAs
    }


}
