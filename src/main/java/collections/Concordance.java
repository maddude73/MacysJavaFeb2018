package collections;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Concordance {
  public static final Pattern WORD_BREAK = Pattern.compile("\\W+");

  public static void main(String[] args) {
    try (Stream<String> ss = Files.lines(Paths.get("PrideAndPrejudice.txt"))) {
      ss
          .map(s -> s.toLowerCase())
          // split into separate words!
//          .flatMap(s -> WORD_BREAK.splitAsStream(s))
          .flatMap(WORD_BREAK::splitAsStream)
          // throw away empty words
          .filter(s -> !s.isEmpty())
          // build table (aka Map of word to count
          .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
          .entrySet().stream()
          .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
          .limit(200)
//          .map((Map.Entry<String, Long> p) -> "The word " + p.getKey() + " occurs " + p.getValue() + " times")
          .map(p -> "The word " + p.getKey() + " occurs " + p.getValue() + " times")
//          .forEach(p -> System.out.println(p));
          .forEach(System.out::println);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
