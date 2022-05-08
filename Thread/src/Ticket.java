public class Ticket implements Runnable {
    private int ticketNums = 10;
    @Override
    public void run() {
        while(true) {
            if(ticketNums <= 0) {
                break;
            }
            try {
                Thread.sleep(200);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" get tickets number: " + ticketNums--);
        }
    }

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket, "Tom").start();
        new Thread(ticket, "Lucy").start();
        new Thread(ticket, "Jack").start();
    }
}