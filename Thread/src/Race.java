public class Race implements Runnable {

    private static String winner;

    @Override
    public void run() {
        for(int i=0; i<=100; i++) {
            if(gameOver(i)) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + " step: " + i);
        }
    }

    private boolean gameOver(int step) {
        if(winner != null) return true;
        if(step >= 100) {
            winner = Thread.currentThread().getName();
            System.out.println("Winner is: " + winner);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race, "rabbit").start();
        new Thread(race, "turtle").start();
    }
}
