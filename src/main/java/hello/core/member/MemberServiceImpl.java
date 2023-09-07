package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

	private final MemberRepository memberRepository;
	
	
	@Autowired //ac.getBean(MemberRepository.class) 이런식으로 동작 한다고 보면된다 Autowired는 
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public void join(Member member) {
		memberRepository.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId);
	}
	
	
	//테스트 용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}

}


//설계 변경으로 MemberServiceImpl은 더이상 MemoryMemberRepository를 의존하지않는다.
//단지 MemberRepository 인터페이스만 의존 (무엇이 들어올지 모른다  {어떤게 들어올것이다 이느낌})
//MemberServiceImpl의 생정자를 통해 어떤 구현 객체를 주입할지는 외부(여기서는 AppConfig) 에서 결정
//MemberServiceImpl의  의존관계의 고민은 외부에 맡기고 실행하는거에만 집중!!