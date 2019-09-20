package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.CourseAgregate;

import java.util.List;

public interface CourseAgregateDao {
    /**
     * Get all courses with count of students in there.
     *
     * @return courses list.
     */
    List<CourseAgregate> findAllWithCountOfStudents();
}
