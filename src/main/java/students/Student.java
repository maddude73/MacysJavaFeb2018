package students;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Student {
  private String name;
  private double gpa;
  private List<String> courses;

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", gpa=" + gpa +
        ", courses=" + courses +
        '}';
  }

  public String getName() {
    return name;
  }

//  public void setName(String name) {
//    this.name = name;
//  }
//
  public double getGpa() {
    return gpa;
  }

//  public void setGpa(double gpa) {
//    this.gpa = gpa;
//  }

  public List<String> getCourses() {
    return courses;
  }

  public static StudentCriterion getSmartnessCriterion(final double threshold) {
//    threshold++; // threshold must be "effectively final" because the object
//    containing the lambda behavior takes a *copy* of it!
    return s -> s.gpa > threshold;
  }
//  private static final StudentCriterion smartnessCriterion = s -> s.gpa > 3;
//  public static StudentCriterion getSmartnessCriterion() {
//    return smartnessCriterion;
//  }

//  public void setCourses(List<String> courses) {
//    this.courses = courses;
//  }

  private Student() {}

  public static Student ofNameGpaCourses(String name, double gpa, String ... courses) {
    if (name == null || gpa < 0 || gpa > 4.0) throw new IllegalArgumentException("BAD");
    Student self = new Student();
    self.name = name;
    self.gpa = gpa;
//    this.courses = Collections.unmodifiableList(Collections.checkedList(courses, String.class));
    // if caller's array is mutated, we're in trouble...
    self.courses = Arrays.asList(courses);
    return self;
  }
}
