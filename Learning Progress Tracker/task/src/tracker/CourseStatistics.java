package tracker;

import java.util.*;
import java.util.stream.Collectors;

public class CourseStatistics {

    private final List<Course> courses;

    public CourseStatistics(List<Course> courses) {
        this.courses = courses;
    }

    public List<String> getMostPopularCourses() {
        List<String> mostPopular = new ArrayList<>();
        int maxEnrolledStudents = courses.stream()
                .mapToInt(Course::getEnrolledStudents)
                .max()
                .orElse(0);
        for (Course course : courses) {
            if (course.getEnrolledStudents() == maxEnrolledStudents) {
                mostPopular.add(course.getName());
            }
        }
        return mostPopular;
    }

    public List<String> getLeastPopularCourses() {
        List<String> leastPopular = new ArrayList<>();
        int minEnrolledStudents = courses.stream()
                .mapToInt(Course::getEnrolledStudents)
                .min()
                .orElse(0);
        for (Course course : courses) {
            if (course.getEnrolledStudents() == minEnrolledStudents) {
                leastPopular.add(course.getName());
            }
        }
        return leastPopular;
    }

    public List<String> getHighestActivityCourses() {
        List<String> highestActivity = new ArrayList<>();
        int maxActivity = courses.stream()
                .mapToInt(Course::getActivity)
                .max()
                .orElse(0);
        for (Course course : courses) {
            if (course.getActivity() == maxActivity) {
                highestActivity.add(course.getName());
            }
        }
        return highestActivity;
    }

    public List<String> getLowestActivityCourses() {
        List<String> lowestActivity = new ArrayList<>();
        int minActivity = courses.stream()
                .mapToInt(Course::getActivity)
                .min()
                .orElse(0);
        for (Course course : courses) {
            if (course.getActivity() == minActivity) {
                lowestActivity.add(course.getName());
            }
        }
        return lowestActivity;
    }

    public List<String> getEasiestCourses() {
        List<String> easiestCourses = new ArrayList<>();
        double maxAverageScore = courses.stream()
                .mapToDouble(Course::getAverageScore)
                .max()
                .orElse(0.0);
        for (Course course : courses) {
            if (course.getAverageScore() == maxAverageScore) {
                easiestCourses.add(course.getName());
            }
        }
        return easiestCourses;
    }

    public List<String> getHardestCourses() {
        // Create a list of courses with non-zero activity
        List<Course> coursesWithActivity = courses.stream()
                .filter(course -> course.getActivity() > 0).sorted(Comparator.comparingDouble(Course::getAverageScore)).toList();

        // Sort the courses by their average grade per assignment in ascending order

        // Find the course with the lowest average grade per assignment
        double lowestAverageScore = coursesWithActivity.get(0).getAverageScore();

        return coursesWithActivity.stream()
                .filter(course -> course.getAverageScore() == lowestAverageScore)
                .map(Course::getName)
                .collect(Collectors.toList());

    }
}
