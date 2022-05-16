public class Daemon {
    public static void main(String[] args) {
        God god = new God();
        People1 p1 = new People1();

        Thread thread = new Thread(god);
        thread.setDaemon(true);

        Thread thread2 = new Thread(p1);
        thread2.setPriority(Thread.MAX_PRIORITY);
        thread2.start();
        Thread thread3 = new Thread(p1);
        thread3.setPriority(Thread.MIN_PRIORITY);
        thread3.start();

        //thread.start();
    }
}

class God implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("God bless you!");
        }
    }
}

class People1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " live happily!");
        }
    }
}