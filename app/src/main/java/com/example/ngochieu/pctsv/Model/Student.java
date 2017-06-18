package com.example.ngochieu.pctsv.Model;

/**
 * Created by NgocHieu on 5/16/2017.
 */

public class Student {
    private int idStudent;
    private String fullName;
    private String studentNumber;
    private Boolean male;
    private String birthday;
    private String facultyName;
    private String courseName;
    private String className;
    private String phoneNumber;
    private String homePhone;
    private String email;

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Boolean getMale() {
        return male;
    }

    public void setMale(Boolean male) {
        this.male = male;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "fullName='" + fullName + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", male=" + male +
                ", birthday='" + birthday + '\'' +
                ", facultyName='" + facultyName + '\'' +
                ", courseName='" + courseName + '\'' +
                ", className='" + className + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
