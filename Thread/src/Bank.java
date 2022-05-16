public class Bank {
    public static void main(String[] args) {
        Account account = new Account(100, "Tom");
        Draw draw1 = new Draw(account, 50, "draw1");
        Draw draw2 = new Draw(account, 100, "draw1");

        draw1.start();
        draw2.start();
    }
}

class Account {
    private float balance;
    String userName;

    public Account(float balance, String userName) {
        this.balance = balance;
        this.userName = userName;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}

class Draw extends Thread {
    Account account;
    float drawAmount;

    public Draw(Account account, float drawAmount, String name) {
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    // synchronized 默认锁 是this
    // synchronized 锁的对象是变化的对象, 需要增删改查的对象
    public void run() {
        synchronized (account) {
            if(account.getBalance() < drawAmount) {
                System.out.println(Thread.currentThread().getName() + " not enough money!");
                return;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            float currMoney = account.getBalance() - drawAmount;
            account.setBalance(currMoney);
            System.out.println(String.format("%s withdraw %.2f$ and has %.2f$ left", account.userName, drawAmount,
                    currMoney));
        }
    }
}
