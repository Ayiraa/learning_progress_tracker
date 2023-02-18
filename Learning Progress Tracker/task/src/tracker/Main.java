package tracker;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int nextId = 1000;
    static Map<Integer, Student> students = new LinkedHashMap<>();
    static List<Course> courses = new ArrayList<>();
    static CourseStatistics courseStatistics = new CourseStatistics(courses);


    public static void main(String[] args) {
        courses.add(new Course("Java", 600));
        courses.add(new Course("DSA", 400));
        courses.add(new Course("Databases", 480));
        courses.add(new Course("Spring", 550));

        String input = "";
        System.out.println("Learning Progress Tracker");
        label:
        while (!input.equals("exit")) {
            input = scanner.nextLine();
            if (input.isBlank()) {
                System.out.println("No input");
                continue;
            }
            switch (input) {
                case "exit":
                    System.out.println("Bye!");
                    break label;
                case "add students":
                    System.out.println("Total " + StudentService.addStudents(students, nextId) + " students added.");
                    break;
                case "back":
                    System.out.println("Enter 'exit' to exit the program");
                    break;
                case "list":
                    if (students.size() == 0) {
                        System.out.println("No students found");
                    } else {
                        System.out.println("Students:");
                        students.keySet().forEach(System.out::println);
                    }
                    break;
                case "add points":
                    StudentService.addPoints(students, courses);
                    break;
                case "find":
                    StudentService.printStudentDetails(students);
                    break;
                case "statistics":
                    CourseService.showCourseStats(courses);
                    break;
                case "notify":
                    Notification.notifyStudents(students,courses);
                    break;
                default:
                    System.out.println("Unknown Command!");
                    break;
            }
        }
    }

}
