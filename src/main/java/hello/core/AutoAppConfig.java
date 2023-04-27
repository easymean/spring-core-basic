package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//        basePackages = "hello.core.member", // 시작점을 지정. 없으면 모든 자바 클래스를 다 조회한다.
//        basePackageClasses = AutoAppConfig.class,
        // base를 지정하지 않는다면 여기가 시작점
                excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}