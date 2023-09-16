package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

@Configuration
@ComponentScan(
		basePackages = "hello.core.member" , //탐색할 패키지의 시작 위치를 지정 --> 이 패키지를 시작으로 하위 패키지 모두 탐색 
				   //= {"hello.core" , "hello.service"} 이런식으로 여러 시작위치를 지정할수도 있다.
		basePackageClasses = AutoAppConfig.class,
		excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION , classes = Configuration.class)
		)
//만약에 아무것도 지정하지 않으면 @ComponentScan 이 붙은 설정 정보의 클래시의 패키지가 시작위치가 된다.
//관례는 프로젝트 루트(기본 가장 최상위폴더) 에 지정한다
//스프링부트는 @SpringBootApplication(시작과 동시에 만들어지는 파일) 여기에도 @ComponentScan이 있긴하다 그래서 자동으로 등록
public class AutoAppConfig {
	
	/*
	 * @Bean(name = "memoryMemberRepository") MemberRepository memberRepository() {
	 * return new MemoryMemberRepository(); }
	 */
}
