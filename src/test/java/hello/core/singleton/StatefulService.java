package hello.core.singleton;

public class StatefulService {
//    private int price; // 상태를 유지하는 필드

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
//        this.price = price; // 여기가 문제!
        return price;
    }

//    public int getPrice() {
//        return price;
//    }

    // 싱글톤 객체는 "상태를 유지(stateful)"하게 설계하면 안된다!
}
