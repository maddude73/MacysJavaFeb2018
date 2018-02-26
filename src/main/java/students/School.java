package students;

import java.util.*;

interface StudentCriterion {
  boolean test(Student s);
}

class SmartCriterion implements StudentCriterion {
  @Override
  public boolean test(Student s) {
    return s.getGpa() > 3.0;
  }
}

class EnthusiamCriterion implements StudentCriterion {
  @Override
  public boolean test(Student s) {
    return s.getCourses().size() > 3;
  }
}


public class School {
  public static List<Student> getStudentsByCriterion(Iterable<Student> in, StudentCriterion crit) {
    List<Student> out = new ArrayList<>();
    for (Student s : in) {
      if (crit.test(s)) {
        out.add(s);
      }
    }
    return out;
  }
//  public static List<Student> getSmartStudents(Iterable<Student> in, double threshold) {
//    List<Student> out = new ArrayList<>();
//    for (Student s : in) {
//      if (s.getGpa() > threshold) {
//        out.add(s);
//      }
//    }
//    return out;
//  }

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
        Student.ofNameGpaCourses("Albert", 1.2, "Art", "History", "Journalism", "Political Science"),
        Student.ofNameGpaCourses("Sheila", 3.8, "Math","Physics", "Chemistry")
    );

    showAll(school);

    Comparator<Student> gradeOrder = new StudentGradeOrdering();
    Collections.sort(school, gradeOrder);
    showAll(school);

    showAll(getStudentsByCriterion(school, new SmartCriterion()));
    showAll(getStudentsByCriterion(school, new EnthusiamCriterion()));
    showAll(getStudentsByCriterion(school,
        (Student s) -> s.getGpa() < 3
        ));
    showAll(school);
  }
}
