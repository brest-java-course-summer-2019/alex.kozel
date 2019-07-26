package com.epam.brest.summer.courses2019.model;

import java.util.Date;

/**
 * POJO Student for model.
 */
public class Student {

    private Integer studentId;

    private String studentName;

    private Integer courseId;

    private Date date;

    public Student() {
    }

    public Student(String studentName, Date date, Integer courseId) {
        this.studentName = studentName;
        this.courseId = courseId;
        this.date = date;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer employeeId) {
        this.studentId = studentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
                + ", Date='" + date + '\''
                + ", courseId=" + courseId
                + '}';
    }
}