package hello.core;

import hello.core.member.domain.Grade;
import hello.core.member.domain.Member;
import hello.core.member.service.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        //기존에는 spring을 사용하지 않고 AppConfig 에서 직접 꺼내서 사용
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        // AppConfig에 있는 환경 설정 정보를 가지고 spring container에 빈을 생성해서 관리한다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // spring container에서 빈을 가져오는 방식으로 변경
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("find member = " + findMember.getName());
        System.out.println("new member = " + member.getName());
    }
}
