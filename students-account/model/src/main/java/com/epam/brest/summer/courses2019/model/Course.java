package com.epam.brest.summer.courses2019.model;

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
     * Constructor without arguments.
     */
    public Course() {
    }

    /**
     * Constructor with department name.
     *
     * @param courseName course name
     */
    public Course(String courseName) {
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
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Course{"
                + "courseId=" + courseId
                + ", departmentName='" + courseName
                + '}';
    }
}