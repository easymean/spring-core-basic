# 컴포넌트 스캔

## 기능
기존에 스프링에서 빈에 등록하기 위해선 클래스 내부에서 @Bean을 사용하여 등록해야 했다.
```java
@Configuration
public class AppConfig {

    @Bean()
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository()); // 의존성 주입
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
```

하지만 @ComponentScan을 사용하면 @Component가 사용된 클래스에 대해 자동으로 빈 등록을 해준다!
```java
// 빈으로 자동 등록
@Component()
public class MemberServiceImpl implements MemberService {
}
```

그렇다면 의존성 주입은 어떻게 하는가? @Autowired를 사용한다. 
<br/>기본 조회 전략은 타입이 같은 빈을 찾아서 주입한다. getBean(xxx.class)와 동일하다.
```java
@Component()
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired // ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}
```

## 탐색 시작점 정하기
- basePackages: 탐색할 패키지의 시작 위치를 지정. 이 패키지를 포함한 하위 패키지를 탐색한다,
- basePackageClasses: 지정한 클래스의 패키지가 탐색 시작 위치
- 만약 지정하지 않으면 @ComponentScan이 붙은 config class가 패키지의 시작 위치이다.

*권장사항*
@ComponentScan이 붙은 config class를 최상단에 두자.