package collections;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

class Average {
  private long count = 0;
  private double sum = 0;
  private static Set<Thread> threads = Collections.synchronizedSet(new HashSet<>());

  public Average() {}

  public void include(double d) {
//    threads.add(Thread.currentThread());

    sum += d;
    count++;
  }
  public void merge(Average other) {
    this.count += other.count;
    this.sum += other.sum;
  }
  public double get() {
    return sum / count;
  }

  public Set<Thread> getThreads() {
    return threads;
  }
}

public class Averager {
  public static void main(String[] args) {
    long start = System.nanoTime();
    Average av = DoubleStream.generate(() -> ThreadLocalRandom.current().nextDouble(-Math.PI, Math.PI))
        .parallel()
        .limit(300_000_000L)
//        .limit(3_000_000L)
        .map(x -> Math.sin(x))
        .collect(() -> new Average(), (a, d) -> a.include(d), (a, a2) -> a.merge(a2));
    long time = System.nanoTime() - start;

    System.out.printf("Average is %6.4f, time taken was %6.4f seconds\n",
        av.get(), (time / 1_000_000_000.0));
//    System.out.println("Threads are: " + av.getThreads());

//        .forEach(n -> System.out.println(n));
  }
}
