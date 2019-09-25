package com.epam.brest.summer.courses2019.service;


import com.epam.brest.summer.courses2019.model.CountStudentsOnCourse;
import com.epam.brest.summer.courses2019.model.Course;

import java.util.Date;
import java.util.List;

public interface CourseService {

    /**
     * Find all Courses stream.
     *
     * @return courses .
     */
    List<Course> findAll();

    /**
     * Get number of students on course
     *
     * @return students list
     */
    List<CountStudentsOnCourse> countStudentsOnCourse();

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

    /**
     * Filter courses by date
     *
     * @param fromDate
     * @param toDate
     *
     * @return list of filtered courses
     */
    List<Course> filterCourseByDate(Date fromDate, Date toDate);

    Course add(Course course);
}