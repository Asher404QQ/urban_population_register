package ru.my_first_project.student_order.domain;

public class University {
    private Long universityID;
    private String universityName;

    public University(Long universityID, String universityName) {
        this.universityID = universityID;
        this.universityName = universityName;
    }

    public Long getUniversityID() {
        return universityID;
    }

    public void setUniversityID(Long universityID) {
        this.universityID = universityID;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public String toString() {
        return "University{" +
                "universityID=" + universityID +
                ", universityName='" + universityName + '\'' +
                '}';
    }
}
