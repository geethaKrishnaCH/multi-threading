package me.easylearnz;

import java.util.Random;

public class UnsafeCounter {
  private int counter = 0;
  private static Random random = new Random(System.currentTimeMillis());

  public void increment() {
    counter++;
  }

  public void decrement() {
    counter--;
  }

  public static void main(String[] args) {
    System.out.println(Runtime.getRuntime().availableProcessors());
    UnsafeCounter usCounter = new UnsafeCounter();
    usCounter.notify();
    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        usCounter.increment();
        sleepRandomlyForLessThan10Secs();
      }
    });
    Thread t2 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        usCounter.decrement();
        sleepRandomlyForLessThan10Secs();
      }

    });
    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (Exception e) {
      Thread.currentThread().interrupt();
    }
    System.out.println(usCounter.counter);
  }

  public static void sleepRandomlyForLessThan10Secs() {
    try {
      Thread.sleep(random.nextInt(10));
    } catch (InterruptedException ie) {
    }
  }
}
