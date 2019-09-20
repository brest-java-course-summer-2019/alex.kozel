package com.epam.brest.summer.courses2019.model;

/**
 * POJO Department for model.
 */
public class CourseAgregate {

    private Integer courseId;

    private String courseName;

    private Integer studentSum;

    /**
     * Constructor without arguments.
     */
    public CourseAgregate() {
    }

    /**
     * Constructor with department name.
     * @param courseName course name
     */
    public CourseAgregate(String courseName) {
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
     * Returns <code>Integer</code> representation of count of students
     * in a course.
     *
     * @return studentSum.
     */
    public Integer getStudentSum() {
        return studentSum;
    }

    /**
     * Sets the course's count of students.
     *
     * @param studentSum Average salary.
     */
    public void setStudentSum(final Integer studentSum) {
        this.studentSum = studentSum;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "CourseAgregate{"
                + "CourseId=" + courseId
                + ", courseName='" + courseName + '\''
                + ", studentSum=" + studentSum
                + '}';
    }
}