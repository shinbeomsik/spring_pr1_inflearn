package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
	
	public MemberService memberService() {
		return new MemberServiceImpl(new MemoryMemberRepository()); //생성자주입
	}
	
	public OrderService orderService() {
		return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
	}
}


//AppConfig는 에플리케이션의 실제 동작에 필요한 '구현 객체를 생성'
//AppConfig는 생성한 객체 인스턴스의 참조를  생성자를 통해서 주입(연결)

//AppConfig 는 공연 관계자 라고 생각 이거는 애플리케이션이 어떻게 동작해야 할지 전체 구성을 책임짐