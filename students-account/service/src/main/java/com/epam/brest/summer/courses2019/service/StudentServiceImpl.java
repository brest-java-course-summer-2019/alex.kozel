package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.model.CourseAgregate;
import com.epam.brest.summer.courses2019.model.Student;
import com.epam.brest.summer.courses2019.dao.StudentDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Student Service Interface implementation.
 */
@Component
public class StudentServiceImpl implements StudentService {


    private static final Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);

    private StudentDao studentDao;

    private CourseAgregate courseAgregate;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> findAll() {
        LOGGER.debug("Find all employees");
        return studentDao.findAll();
    }

    @Override
    public List<CourseAgregate> findByCourseId(Integer courseId) {
        LOGGER.debug("findByCourseId({})", courseId);
        return courseAgregate.findByCourseId(courseId);
    }

    @Override
    public Student findById(Integer studentId) {
        LOGGER.debug("findById({})", studentId);
        return studentDao.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Failed to get student from DB"));
    }

    @Override
    public Student add(Student student) {
        LOGGER.debug("add({})", student);
        return studentDao.add(student);
    }

    @Override
    public void update(Student student) {
        LOGGER.debug("update({})", student);
        studentDao.update(student);
    }

    @Override
    public void delete(Integer studetnId) {
        LOGGER.debug("delete({})", studetnId);
        studentDao.delete(studetnId);
    }
}
