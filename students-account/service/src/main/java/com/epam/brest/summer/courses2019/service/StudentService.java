package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.model.CourseAgregate;
import com.epam.brest.summer.courses2019.model.Student;

import java.util.List;

/**
 * Student service interface.
 */
public interface StudentService {

    /**
     * Get all students.
     *
     * @return list of all students
     */
    List<Student> findAll();

    /**
     * Get all students with specified course id.
     *
     * @param courseId course id
     * @return list of students by course id
     */
    List<CourseAgregate> findByCourseId(Integer courseId);

    /**
     * Get student with specified id.
     *
     * @param studentId student id
     * @return student by id
     */
    Student findById(Integer studentId);

    /**
     * Persist new student.
     *
     * @param student student
     * @return student
     */
    Student add(Student student);

    /**
     * Update student.
     *
     * @param student student
     */
    void update(Student student);

    /**
     * Delete student with specified id.
     *
     * @param studentId student id
     */
    void delete(Integer studentId);

}
