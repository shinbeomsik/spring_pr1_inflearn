package hello.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;

	// @RequiredArgsConstructor이게 있으면 자동으로 이 밑에 있는걸 만들어준다.!!!
	@Autowired // 이거 한곳에만 있으면 생략가능
	public OrderServiceImpl(MemberRepository memberRepository,  @MainDiscountPolicy DiscountPolicy discountPolicy) {
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);

		return new Order(memberId, itemName, itemPrice, discountPrice);
	}

	// 테스트 용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}

}

//설계 변경으로 OrderServiceImpl은 더이상 FixDiscountPolicy를 의존하지않는다.
//단지 OrderServiceImpl 인터페이스만 의존 (무엇이 들어올지 모른다  {어떤게 들어올것이다 이느낌})
//OrderServiceImpl의 생정자를 통해 어떤 구현 객체를 주입할지는 외부(여기서는 AppConfig) 에서 결정
//OrderServiceImpl의  의존관계의 고민은 외부에 맡기고 실행하는거에만 집중!!
