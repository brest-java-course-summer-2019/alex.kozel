package com.epam.brest.summer.courses2019.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class CourseTest {

    Course course = new Course();

    Integer courseId = 2;
    String courseName = "Math";
    Date date = new Date();


    @Test
    public void getCourseId(){
        course.setCourseId(courseId);
        Assert.assertEquals(course.getCourseId(), courseId);
    }

    @Test
    public void getCourseName(){
        course.setCourseName(courseName);
        Assert.assertEquals(course.getCourseName(), courseName);
    }

    @Test
    public void  getCourseDate(){
        course.setCourseDate(date);
        Assert.assertEquals(course.getCourseDate(), date);
    }
}
