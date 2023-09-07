package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

@Configuration
public class AppConfig {
	
	// @Bean memberService호출하면 --> new MemoryMemberRepository()을 호출
	// @Bean orderService호출 --> new MemoryMemberRepository() 을 호출
	
	// 가장 먼저 call AppConfig.memberService 호출                  (실행되는 순서는 다를수있다!) ==>여기는 우리가 생각할때
	// 그다음   call AppConfig.memberRepository 호출
	// 그다음 call AppConfig.memberRepository 호출
	// 그다음call AppConfig.orderService 호출
	// 마지막 call AppConfig.memberRepository 호출
	
	
	//==-===
	// call AppConfig.memberService
	// call AppConfig.memberRepository
	// call AppConfig.orderService           실제로는 이렇게 호출이됨
	
	@Bean
	public MemberService memberService() {
		System.out.println("call AppConfig.memberService");
		return new MemberServiceImpl(memberRepository()); // 생성자주입
	}

	@Bean
	public MemberRepository memberRepository() {
		System.out.println("call AppConfig.memberRepository");
		return new MemoryMemberRepository();
	}

	// └ 이거 리턴타입은 구체클래스가 아니라 인터페이스를 선택해야됨
	@Bean
	public OrderService orderService() {
		System.out.println("call AppConfig.orderService");
		return new OrderServiceImpl(memberRepository(), discountPolicy());
	}

	@Bean
	public DiscountPolicy discountPolicy() {
		// return new FixDiscountPolicy(); //여기만 바꾸면 정해진 금액의 할인 아니면 비율로 할인 이렇게 설정가능
		return new RateDiscountPolicy(); // 이렇게 되면 구성 역활을 담당하는 AppConfig만 바꾸면된다
	} // 사용 역활을 담당하는 어떤 코드들도 번경할 필요없다.
}

// 이렇게 되면 에플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악할수 있다.

//AppConfig는 에플리케이션의 실제 동작에 필요한 '구현 객체를 생성'
//AppConfig는 생성한 객체 인스턴스의 참조를  생성자를 통해서 주입(연결)

//AppConfig 는 공연 관계자 라고 생각 이거는 애플리케이션이 어떻게 동작해야 할지 전체 구성을 책임짐