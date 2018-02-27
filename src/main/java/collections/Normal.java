package collections;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Normal {
  public static void main(String[] args) {
    Set<Map.Entry<Integer, Long>> set = IntStream.generate(() ->
        ThreadLocalRandom.current().nextInt(1, 7) +
            ThreadLocalRandom.current().nextInt(1, 7) +
            ThreadLocalRandom.current().nextInt(1, 7) +
            ThreadLocalRandom.current().nextInt(1, 7) +
            ThreadLocalRandom.current().nextInt(1, 7) +
            ThreadLocalRandom.current().nextInt(1, 7)
    )
        .limit(10_000)
        .boxed()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet();

    long maxCount = set.stream()
//        .mapToLong(e -> e.getValue())
        .mapToLong(Map.Entry::getValue)
        .max().orElse(1);

    set.stream()
        .sorted(Map.Entry.comparingByKey())
        .forEach(p -> System.out.println(p.getKey() + ": " + p.getValue()
            + Stream.generate(() -> "*").limit(100 * p.getValue() / maxCount).collect(Collectors.joining()))
        );
  }
}
