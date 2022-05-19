public class TVShow {
    public static void main(String[] args) {
        TV tv = new TV();

        new Performer(tv).start();
        new Audience(tv).start();
    }
}

class Performer extends Thread {
    TV tv;

    public Performer(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if(i % 3 == 0) {
                this.tv.play("Little Prince");
            } else {
                this.tv.play("Snow White");
            }
        }
    }
}

class Audience extends Thread {
    TV tv;

    public Audience(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            this.tv.watch();
        }
    }
}

class TV extends Thread {
    String program;
    boolean flag = true;

    public synchronized void play(String program) {
        if(!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Performers play " + program + "...");
        this.program = program;
        this.flag = !this.flag;
        this.notifyAll();
    }

    public synchronized void watch() {
        if(flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Audience play " + this.program + "***");
        this.flag = !this.flag;
        this.notifyAll();
    }
}
