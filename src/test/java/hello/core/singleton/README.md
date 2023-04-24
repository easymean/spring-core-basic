# 싱글턴 패턴

## 등장배경
- 싱글턴 패턴을 사용하지 않으면 요청시 마다 새로운 객체가 생성&소멸된다. > AppConfig에서 매번 new를 통해 생성한다.
- 메모리 낭비가 심하다. (하드웨어가 좋아져서 크게 못 느끼긴하지만..)


## 해결책: 싱글턴 패턴
- 클래스의 인스턴스가 딱 1개만 생기는 것을 보장하는 디자인 패턴
- 그러면 객체가 2개 생기는 것을 막으려면? 
  - private 생성자를 선언하여 외부에서 new 키워드를 사용하지 못하도록 막는다.

```java
public class SingletonService {
    private static final SingletonService instance = new SingletonService();
    
    public static SingletonService getInstance(){
        return instance;
    }
    
    private SingletonService(){}
}
```

## 테스트
SingletonTest에서 테스트한 것처럼 매번 같은 객체가 생성되는 것을 확인할 수 있다.

## 싱글턴 패턴의 문제점
그러면 싱글턴 패턴이 무조건 좋은가? 아니다.
- 위에 적은 SingletonService 코드처럼 매번 적어야 한다.
- getInstance를 호출하면서 구체클래스에 의존한다. -> DIP 위반
- DIP를 위반하면 OCP를 위반할 확률도 높다.
- 테스트하기 어렵다.
- 내부 속성을 변경하거나 초기화가 어렵다.
- private 생성자라 자식 클래스 만들기가 어렵다.
- 결론적으로 유연성이 떨어진다.

## 스프링과 싱글턴
하지만 스프링 컨테이너는 객체를 싱글턴으로 관리해준다. (위에 귀찮은 코드 안 적어도 된다!)