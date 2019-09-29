package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.dao.CountStudentsOnCourseDao;
import com.epam.brest.summer.courses2019.dao.CourseDao;
import com.epam.brest.summer.courses2019.model.CountStudentsOnCourse;
import com.epam.brest.summer.courses2019.model.Course;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Student Service Interface implementation.
 */
@Service
@Component
@Transactional
public class CourseServiceImpl implements CourseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);

    private CourseDao courseDao;

    private CountStudentsOnCourseDao countStudentsOnCourseDao;

    public CourseServiceImpl(CourseDao dao, CountStudentsOnCourseDao countStudentsOnCourseDao) {
            this.courseDao = dao;
            this.countStudentsOnCourseDao = countStudentsOnCourseDao;
        }

        @Override
        public List<Course> findAll() {
            LOGGER.debug("Find all courses");
            return courseDao.findAll();
        }

        @Override
        public List<CountStudentsOnCourse> countStudentsOnCourse()
                throws DataAccessException{
        LOGGER.debug("Count students on course");
        return countStudentsOnCourseDao.countStudentsOnCourse();
        }

        @Override
        public Course findById(Integer id) {
        LOGGER.debug("findById({})", id);
        return courseDao.findById(id)
                    .orElseThrow(() -> new RuntimeException("Failed to get courses from DB"));
        }

        @Override
        public void update(Course course) {
            LOGGER.debug("update({})", course);
            courseDao.update(course);
        }

        @Override
        public void delete(int id) {
            LOGGER.debug("delete({})", id);
            courseDao.delete(id);
        }

        @Override
        public List<Course> filterCourseByDate(Date fromDate, Date toDate) throws DataAccessException {
        LOGGER.debug("filterCourseByDate({}, {})", fromDate, toDate);
        return courseDao.filterCourseByDate(fromDate, toDate);
    }

    @Override
    public Course add(Course course) {
        return courseDao.add(course);
    }
}
