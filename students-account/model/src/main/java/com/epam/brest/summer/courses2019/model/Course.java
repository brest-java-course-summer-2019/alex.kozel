package com.epam.brest.summer.courses2019.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * POJO Course for model.
 */
public class Course {

    /**
     * Course Id.
     */
    private Integer courseId;

    /**
     * Course Name.
     */
    private String courseName;

    /**
     * Data Course begin.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date courseDate;


    /**
     * Count of students on course
     */
    private Integer countOfStudentsOnCourse;

    /**
     * Constructor without arguments.
     */
    public Course() {
    }

    /**
     * Constructor with department name.
     *
     * @param courseName course name
     * @param courseDate course date
     */
    public Course(String courseName, Date courseDate) {
        this.courseDate = courseDate;
        this.courseName = courseName;
    }

    /**
     * Returns <code>Integer</code> representation of this courseId.
     *
     * @return courseId Course Id.
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * Sets the course's identifier.
     *
     * @param courseId Course Id.
     */
    public void setCourseId(final Integer courseId) {
        this.courseId = courseId;
    }

    /**
     * Returns <code>String</code> representation of this courseName.
     *
     * @return courseName Course Name.
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Sets the course's name.
     *
     * @param courseName Department Name.
     */
    public void setCourseName(final String courseName) {
        this.courseName = courseName;
    }

    /**
     * Returns <code>Date</code> representation of this courseDate.
     *
     * @return courseDate course Date.
     */
    public Date getCourseDate() {
        return courseDate;
    }

    /**
     * Sets the course's date.
     *
     * @param  courseDate Course date.
     */
    public void setCourseDate(final Date courseDate) {
        this.courseDate = courseDate;
    }

    /**
     * Get number of Students on a course
     *
     * @return countOfStudentsOnCourse count students on course
     */
    public Integer getCountOfStudentsOnCourse() {
        return countOfStudentsOnCourse;
    }

    public void setCountOfStudentsOnCourse(Integer countOfStudentsOnCourse) {
        this.countOfStudentsOnCourse = countOfStudentsOnCourse;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Course{"
                + "courseId=" + courseId
                + ", courseName='" + courseName
                + ", courseDate='" + courseDate
                + '}';
    }
}