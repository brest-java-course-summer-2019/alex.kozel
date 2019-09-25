package com.epam.brest.summer.courses2019.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class CourseAgregateTest {

    CountStudentsOnCourse agregate = new CountStudentsOnCourse();

    Integer sum = 15;
    Integer courseId = 1;
    String courseName = "Math";

    @Test
    public void getStudentSum(){
        agregate.setCountOfStudents(15);
        Assert.assertEquals(agregate.getCountOfStudents(), sum);
    }

    @Test
    public void getCourseId(){
        agregate.setCourseId(courseId);
        Assert.assertEquals(agregate.getCourseId(), courseId);
    }

    @Test
    public void getCourseName(){
        agregate.setCourseName(courseName);
        Assert.assertEquals(agregate.getCourseName(), courseName);
    }

}
