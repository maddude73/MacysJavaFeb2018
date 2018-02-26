package students;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class School {
  public static void showAll(Iterable<Student> ls) {
    for (Student s : ls) {
      System.out.println("> " + s);
    }
    System.out.println("---------------------------");
  }

  public static void main(String[] args) {
    List<Student> school = Arrays.asList(
        Student.ofNameGpaCourses("Fred", 3.2, new String[]{"Math","Physics"}),
        Student.ofNameGpaCourses("Jim", 2.2, "Art"),
        Student.ofNameGpaCourses("Sheila", 3.8, "Math","Physics", "Chemistry")
    );

    showAll(school);

    Comparator<Student> gradeOrder = new StudentGradeOrdering();
    Collections.sort(school, gradeOrder);
    showAll(school);
  }
}
