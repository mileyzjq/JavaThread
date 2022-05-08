public class TestYield {
    static class MyYield implements Runnable {
        @Override
        public void run() {
            System.out.println(String.format("Thread %s --> START", Thread.currentThread().getName()));
            Thread.yield();
            System.out.println(String.format("Thread %s --> END", Thread.currentThread().getName()));
        }
    }

    public static void main(String[] args) {
        MyYield myYield = new MyYield();
        new Thread(myYield, "A").start();
        new Thread(myYield, "B").start();
    }
}

