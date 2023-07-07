package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

// public class NetworkClient implements InitializingBean, DisposableBean {
public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        //        connect(); // 생성자에서는 메서드를 호출하는 것을 지양해야 한다.
        //        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작 시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료 시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    // 의존관계 주입이 끝난 후 호출해주는 함수
    @PostConstruct
    public void init() throws Exception {
        System.out.println("NetworkClient.init");
        System.out.println("의존관계 주입 끝난 후, url 정보 is not null인 채로 연결 시도");
        connect();
        call("초기화 연결 메세지");
    }

    // 빈이 종료될 때 호출해주는 함수
    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
