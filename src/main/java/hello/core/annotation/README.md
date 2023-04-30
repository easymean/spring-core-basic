# @Autowired, @Qualifier, @Primary

## 조회 대상 빈이 2개 이상일 때 해결 방법
@Autowired는 기본적으로 타입 매칭을 사용하여 조회한다. 아래 코드는 **ac.getBean(DiscountPolicy.class)** 와 유사하게 동작한다고 보면 된다.

```java
@Autowired
private DiscountPolicy discountPolicy;
```

따라서 DiscountPolicy의 하위타입인 FixDiscountPolicy, RateDiscountPolicy 를 모두 스프링 빈으로 등록한다면 **NoUniqueBeanDefinitionException** 이 발생한다.
동일한 타입의 빈이 2개이기 떄문!

```java
@Component
public class FixDiscountPolicy implements DiscountPolicy {
}

@Component
public class RateDiscountPolicy implements DiscountPolicy {
}

// 의존성 자동 주입
@Autowired
private DiscountPolicy discountPolicy;
```

이런 경우 해결방법에 대해 알아보자.
## 1. @Autowired

@Autowired는 빈 매칭시 다음의 규칙을 사용한다.
1) 타입 매칭
2) 1)의 결과가 2개 이상이라면, 필드명, 파라미터명으로 빈 이름 매칭

2)를 사용하여 DiscountPolicy가 RateDiscountPolicy를 주입할 수 있다.

```java
@Autowired
private DiscountPolicy rateDiscountPolicy;
```

## 2.@Qualifier
@Qualifier을 사용하여 추가 구분자를 붙여준다. (빈 이름 변경은 X)

```java
@Component
@Qualifier("mainDiscountPolicy")
public class RateDiscountPolicy implements DiscountPolicy{
    
}
```
자동 주입을 할 땐 아래와 같이 사용한다.

```java
// 생성자 자동 주입 예시
@Autowired
pulic OrderServiceImpl(@Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy){
    this.discountPolicy = discountPolicy;
}

// 수정자 자동 주입 예시
@Autowired
public DiscountPolicy setDiscountPolicy(@Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy){
    this.discountPolicy = discountPolicy;
}
```

@Qualifier는 빈 매칭시 다음의 규칙을 사용한다.
1) @Qualifier끼리 매칭
2) 빈 이름 매칭
3) NoSuchBeanDefinitionException 예외 발생

<br>

## 3. @Primary
우선순위를 정하는 방법이다. @Autowired 시에 여러 빈이 매칭되면 @Primary가 우선권을 가진다.
rateDiscountPolicy가 우선권을 가지게 하고 싶다면

```java
@Component
public class FixDiscountPolicy implements DiscountPolicy {
}

@Component
@Primary
public class RateDiscountPolicy implements DiscountPolicy {
}
```

## + @Primary, @Qualifier 언제 사용해야 할까?
디폴트로 설정해야 하는 값은 @Primary, 사용자 설정이 추가되어야 한다면 추가로 @Qualifier 를 사용하는 것이 좋다. <br>
예를 들어 DB 커넥션을 관리하는 빈이라면 <br>
-> 메인 DB 커넥션 - @Primary <br>
-> 서브 DB 커넥션 - @Qualifier <br>
로 정의하는 것이 좋겠다.

## + @Primary vs @Qualifier 우선순위?
@Qualifier 가 더 높다.
