# 컴포넌트 스캔 filter 적용

## Annotation 생성하기
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {

}
```

## 필터 걸기

configuration 클래스에 다음과 같이 annotation을 건다.

```java
@Configuration
@ComponentScan(
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
)
static class ComponentFilterAppConfig { }
```

includeFilters는 컴포넌트 스캔 대상,
excludeFilters는 컴포넌트 스캔 대상이 아니다.