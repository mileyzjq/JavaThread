import java.util.concurrent.locks.ReentrantLock;

public class Ticket implements Runnable {
    private int ticketNums = 10;
    boolean flag = true;
    @Override
    public void run() {
        while(flag) {
            checkValidation();
        }
    }

    private synchronized void checkValidation() {
        if(ticketNums <= 0) {
            flag = false;
            return;
        }
        try {
            Thread.sleep(200);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" get tickets number: " + ticketNums--);
    }

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket, "Tom").start();
        new Thread(ticket, "Lucy").start();
        new Thread(ticket, "Jack").start();

//        Ticket2 ticket2 = new Ticket2();
//        new Thread(ticket2, "Tom").start();
//        new Thread(ticket2, "Lucy").start();
//        new Thread(ticket2, "Jack").start();
    }
}

// lock
class Ticket2 implements Runnable {
    private int ticketNums = 100;
    private final ReentrantLock lock =  new ReentrantLock();

    @Override
    public void run() {
        while(true) {
            try {
                lock.lock();
                if(ticketNums <= 0) {
                    break;
                }
                System.out.println(Thread.currentThread().getName()+" get tickets number: " + ticketNums--);
                try {
                    Thread.sleep(1000);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
            }
        }
    }
}