package tracker;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class Notification {
    private static final String PATTERN = "To: {0}{1}Re: Your Learning Progress{1}Hello, {2} {3}! You have accomplished our {4} course!{1}";
    private final String message;

    public Notification(Student student, Course course){
        message = MessageFormat.format(PATTERN, student.getEmail(), System.lineSeparator(),
                student.getFirstName(), student.getLastName(), course.getName());
    }

    @Override
    public String toString() {
        return message;
    }
    public static void notifyStudents(Map<Integer,Student> students, List<Course> courses) {
        int studentsNotified = 0;

        for (var student : students.entrySet()) {
            if (!student.getValue().hasBeenNotified) {
                if (student.getValue().getJavaPts() >= courses.get(0).getMAX_SCORE()) {
                    System.out.print(new Notification(student.getValue(), courses.get(0)));
                    if (!student.getValue().isHasBeenNotified()) studentsNotified++;
                    student.getValue().setHasBeenNotified(true);

                }
                if (student.getValue().getDSPts() >= courses.get(1).getMAX_SCORE()) {
                    System.out.print(new Notification(student.getValue(), courses.get(1)));
                    if (!student.getValue().isHasBeenNotified()) studentsNotified++;
                    student.getValue().setHasBeenNotified(true);

                }
                if (student.getValue().getDatabasePts() >= courses.get(2).getMAX_SCORE()) {
                    System.out.print(new Notification(student.getValue(), courses.get(2)));
                    if (!student.getValue().isHasBeenNotified()) studentsNotified++;
                    student.getValue().setHasBeenNotified(true);

                }
                if (student.getValue().getSpringPts() >= courses.get(3).getMAX_SCORE()) {
                    System.out.print(new Notification(student.getValue(), courses.get(3)));
                    if (!student.getValue().isHasBeenNotified()) studentsNotified++;
                    student.getValue().setHasBeenNotified(true);

                }

            }

        }
        System.out.println("Total " + studentsNotified + " students have been notified.");
    }
}