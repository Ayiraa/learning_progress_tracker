package tracker;

import java.util.List;

public class Validator {
    public static boolean isValidFirstName(String firstName) {
        if (firstName.matches("^[a-zA-Z]([a-zA-Z]|[-'](?=[^-']))*[a-zA-Z]$")) {
            return true;
        } else {
            System.out.println("Incorrect first name.");
            return false;
        }
    }

    public static boolean isValidLastName(String lastName) {
        if (lastName.matches("[a-zA-Z]([a-zA-Z]|[-' ](?=[^-' ]))*[a-zA-Z]$")) {
            return true;
        } else {
            System.out.println("Incorrect last name.");
            return false;
        }
    }

    public static boolean isValidEmail(String email) {
        if (email != null && email.matches("\\w+(?:[-.]*\\w*)*@\\w+\\.\\w+")) {
            return true;
        } else {
            System.out.println("Incorrect email.");
            return false;
        }
    }

    public static boolean isValidCredentials(String[] details) {
        if (details.length > 2) {
            return true;
        } else {
            System.out.println("Incorrect credentials.");
            return false;
        }
    }

    protected static boolean isPointsValid(String points) {
        return  points.matches("^(?:[0-9]+\\s+){3}[0-9]+\\s*?$");
    }

    public static boolean isCourseValid(String courseName, List<Course> courses) {
        return courses.stream().anyMatch(course -> course.getName().equals(courseName));
    }

}
