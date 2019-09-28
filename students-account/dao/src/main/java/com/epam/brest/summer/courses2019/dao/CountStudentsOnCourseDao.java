package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.CountStudentsOnCourse;

import java.util.List;

public interface CountStudentsOnCourseDao {
    /**
     * Get all courses with count of students in there.
     *
     * @return courses list.
     */
    List<CountStudentsOnCourse> countStudentsOnCourse();
}
