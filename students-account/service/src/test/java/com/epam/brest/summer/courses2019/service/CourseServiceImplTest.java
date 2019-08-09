package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.model.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml"})
public class CourseServiceImplTest {

    @Autowired
    private CourseService courseService;

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
        int id = 2;
        Course course = create();
        course.setCourseId(id);
        courseService.update(course);
        course = courseService.findById(id);

        assertNotNull(course);
        assertEquals("anotherCourse", course.getCourseName());
    }

    @Test
    void delete() {
        int id = 3;
        courseService.delete(id);
        assertThrows(RuntimeException.class, () -> courseService.findById(id));
    }

    @Test
    void add() {
        long count = courseService.findAll().size();
        assertThrows(DuplicateKeyException.class, () -> courseService.add(create(), create()));
        long newCount = courseService.findAll().size();
        assertEquals(count, newCount);
    }

    private Course create() {
        Course course = new Course();
        course.setCourseName("anotherCourse");
        return course;
    }
}


