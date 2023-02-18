package tracker;

import java.util.Objects;

public class Student {
    String email;
    String firstName;
    String lastName;

    int id;
    int javaPts;
    int DSPts;
    int DatabasePts;
    int SpringPts;

    boolean hasBeenNotified;

    public Student(int id, String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.javaPts=0;
        this.DatabasePts =0;
        this.DSPts=0;
        this.SpringPts=0;
        this.id=id;
        this.hasBeenNotified=false;
    }

    public boolean isHasBeenNotified() {
        return hasBeenNotified;
    }


    public void setHasBeenNotified(boolean hasBeenNotified) {
        this.hasBeenNotified = hasBeenNotified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getJavaPts() {
        return javaPts;
    }

    public void setJavaPts(int javaPts) {
        this.javaPts = javaPts;
    }

    public int getDSPts() {
        return DSPts;
    }

    public void setDSPts(int DSPts) {
        this.DSPts = DSPts;
    }

    public int getDatabasePts() {
        return DatabasePts;
    }

    public void setDatabasePts(int databasePts) {
        this.DatabasePts = databasePts;
    }

    public int getSpringPts() {
        return SpringPts;
    }

    public void setSpringPts(int springPts) {
        SpringPts = springPts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return email.equals(student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
