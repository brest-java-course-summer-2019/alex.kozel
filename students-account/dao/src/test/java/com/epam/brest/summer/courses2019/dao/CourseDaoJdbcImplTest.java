package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.CountStudentsOnCourse;
import com.epam.brest.summer.courses2019.model.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
@Rollback
public class CourseDaoJdbcImplTest {

    private static final String COURSE = "Math";
    private static final String COURSEFORID = "DEV";
    private static final String NEW_COURSE = "Education";
    private static final Integer COURSEID = 1;
    private static final String DATE = "1970-02-02";
    private static final String FROM_DATE = "1970-01-02";
    private static final String TO_DATE = "1970-03-02";
    private SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    private  Date date = formatDate.parse(DATE);


    @Autowired
    CourseDao courseDao;

    @Autowired
    CountStudentsOnCourseDao countStudentsOnCourseDao;

    public CourseDaoJdbcImplTest() throws ParseException {
    }

    @Test
    public void findAll() throws DataAccessException {
        List<Course> courses = courseDao.findAll();
        assertNotNull(courses);
        assertTrue(courses.size() > 0);
    }

    @Test
    public void countStudentsOnCourse() {
        List<CountStudentsOnCourse> courses = countStudentsOnCourseDao.countStudentsOnCourse();
        assertNotNull(courses);
        assertTrue(courses.size() > 0);
        assertTrue(courses.get(0).getCountOfStudents().intValue() > 0);
    }

    @Test
    public void getCourseById() {
        Course course = courseDao.findById(COURSEID).get();
        assertNotNull(course);
        assertEquals(course.getCourseId(), COURSEID);
        assertEquals(course.getCourseName(), COURSEFORID);
    }

    @Test
    public void addCourse() {
        int sizeBefore = courseDao.findAll().size();
        Course testCourse = new Course(COURSE, date);
        Course newCourse = courseDao.add(testCourse);
        int sizeAfter = courseDao.findAll().size();
        assertNotNull(newCourse.getCourseId());
        assertEquals(sizeBefore +1, sizeAfter);
    }

    @Test
    public void updateCourse() {
        Course newCourse = new Course(COURSE, date);
        newCourse = courseDao.add(newCourse);
        newCourse.setCourseName(NEW_COURSE);
        courseDao.update(newCourse);
        Course updatedCourse = courseDao.findById(newCourse.getCourseId()).get();
        assertEquals(newCourse.getCourseId(), updatedCourse.getCourseId());
        assertEquals(newCourse.getCourseName(), updatedCourse.getCourseName());
    }

    @Test
    public void deleteCourse() {
        Course course = new Course(COURSE, date);
        course = courseDao.add(course);
        List<Course> courses = courseDao.findAll();
        int sizeBefore = courses.size();
        courseDao.delete(course.getCourseId());
        assertTrue((sizeBefore - 1) == courseDao.findAll().size());
    }
}