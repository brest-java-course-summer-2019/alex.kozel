package com.epam.brest.summer.courses2019.service;


import com.epam.brest.summer.courses2019.model.Course;

import java.util.List;

public interface CourseService {

    /**
     * Find all Courses stream.
     *
     * @return courses .
     */
    List<Course> findAll();

    /**
     * Find course By Id.
     *
     * @param id id
     * @return Course
     */
    Course findById(Integer id);

    /**
     * Update course.
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

    void add(Course... courses);

    Course add(Course course);
}