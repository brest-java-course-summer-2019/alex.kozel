package com.epam.brest.summer.courses2019.model;

/**
 * POJO Student for model.
 */
public class Student {
    /**
     * Student ID - Primary key
     */
    private Integer studentId;

    /**
     * Student Name
     */
    private String studentName;

    /**
     * Course Id - Foreigner key
     */
    private Integer courseId;


    public Student() {
    }

    /**
     * Constructor with parameters
     * @param studentName
     * @param courseId
     */

    public Student(String studentName, Integer courseId) {
        this.studentName = studentName;
        this.courseId = courseId;
    }

    /**
     *Returns <code>Integer</code> representation of this studentId.
     *
     *@return studentId Student Id.
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * Sets the course's identifier.
     *
     * @param studentId Student Id.
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * Returns <code>String</code> representation of this studentName.
     *
     * @return studentName Student Name.
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Sets the course's name.
     *
     * @param studentName Student Name.
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Returns <code>Integer</code> representation of this courseId.
     *
     * @return courseId course Id.
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * Sets the course's identifier.
     *
     * @param courseId Course Id.
     */
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