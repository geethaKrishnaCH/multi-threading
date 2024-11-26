package me.easylearnz;

public class SumUpExample {
  private long startRange;
  private long endRange;
  private long sum;
  private static long MAX_NUM = Integer.MAX_VALUE;

  public SumUpExample(long startRange, long endRange) {
    this.startRange = startRange;
    this.endRange = endRange;
  }

  public void add() {
    sum = 0;
    for (long i = startRange; i < endRange; i++) {
      sum += i;
    }
  }

  public static void oneThread() {
    SumUpExample s3 = new SumUpExample(1, MAX_NUM);
    long start = System.currentTimeMillis();
    s3.add();
    long end = System.currentTimeMillis();
    long totalSum = s3.sum;

    System.out.println("One thread final count = " + totalSum + " took " + (end - start));
  }

  public static void twoThreads() {
    SumUpExample s1 = new SumUpExample(1, MAX_NUM / 2);
    SumUpExample s2 = new SumUpExample(MAX_NUM / 2 + 1, MAX_NUM);

    Thread thread1 = new Thread(() -> {
      s1.add();
    });
    Thread thread2 = new Thread(() -> {
      s2.add();
    });

    long start = System.currentTimeMillis();

    thread1.start();
    thread2.start();

    try {
      thread1.join();
      thread2.join();
    } catch (Exception e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    }

    long finalSum = s1.sum + s2.sum;
    long end = System.currentTimeMillis();

    System.out.println("Two threads final count = " + finalSum + " took " + (end - start));

  }

  public static void main(String[] args) {
    oneThread();
    twoThreads();
  }
}
