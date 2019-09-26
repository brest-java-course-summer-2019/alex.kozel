package com.epam.brest.summer.courses2019.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * POJO Department for model.
 */
public class CountStudentsOnCourse extends Course{

    private Integer courseId;

    private String courseName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date courseDate;

    private Integer countOfStudents;

    /**
     * Constructor without arguments.
     */
    public CountStudentsOnCourse() {
    }

    /**
     * Constructor with department name.
     * @param courseName course name
     */
    public CountStudentsOnCourse(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Returns <code>Integer</code> representation of this courseId.
     * @return courseId Course Id.
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * Sets the course's identifier.
     * @param courseId course Id.
     */
    public void setCourseId(final Integer courseId) {
        this.courseId = courseId;
    }

    /**
     * Returns <code>String</code> representation of this courseName.
     * @return courseName Course Name.
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Sets the course's name.
     * @param courseName Course Name.
     */
    public void setCourseName(final String courseName) {
        this.courseName = courseName;
    }

    /**
     * Get Data of Course
     *
     * @return course date
     */
    @Override
    public Date getCourseDate() {
        return courseDate;
    }

    @Override
    public void setCourseDate(Date courseDate) {
        this.courseDate = courseDate;
    }


    /**
     * Returns <code>Integer</code> representation of count of students
     * in a course.
     *
     * @return studentSum.
     */
    public Integer getCountOfStudents() {
        return countOfStudents;
    }

    /**
     * Sets the course's count of students.
     *
     * @param countOfStudents sum of students.
     */
    public void setCountOfStudents(final Integer countOfStudents) {
        this.countOfStudents = countOfStudents;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "CountStudentsOnCourse{"
                + "CourseId=" + courseId
                + ", courseName='" + courseName + '\''
                + ", courseDate='" + courseDate + '\''
                + ", countOfStudents=" + countOfStudents
                + '}';
    }
}