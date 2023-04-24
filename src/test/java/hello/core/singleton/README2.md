# 싱글턴 패턴의 주의점

## stateless로 설계해야 한다!
- 특정 클라이언트에 의존적인 필드가 있으면 안된다.
- 특정 클라이언트가 값을 변경할 수 있어도 안된다.
- 가급적 읽기만 가능해야 한다.
- 필드 대신 자바에서 공유되지 않는 local var, parameter, ThreadLocal 등을 사용한다.
=> 나중에 정말 큰 장애로 이어질 수 있다.


## stateful 설계시 문제점

```java

public class StatefulService {
  private int price; // 상태를 가진 필드

  public void order(String name, int price){
    System.out.println("name = " + name + " price = " + price);
    this.price = price;
  }

  public int getPrice(){
    return price;
  }
}

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
}
```
StatefulService에서 price 필드를 공유하면서, 사용자A의 주문 내역이 사용자B의 주문내역으로 덮어쓰기 되는 것이 확인된다. (실무에서 이러면 큰일난다.)
stateful한 price때문에 문제가 발생!


## 해결
```java
public class StatefulService {
    public int order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        return price;
    }
}
```
상태를 가진 price를 삭제하고 local variable로 리턴하게 만든다.