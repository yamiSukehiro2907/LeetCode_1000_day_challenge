
import java.util.*;
import java.util.concurrent.*;

class Consumer implements Runnable {
    private Queue<Object> store;
    @SuppressWarnings("unused")
    private int maxSize;
    private String name;
    private Semaphore prodSemaphore;
    private Semaphore conSemaphore;

    public Consumer(Queue<Object> store, int maxSize, String name, Semaphore prodSemaphore, Semaphore conSemaphore) {
        this.store = store;
        this.maxSize = maxSize;
        this.name = name;
        this.prodSemaphore = prodSemaphore;
        this.conSemaphore = conSemaphore;
    }

    public void run() {
        while (true) {
            try {
                conSemaphore.acquire(); // if(c > 0) c--
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (store.size() > 0) { // if c > 0 then no need to check here
                System.out.println(this.name + " is consuming an item, Size = " + store.size());
                store.remove();
            }

            prodSemaphore.release();
        }
    }
}

class Producer implements Runnable {
    private Queue<Object> store;
    private int maxSize;
    private String name;
    private Semaphore prodSemaphore;
    private Semaphore conSemaphore;

    public Producer(Queue<Object> store, int maxSize, String name, Semaphore prodSemaphore, Semaphore conSemaphore) {
        this.store = store;
        this.maxSize = maxSize;
        this.name = name;
        this.prodSemaphore = prodSemaphore;
        this.conSemaphore = conSemaphore;
    }

    public void run() {
        while (true) {
            try {
                prodSemaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (store.size() < maxSize) { // if p > 0 then no need to check here
                System.out.println(this.name + " is producing an item, Size = " + store.size());
                store.add(new Object());
            }

            conSemaphore.release();
        }
    }
}

public class ProducerConsumerSemaphores {
    public static void main(String[] args) {
        Queue<Object> store = new ConcurrentLinkedQueue<>();
        int maxSize = 6;
        Semaphore producerSemaphore = new Semaphore(maxSize);
        Semaphore consumerSemaphore = new Semaphore(0);

        Producer p1 = new Producer(store, maxSize, "P1", producerSemaphore, consumerSemaphore);
        Producer p2 = new Producer(store, maxSize, "P2", producerSemaphore, consumerSemaphore);
        Producer p3 = new Producer(store, maxSize, "P3", producerSemaphore, consumerSemaphore);
        Producer p4 = new Producer(store, maxSize, "P4", producerSemaphore, consumerSemaphore);
        Producer p5 = new Producer(store, maxSize, "P5", producerSemaphore, consumerSemaphore);
        Producer p6 = new Producer(store, maxSize, "P6", producerSemaphore, consumerSemaphore);

        Consumer c1 = new Consumer(store, maxSize, "C1", producerSemaphore, consumerSemaphore);
        Consumer c2 = new Consumer(store, maxSize, "C2", producerSemaphore, consumerSemaphore);
        Consumer c3 = new Consumer(store, maxSize, "C3", producerSemaphore, consumerSemaphore);
        Consumer c4 = new Consumer(store, maxSize, "C4", producerSemaphore, consumerSemaphore);
        Consumer c5 = new Consumer(store, maxSize, "C5", producerSemaphore, consumerSemaphore);

        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);
        Thread t3 = new Thread(p3);
        Thread t4 = new Thread(p4);
        Thread t5 = new Thread(p5);
        Thread t6 = new Thread(p6);

        Thread t7 = new Thread(c1);
        Thread t8 = new Thread(c2);
        Thread t9 = new Thread(c3);
        Thread t10 = new Thread(c4);
        Thread t11 = new Thread(c5);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        t11.start();
    }
}