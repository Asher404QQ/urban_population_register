package ru.my_first_project.student_order.domain;

import ru.my_first_project.student_order.domain.office.RegisterOffice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class StudentOrder {
    private LocalDateTime studentOrderDate;
    private StudentOrderStatus studentOrderStatus;
    private static long count = 0;
    private Long studentOrderId;
    private Adult husband;
    private Adult wife;
    private List<Child> children;
    private RegisterOffice marriageOffice;
    private LocalDate marriageDate;
    private String marriageCertificateId;

    public StudentOrder(Adult husband, Adult wife, Child... children) {
        this.husband = husband;
        this.wife = wife;
        this.children = new ArrayList<>(Arrays.asList(children));
        studentOrderId = ++count;
    }

    public StudentOrder(){}


    public Adult getHusband() {
        return husband;
    }

    public void setHusband(Adult husband) {
        this.husband = husband;
    }

    public Adult getWife() {
        return wife;
    }

    public void setWife(Adult wife) {
        this.wife = wife;
    }

    public Long getStudentOrderId() {
        return studentOrderId;
    }

    public void setStudentOrderId(Long studentOrderId) {
        this.studentOrderId = studentOrderId;
    }

    public LocalDateTime getStudentOrderDate() {
        return studentOrderDate;
    }

    public void setStudentOrderDate(LocalDateTime studentOrderDate) {
        this.studentOrderDate = studentOrderDate;
    }

    public StudentOrderStatus getStudentOrderStatus() {
        return studentOrderStatus;
    }

    public void setStudentOrderStatus(StudentOrderStatus studentOrderStatus) {
        this.studentOrderStatus = studentOrderStatus;
    }

    public RegisterOffice getMarriageOffice() {
        return marriageOffice;
    }

    public void setMarriageOffice(RegisterOffice marriageOffice) {
        this.marriageOffice = marriageOffice;
    }

    public LocalDate getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(LocalDate marriageDate) {
        this.marriageDate = marriageDate;
    }

    public String getMarriageCertificateId() {
        return marriageCertificateId;
    }

    public void setMarriageCertificateId(String marriageCertificateId) {
        this.marriageCertificateId = marriageCertificateId;
    }

    public static long getCount() {
        return count;
    }

    public static void setCount(long count) {
        StudentOrder.count = count;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public void addChild(Child child) {
        if (children == null) {
            children = new ArrayList<>(5);
        }
        children.add(child);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentOrder that = (StudentOrder) o;
        return Objects.equals(studentOrderDate, that.studentOrderDate) && studentOrderStatus == that.studentOrderStatus && Objects.equals(studentOrderId, that.studentOrderId) && Objects.equals(husband, that.husband) && Objects.equals(wife, that.wife) && Objects.equals(children, that.children) && Objects.equals(marriageOffice, that.marriageOffice) && Objects.equals(marriageDate, that.marriageDate) && Objects.equals(marriageCertificateId, that.marriageCertificateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentOrderDate, studentOrderStatus, studentOrderId, husband, wife, children, marriageOffice, marriageDate, marriageCertificateId);
    }

    @Override
    public String toString() {
        return "StudentOrder{" +
                "studentOrderDate=" + studentOrderDate +
                ", studentOrderStatus=" + studentOrderStatus +
                ", studentOrderId=" + studentOrderId +
                ", husband=" + husband +
                ", wife=" + wife +
                ", children=" + children +
                ", marriageOffice=" + marriageOffice +
                ", marriageDate=" + marriageDate +
                ", marriageCertificateId='" + marriageCertificateId + '\'' +
                '}';
    }
}
