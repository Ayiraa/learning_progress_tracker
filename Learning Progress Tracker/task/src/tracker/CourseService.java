package tracker;

import java.util.List;
import java.util.Map;

import static tracker.Main.*;

public class CourseService {
    static void updateCoursesStats(int id, int javaPts, int dsPts, int databasePts, int springPts, Map<Integer,Student> students, List<Course> courses) {
        if(javaPts>0){
            Course Java = courses.get(0);
            Java.setActivity(Java.getActivity()+1);
            if(students.get(id).getJavaPts()==0) Java.setEnrolledStudents(Java.getEnrolledStudents()+1);
            Java.setTotalScore(Java.getTotalScore()+javaPts);
        }
        if(dsPts>0){
            Course DSA = courses.get(1);
            DSA.setActivity(DSA.getActivity()+1);
            if(students.get(id).getDSPts()==0) DSA.setEnrolledStudents(DSA.getEnrolledStudents()+1);
            DSA.setTotalScore(DSA.getTotalScore()+dsPts);
        }
        if(databasePts>0){
            Course Databases = courses.get(2);
            Databases.setActivity(Databases.getActivity()+1);
            if(students.get(id).getDatabasePts()==0) Databases.setEnrolledStudents(Databases.getEnrolledStudents()+1);
            Databases.setTotalScore(Databases.getTotalScore()+databasePts);
        }
        if(springPts>0){
            Course Spring = courses.get(3);
            Spring.setActivity(Spring.getActivity()+1);
            if(students.get(id).getSpringPts()==0) Spring.setEnrolledStudents(Spring.getEnrolledStudents()+1);
            Spring.setTotalScore(Spring.getTotalScore()+springPts);
        }
    }

    protected static void listCoursesStats() {
        List<String> mostPopularCourses = courseStatistics.getMostPopularCourses();
        System.out.println("Most popular: " + String.join(", ", mostPopularCourses));

        List<String> leastPopularCourses = courseStatistics.getLeastPopularCourses();
        if(leastPopularCourses.equals(mostPopularCourses)){
            System.out.println("Least popular: n/a");
        }else{
            System.out.println("Least popular: " + String.join(", ", leastPopularCourses));
        }

        List<String> highestActivityCourses = courseStatistics.getHighestActivityCourses();
        System.out.println("Highest activity: " + String.join(", ", highestActivityCourses));

        List<String> lowestActivityCourses = courseStatistics.getLowestActivityCourses();

        if(lowestActivityCourses.equals(highestActivityCourses)){
            System.out.println("Lowest activity: n/a");
        }else{
            System.out.println("Lowest activity: " + String.join(", ", lowestActivityCourses));
        }


        List<String> easiestCourses = courseStatistics.getEasiestCourses();
        System.out.println("Easiest Course: " + String.join(", ", easiestCourses));

        List<String> hardestCourses = courseStatistics.getHardestCourses();
        System.out.println("Hardest course: " + String.join(", ", hardestCourses));

    }

    protected static void showCourseStats(List<Course> courses) {
        System.out.println("Type the name of a course to see details or 'back' to quit");
        boolean allCoursesEmpty = courses.stream().allMatch(course -> course.getEnrolledStudents() == 0);
        boolean isBack=false;
        String input;
        if (allCoursesEmpty) {
            System.out.println("""
                                Most popular: n/a\s
                                Least popular: n/a\s
                                Highest activity: n/a
                                Lowest activity: n/a
                                Easiest course: n/a
                                Hardest course: n/a\s""");
        } else {
            CourseService.listCoursesStats();
        }

        do{
            input=scanner.nextLine();
            if(input.equals("back")){
                isBack=true;
            }else{
                if(Validator.isCourseValid(input, courses)){
                    StudentService.printTopLearners(input,students,courses);
                }else{
                    System.out.println("Unknown course");
                }
            }
        }while(!isBack);
    }

}
