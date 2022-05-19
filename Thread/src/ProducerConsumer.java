import java.util.Stack;

public class ProducerConsumer {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();
        new Thread(new Producer(container)).start();
        new Thread(new Consumer(container)).start();
    }
}

class Producer implements Runnable{
    SynContainer container;

    public Producer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            this.container.pushProduct(new Product(i));
            System.out.println(String.format("Produce %d product ...", i));
        }
    }
}

class Consumer implements  Runnable {
    SynContainer container;

    public Consumer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            this.container.popProduct();
            System.out.println(String.format("Consume %d product ***", i));
        }
    }
}

class Product {
    int id;

    public Product(int id) {
        this.id = id;
    }
}

class SynContainer extends Thread {
    Stack<Product> stack = new Stack<>();
    private static final int VOLUME = 10;

    public synchronized void pushProduct(Product product) {
        if(stack.size() >= VOLUME) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stack.push(product);
        this.notifyAll();
    }

    public synchronized Product popProduct() {
        if(stack.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Product product = stack.pop();
        this.notifyAll();
        return product;
    }
}