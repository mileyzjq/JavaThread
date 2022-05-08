public class StaticProxy {
    public static void main(String[] args) {
        // Lambda expression enable runnable interface
        new Thread(()-> System.out.println("I love you")).start();
        //People p1 = new People("Tom");
        new WeddingCompany(new People("Tom")).HappyMarry();
    }
}

interface Marry {
    void HappyMarry();
}

class People implements Marry {
    private String name;

    public People(String name) {
        this.name = name;
    }

    @Override
    public void HappyMarry() {
        System.out.println(this.name + " is going to have a happy wedding");
    }
}

class WeddingCompany implements Marry {
    private Marry target;

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void HappyMarry() {
        before();
        this.target.HappyMarry();
        after();
    }

    private void after() {
        System.out.println("After, Cleaning...");
    }

    private void before() {
        System.out.println("Before, Preparing...");
    }
}