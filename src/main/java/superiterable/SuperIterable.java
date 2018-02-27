package superiterable;

import students.Student;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {
  private Iterable<E> self;

  public SuperIterable(Iterable<E> self) {
    this.self = self;
  }

  public SuperIterable<E> distinct() {
    Set<E> out = new HashSet<>();
    self.forEach(e -> out.add(e));
    return new SuperIterable<>(out);
  }
  public SuperIterable<E> filter(Predicate<E> pred) {
    List<E> out = new ArrayList<>();
    for (E e : self) {
      if (pred.test(e)) {
        out.add(e);
      }
    }
    return new SuperIterable<>(out);
  }

  public <F> SuperIterable<F> flatMap(Function<E, SuperIterable<F>> op) {
    List<F> out = new ArrayList<>();
    self.forEach(e -> op.apply(e).forEach(f -> out.add(f)));

    return new SuperIterable<>(out);
  }

  @Override
  public Iterator<E> iterator() {
    return self.iterator();
  }

  public <F> SuperIterable<F> map(Function<E, F> op) {
    List<F> out = new ArrayList<>();
    self.forEach(e -> out.add(op.apply(e)));
    return new SuperIterable<>(out);
  }

  public SuperIterable<E> peek(Consumer<E> op) {
    for (E e : self) {
      op.accept(e);
    }
    return this;
  }

  //  public void withEvery(Consumer<E> op) {
//    for (E e : self) {
//      op.accept(e);
//    }
//  }
//
  public static void main(String[] args) {
    SuperIterable<String> sos = new SuperIterable<>(Arrays.asList("Fred", "Jim", "Sheila", "Tim", "Albert"));

    for (String s : sos.filter(s -> s.length() > 3).filter(s -> s.length() < 6)) {
      System.out.println("> " + s);
    }

//    SuperIterable<Student> school = new SuperIterable<>(Arrays.asList(
//        Student.ofNameGpaCourses("Fred", 3.2, new String[]{"Math", "Physics"}),
//        Student.ofNameGpaCourses("Jim", 2.2, "Art"),
//        Student.ofNameGpaCourses("William", 3.9, "Art", "Math"),
//        Student.ofNameGpaCourses("Susan", 3.2, "Engineering", "Rocket Science"),
//        Student.ofNameGpaCourses("Albert", 1.2, "Art", "History", "Journalism", "Political Science"),
//        Student.ofNameGpaCourses("Sheila", 3.8, "Math", "Physics", "Chemistry")
//    ));
//
//    school
//        .filter(s -> s.getGpa() > 3)
//        .peek(s -> System.out.println(">> " + s))
//        .filter(s -> s.getName().charAt(0) > 'M')
////        .map(s -> s.getName())
//        .flatMap(s -> new SuperIterable<>(s.getCourses()))
//        .distinct()
//        .forEach(s -> System.out.println("--" + s))
//      ;

    List<Student> school = Arrays.asList(
        Student.ofNameGpaCourses("Fred", 3.2, new String[]{"Math", "Physics"}),
        Student.ofNameGpaCourses("Jim", 2.2, "Art"),
        Student.ofNameGpaCourses("William", 3.9, "Art", "Math"),
        Student.ofNameGpaCourses("Susan", 3.2, "Engineering", "Rocket Science"),
        Student.ofNameGpaCourses("Albert", 1.2, "Art", "History", "Journalism", "Political Science"),
        Student.ofNameGpaCourses("Sheila", 3.8, "Math", "Physics", "Chemistry")
    );

    school.stream()
        .filter(s -> s.getGpa() > 3)
        .peek(s -> System.out.println(">> " + s))
        .filter(s -> s.getName().charAt(0) > 'M')
//        .map(s -> s.getName())
        .flatMap(s -> s.getCourses().stream())
        .distinct()
        .forEach(s -> System.out.println("--" + s))
      ;
  }
}
