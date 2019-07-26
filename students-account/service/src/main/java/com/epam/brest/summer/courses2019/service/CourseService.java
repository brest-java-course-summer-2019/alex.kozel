package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.model.Course;


public interface CourseService {

    /**
     * Find all Courses stream.
     *
     * @return courses .
     */
    List<Course> findAll();

    /**
     * Find Department By Id.
     *
     * @param id id
     * @return Course
     */
    Course findById(Integer id);

    /**
     * Update department.
     *
     * @param course course
     */
    void update(Course course);

    /**
     * Delete Course.
     *
     * @param id course id
     */
    void delete(int id);
}