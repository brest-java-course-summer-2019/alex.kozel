package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
public class CourseDaoJdbcImplTest {

    private static final String COURSE = "Math";
    private static final String COURSEFORID = "DEV";
    private static final String NEW_COURSE = "Education";
    private Integer courseId = 1;

    @Autowired
    CourseDao courseDao;

    @Test
    public void findAll() throws DataAccessException {
        List<Course> courses = courseDao.findAll();
        assertNotNull(courses);
        assertTrue(courses.size() > 0);
    }

    @Test
    public void getCourseById() {
        Course course = courseDao.findById(courseId).get();
        assertNotNull(course);
        assertEquals(course.getCourseId(), courseId);
        assertEquals(course.getCourseName(), COURSEFORID);
    }

    @Test
    public void addCourse() {
        Course testCourse = new Course();
        testCourse.setCourseName(COURSE);
        Course newCourse = courseDao.add(testCourse);
        assertNotNull(newCourse.getCourseId());
    }

    @Test
    public void updateCourse() {
        Course newCourse = new Course(COURSE);
        newCourse = courseDao.add(newCourse);
        newCourse.setCourseName(NEW_COURSE);
        courseDao.update(newCourse);
        Course updatedCourse = courseDao.findById(newCourse.getCourseId()).get();
        assertEquals(newCourse.getCourseId(), updatedCourse.getCourseId());
        assertEquals(newCourse.getCourseName(), updatedCourse.getCourseName());
    }

    @Test
    public void deleteCourse() {
        Course course = new Course(COURSE);
        course = courseDao.add(course);
        List<Course> courses = courseDao.findAll();
        int sizeBefore = courses.size();
        courseDao.delete(course.getCourseId());
        assertTrue((sizeBefore - 1) == courseDao.findAll().size());
    }
}