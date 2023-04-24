package hello.core.singleton;

public class SingletonService {

    // singleton
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){

    }

//    public static void main(String[] args) {
//        SingletonService singletonService = new SingletonService(); // 자기 자신은 호출가능 ^^;
//    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
