package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
	
	public MemberService memberService() {
		return new MemberServiceImpl(memberRepository()); //생성자주입
	}

	private MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
	// └ 이거 리턴타입은 구체클래스가 아니라 인터페이스를 선택해야됨
	
	public OrderService orderService() {
		return new OrderServiceImpl(memberRepository(), discountPolicy());
	}
	
	public DiscountPolicy discountPolicy() {
		//return new FixDiscountPolicy();  //여기만 바꾸면 정해진 금액의 할인 아니면 비율로 할인 이렇게 설정가능
		return new RateDiscountPolicy();   //이렇게 되면 구성 역활을 담당하는  AppConfig만 바꾸면된다
	}                                      // 사용 역활을 담당하는 어떤 코드들도 번경할 필요없다.
}

// 이렇게 되면 에플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악할수 있다.


//AppConfig는 에플리케이션의 실제 동작에 필요한 '구현 객체를 생성'
//AppConfig는 생성한 객체 인스턴스의 참조를  생성자를 통해서 주입(연결)

//AppConfig 는 공연 관계자 라고 생각 이거는 애플리케이션이 어떻게 동작해야 할지 전체 구성을 책임짐