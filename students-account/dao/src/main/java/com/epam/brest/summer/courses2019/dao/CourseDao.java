package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Course;


import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Course DAO Interface.
 */
public interface CourseDao {

    /**
     * Persist new courses.
     *
     * @param course new course
     * @return new course object.
     */
    Course add(Course course);

    /**
     * Update course.
     *
     * @param course course
     */
    void update(Course course);

    /**
     * Delete course with specified id.
     *
     * @param courseId course id
     */
    void delete(Integer courseId);

    /**
     * Get courses.
     *
     * @return courses list.
     */
    List<Course> findAll();

    /**
     * Get Course By Id.
     *
     * @param courseId courseId
     *
     * @return Course
     */
    Optional<Course> findById(Integer courseId);

    List<Course> filterCourseByDate(Date fromDate, Date toDate);
}