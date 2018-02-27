package students;

import java.util.*;

interface Criterion<E> {
  boolean test(E s);
}

public class School2 {
  public static <E> List<E> getByCriterion(Iterable<E> in, Criterion<E> crit) {
    List<E> out = new ArrayList<>();
    for (E s : in) {
      if (crit.test(s)) {
        out.add(s);
      }
    }
    return out;
  }

  public static <E> void showAll(Iterable<E> ls) {
    for (E s : ls) {
      System.out.println("> " + s);
    }
    System.out.println("---------------------------");
  }

  public static void main(String[] args) {
    List<Student> school = Arrays.asList(
        Student.ofNameGpaCourses("Fred", 3.2, new String[]{"Math", "Physics"}),
        Student.ofNameGpaCourses("Jim", 2.2, "Art"),
        Student.ofNameGpaCourses("Albert", 1.2, "Art", "History", "Journalism", "Political Science"),
        Student.ofNameGpaCourses("Sheila", 3.8, "Math", "Physics", "Chemistry")
    );

    showAll(school);

    showAll(getByCriterion(school, s -> s.getGpa() < 3));

    List<String> ls = Arrays.asList("Fred", "Jim", "Sheila", "van der Valk");
    showAll(getByCriterion(ls, s -> s.length() > 4));
    showAll(getByCriterion(ls, s -> Character.isLowerCase(s.charAt(0))));
  }
}
