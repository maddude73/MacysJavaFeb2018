package collections;

import students.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentGrades {
  public static String letterGrade(Student s) {
    double grade = s.getGpa();
    if (grade > 3.6) return "A";
    else if (grade > 3.1) return "B";
    else return "F";
  }
  public static void main(String[] args) {
    List<Student> school = Arrays.asList(
        Student.ofNameGpaCourses("Fred", 3.2, new String[]{"Math", "Physics"}),
        Student.ofNameGpaCourses("Jim", 2.2, "Art"),
        Student.ofNameGpaCourses("Albert", 1.2, "Art", "History", "Journalism", "Political Science"),
        Student.ofNameGpaCourses("Sheila", 3.8, "Math", "Physics", "Chemistry")
    );

//    Map<String, List<Student>> m = school.stream()
//        .collect(Collectors.groupingBy(s -> letterGrade(s)));
//
//    m.forEach((k, v) -> System.out.println("Students with grade " + k + " are " + v));

//    Map<String, List<String>> m = school.stream()
//        .collect(Collectors.groupingBy(s -> letterGrade(s), Collectors.mapping(s -> s.getName(), Collectors.toList())));
//    m.forEach((k, v) -> System.out.println("Students with grade " + k + " are " + v));

    Map<String, Long> m = school.stream()
        .collect(Collectors.groupingBy(s -> letterGrade(s), Collectors.counting()));
    m.forEach((k, v) -> System.out.println(v + " students have grade " + k));

  }
}
