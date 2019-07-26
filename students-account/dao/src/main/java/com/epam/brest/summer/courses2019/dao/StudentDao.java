package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Student;

import java.util.List;
import java.util.Optional;

/**
 * Student DAO Interface.
 */
public interface StudentDao {

    /**
     * Get all students.
     *
     * @return list of all students
     */
    List<Student> findAll();

    /**
     * Get all student with specified course id.
     *
     * @param courseId course id
     * @return list of employees by department id
     */
    List<Student> findByCourseId(Integer courseId);

    /**
     * Get student with specified id.
     *
     * @param studentId student id
     * @return student by id
     */
    Optional<Student> findById(Integer studentId);

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
     * @param studentId course id
     */
    void delete(Integer studentId);

}