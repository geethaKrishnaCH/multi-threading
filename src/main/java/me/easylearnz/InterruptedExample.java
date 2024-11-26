package me.easylearnz;

public class InterruptedExample {
  public static void main(String[] args) throws InterruptedException {

    Thread thread1 = new Thread(() -> {
      try {
        System.out.println("I am too sleepy, let me sleep for an hour");
        Thread.sleep(1000 * 60 * 60);
      } catch (Exception e) {
        System.out.println(
            "The interrupt flag is cleared " + Thread.interrupted() + ",  " + Thread.currentThread().isInterrupted());
        Thread.currentThread().interrupt();
        System.out.println("Oh someone woke me up");
        System.out.println("The interrupt flag is now set to " + Thread.interrupted() + ",  "
            + Thread.currentThread().isInterrupted());
      }
    });
    thread1.start();
    thread1.interrupt();
    thread1.join();
  }
}
