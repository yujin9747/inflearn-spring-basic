package hello.core.member.service;

import hello.core.member.domain.Member;
import hello.core.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// OrderServiceImpl 과 똑같은 이름인 경우 BeanDefinitionStoreException 발생
//@Component("service")
@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository; // 오로지 추상화(인터페이스)에만 의존한다.

    @Autowired // 생성자가 하나일 경우 생략 가능. ac.getBean(MemberRepository.class)와 같은 의미
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

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
