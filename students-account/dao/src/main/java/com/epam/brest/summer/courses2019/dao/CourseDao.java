package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Course;


import java.util.List;
import java.util.Optional;

/**
 * Course DAO Interface.
 */
public interface CourseDao {

    /**
     * Persist new department.
     *
     * @param course new course
     * @return new department object.
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
     * @param courseId department id
     */
    void delete(Integer courseId);

    /**
     * Get courses.
     *
     * @return courses list.
     */
    List<Course> findAll();

    /**
     * Get Department By Id.
     *
     * @param courseId courseId
     * @return Course
     */
    Optional<Course> findById(Integer courseId);

}