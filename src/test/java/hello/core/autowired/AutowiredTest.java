package hello.core.autowired;

import hello.core.member.domain.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
        @Autowired(required = false)
        public void setNoBean1(Member member) {
            // Member는 스프링 빈이 아니다.
            // 아예 호출 안됨
            System.out.println("setNoBean1 = " + member);
        }

        @Autowired
        public void setNoBean2(@Nullable Member member) {
            // Member는 스프링 빈이 아니다.
            // null이 들어감
            System.out.println("setNoBean2 = " + member);
        }

        @Autowired
        public void setNoBean3(Optional<Member> member) {
            // Member는 스프링 빈이 아니다.
            // Optional.empty가 들어감 -> isPresent() = false
            System.out.println("setNoBean3 = " + member);
            System.out.println("setNoBean3 = " + member.isPresent());
        }
    }
}
