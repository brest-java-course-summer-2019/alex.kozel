package com.epam.brest.summer.courses2019.model;

/**
 * POJO Student for model.
 */
public class Student {
    private Integer studentId;
    private String studentName;
    private Integer courseId;


    public Student() {
    }

    public Student(String studentName, Integer courseId) {
        this.studentName = studentName;
        this.courseId = courseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Student{"
                + "studentId= " + studentId
                + ", studentName='" + studentName + '\''
                + ", courseId= " + courseId
                + '}';
    }
}