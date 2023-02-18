package tracker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static tracker.Main.scanner;

public class StudentService {
    public static int addStudents(Map<Integer,Student> students, int nextId) {
        int studentsAdded = 0;
        boolean isBack = false;
        String input;
        System.out.println("Enter student credentials or 'back' to return:");
        do {
            input = scanner.nextLine();
            if (input.equals("back")) {
                isBack = true;
            } else {
                if (Validator.isValidCredentials(input.split(" "))) {
                    int firstIndex = input.indexOf(" ");
                    int lastIndex = input.lastIndexOf(" ");
                    String firstName = input.substring(0, firstIndex).trim();
                    String lastName = input.substring(firstIndex, lastIndex).trim();
                    String email = input.substring(lastIndex).trim();

                    if (Validator.isValidFirstName(firstName) && Validator.isValidLastName(lastName) && Validator.isValidEmail(email) && !isEmailTaken(email, students)) {
                        students.put(nextId, new Student(nextId, email,firstName,lastName));
                        nextId++;
                        System.out.println("The student has been added.");
                        studentsAdded++;
                    }
                }
            }
        } while (!isBack);
        return studentsAdded;
    }
    private static boolean isEmailTaken(String email, Map<Integer,Student> students) {
        if (students.values().stream()
                .anyMatch(student -> student.getEmail().equals(email))){
            System.out.println("This email is already taken");
            return true;
        }else{
            return false;
        }
    }

    protected static void printStudentDetails(Map<Integer, Student> students) {
        System.out.println("Enter an id and points or 'back to return:");

        String input;
        do{
            input=scanner.nextLine();
            if(input.equals("back")) break;
            int id = Integer.parseInt(input);
            if(students.containsKey(id)){
                Student student=students.get(id);
                System.out.println(id + " points: Java=" +student.getJavaPts()+
                        "; DSA=" + student.getDSPts() + "; Databases="+student.getDatabasePts() +
                        "; Spring="+ student.getSpringPts());
            }else{
                System.out.println("No student is found for id=" + id);
            }

        }while(true);

    }

    protected static void addPoints(Map<Integer, Student> students, List<Course> courses){
        System.out.println("Enter an id and points or 'back to return:");
        String input;
        do{
            input=scanner.nextLine();
            if (input.equals("back")) break;
            if(Validator.isPointsValid(input)){

                String[] textPoints=input.split(" ");
                int[] points = new int[4];
                int id=0;
                for (int i = 1; i < 5; i++) {
                    points[i-1] = Integer.parseInt(textPoints[i]);
                }
                try {
                    id = Integer.parseInt(textPoints[0]);

                } catch (NumberFormatException e) {
                    System.out.println("No student is found for id=" +textPoints[0]);
                }

                if(students.containsKey(id)){
                    Student student = students.get(id);
                    CourseService.updateCoursesStats(id,points[0], points[1],points[2], points[3], students, courses);
                    student.setJavaPts(student.getJavaPts()+points[0]);
                    student.setDSPts(student.getDSPts()+points[1]);
                    student.setDatabasePts(student.getDatabasePts()+points[2]);
                    student.setSpringPts(student.getSpringPts()+points[3]);

                    System.out.println("Points updated");

                }else{
                    System.out.println("No student is found for id=" + id);
                }


            }else{
                System.out.println("Incorrect points format");
            }

        }while(true);
    }
    public static void printTopLearners(String courseName, Map<Integer,Student> students, List<Course> courses) {
        System.out.println(courseName);
        System.out.println("id    points completed");
        Map<Integer, BigDecimal> topLearners = new HashMap<>();
        int coursePts = 0;
        int maxScore = 1;
        for (var entry : students.entrySet()) {
            Student student = entry.getValue();

            switch (courseName) {
                case "Java" -> {
                    coursePts = student.getJavaPts();
                    maxScore = courses.get(0).getMAX_SCORE();
                }
                case "DSA" -> {
                    coursePts = student.getDSPts();
                    maxScore = courses.get(1).getMAX_SCORE();
                }
                case "Databases" -> {
                    coursePts = student.getDatabasePts();
                    maxScore = courses.get(2).getMAX_SCORE();
                }
                case "Spring" -> {
                    coursePts = student.getSpringPts();
                    maxScore =  courses.get(3).getMAX_SCORE();
                }
            }

            if (coursePts > 0) {
                BigDecimal progress = new BigDecimal((double) coursePts / maxScore * 100.0);
                progress = progress.setScale(1, RoundingMode.HALF_UP);
                topLearners.put(student.getId(),progress);
            }

        }

        List<Map.Entry<Integer, BigDecimal>> entries = new ArrayList<>(topLearners.entrySet());
        entries.sort(Map.Entry.<Integer, BigDecimal>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()));

        for (Map.Entry<Integer, BigDecimal> entr : entries) {
            BigDecimal progress = entr.getValue();
            int id = entr.getKey();
            int points = getCoursePoints(courseName, id,students);

            System.out.println(id + " " + points + " " + progress + "%");
        }
    }
    private static int getCoursePoints(String courseName, int studentId, Map<Integer,Student> students) {
        return switch (courseName) {
            case "Java" -> students.get(studentId).getJavaPts();
            case "DSA" -> students.get(studentId).getDSPts();
            case "Databases" -> students.get(studentId).getDatabasePts();
            case "Spring" -> students.get(studentId).getSpringPts();
            default -> throw new IllegalArgumentException("Invalid course name: " + courseName);
        };
    }
}
