package tracker;

public class Course {

    String name;
    int enrolledStudents;

    int activity;
    int totalScore;

    double averageScore;

    final int MAX_SCORE;


    public Course(String name, int MAX_SCORE) {
        this.name = name;
        this.enrolledStudents=0;
        this.activity=0;
        this.totalScore =0;
        this.MAX_SCORE=MAX_SCORE;
    }

    public int getMAX_SCORE() {
        return MAX_SCORE;
    }

    public String getName() {
        return name;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(int enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
        this.updateAverageScore();
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
        this.updateAverageScore();
    }

    public double getAverageScore() {
        return averageScore;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", enrolledStudents=" + enrolledStudents +
                ", activity=" + activity +
                ", totalScore=" + totalScore +
                ", averageScore=" + averageScore +
                '}';
    }

    private void updateAverageScore() {
        if (activity == 0) {
            averageScore = 0;
        } else {
            averageScore = totalScore / (double) activity;
        }
    }
}
