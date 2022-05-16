public class DeadLock {
    public static void main(String[] args) {
        Makeup lily = new Makeup(0, "Lily");
        Makeup mary = new Makeup(1, "Mary");

        lily.start();
        mary.start();
    }

}

class Lipstick {

}

class Mirror {

}

class Makeup extends Thread {
    static  Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();

    int choice;
    String girlName;

    public Makeup(int choice, String girlName) {
        this.choice = choice;
        this.girlName = girlName;
    }

    public void run() {
        if(choice == 0) {
            synchronized (lipstick) {
                System.out.println(this.girlName + " get lipstick lock");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Dead lock
                synchronized (mirror) {
                    System.out.println(this.girlName + " get mirror lock");
                }
            }
//            synchronized (mirror) {
//                System.out.println(this.girlName + " get mirror lock");
//            }
        } else {
            synchronized (mirror) {
                System.out.println(this.girlName + " get mirror lock");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Dead lock
                synchronized (lipstick) {
                    System.out.println(this.girlName + " get lipstick lock");
                }
            }
            //Not Dead lock
//            synchronized (lipstick) {
//                System.out.println(this.girlName + " get lipstick lock");
//            }

        }
    }
}

