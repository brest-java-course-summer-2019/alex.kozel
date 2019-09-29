package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.model.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:dao-context.xml", "classpath*:test-db.xml", "classpath*:test-service.xml"})
@Rollback
public class CourseServiceImplTest  {

    private SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    private static final String FROM_DATE = "1970-01-11";
    private static final String TO_DATE = "2019-07-22";
    Date date = formatDate.parse("1991-02-02");


    @Autowired
    private CourseService courseService;

    public CourseServiceImplTest() throws ParseException {
    }


    @Test
    void findAll() {
        List<Course> courses = courseService.findAll();

        assertNotNull(courses);
        assertFalse(courses.isEmpty());
    }

    @Test
    void findById() {
        int id = 1;
        Course course = courseService.findById(id);

        assertNotNull(course);
        assertEquals("DEV", course.getCourseName());
    }

    @Test
    void update() {
        Course course = courseService.findById(2);
        course.setCourseName("anotherCourse");
        assertNotNull(course);
        assertEquals("anotherCourse", course.getCourseName());
    }

    @Test
    void add() {
        long count = courseService.findAll().size();
        Course course = new Course("anotherCourse", date);
        long newCount = courseService.findAll().size();
        assertEquals(count, newCount);
    }

    @Test
    void delete() throws ParseException{
        Date date = formatDate.parse("1991-02-02");
        Course course = new Course("Neo", date);
        courseService.add(course);
        List<Course> courses = courseService.findAll();
        int sizeBefore = courses.size();
        courseService.delete(course.getCourseId());
        assertTrue((sizeBefore - 1) == courseService.findAll().size());
    }

    @Test
    public void testFilterDeviceByDates() throws ParseException {
        Date fromDate = formatDate.parse(FROM_DATE);
        Date toDate = formatDate.parse(TO_DATE);
        List<Course> courses = courseService.filterCourseByDate(fromDate, toDate);
        assertNotNull(courses);
        assertTrue(courses.size() > 1);
    }

    private Course create() {
        Course course = new Course();
        course.setCourseName("anotherCourse");
        return course;
    }
}


