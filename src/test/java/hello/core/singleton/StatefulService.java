package hello.core.singleton;

public class StatefulService {
    private int price; // 상태를 가진 필드

    public void order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        this.price = price;
    }

    public int getPrice(){
        return price;
    }

    public int order2(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        return price;
    }
}
