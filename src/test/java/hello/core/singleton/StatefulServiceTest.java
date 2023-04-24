package hello.core.singleton;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;


class StatefulServiceTest {
    @Test
    void statefulServiceSingleTon(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        //ThreadA: 사용자A 10000원 주문
        statefulService1.order("userA", 10000);
        //ThreadB: 사용자B 30000원 주문
        statefulService2.order("userB", 30000);

        // 사용자A의 주문 내역을 확인하고 싶다.
        int price = statefulService1.getPrice();
        System.out.println(price); // 하지만 기대와 다르게 30000원이 출력된다.

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(30000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }


}