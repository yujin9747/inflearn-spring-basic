package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest1 {

    @Test
    public void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean bean = ac.getBean(PrototypeBean.class);
        bean.addCount();
        Assertions.assertThat(bean.getCount()).isEqualTo(1);

        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();
        Assertions.assertThat(bean2.getCount()).isEqualTo(1);
    }

    @Test
    public void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        // singleton bean 내에 있는 필드를 사용해서 add 했기 때문에 prototypeBean은 한 번만 init되고, 재사용된다.
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1); // 기존에 있던 prototypeBean을 재사용했기 때문에 2가 나온다.
    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean {

        // spring외의 다른 컨테이너에서 코드를 사용할 일이 있는게 아니라면 spring에 의존적인 ObjectProvider를 사용하는 것이 좋다.

        // Provider는 자바 표준. JSR-330. get() 하나로 매우 기능이 단순함. 단위테스트를 만들거나 mock 코드를 만들기 훨씬 쉬워진다.
        private final Provider<PrototypeBean> prototypeBeanProvider;

// ObjectProvider : ObjectFactory의 확장 버전, 둘 다 spring에 의존적인 방법!!
        // spring container에서 대신 조회해주는 대리자 역할 : ObjectProvider --> Dependency Lookup 정도의 기능만 제공함 (DI와 반대 방향)
//        private final PrototypeBean prototypeBean;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public int getCount() {
            return count;
        }

        public int addCount() {
            return ++count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
