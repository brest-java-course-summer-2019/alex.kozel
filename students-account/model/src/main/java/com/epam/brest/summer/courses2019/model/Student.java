package com.epam.brest.summer.courses2019.model;

import java.util.Date;

/**
 * POJO Student for model.
 */
public class Student {

    private Integer studentId;

    private String studentName;

    private Integer courseId;

    private Date dateCourseBegin;

    public Student() {
    }

    public Student(String studentName, Date dateCourseBegin, Integer courseId) {
        this.studentName = studentName;
        this.courseId = courseId;
        this.dateCourseBegin = dateCourseBegin;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer employeeId) {
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
                + "studentId=" + studentId
                + ", studentName='" + studentName + '\''
                + ", courseId=" + courseId
                + '}';
    }
}