package com.epam.brest.summer.courses2019.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class StudentTest {

    Student student = new Student();

    @Test
    public void getEmployeeId() {
        student.setStudentId(15);
        Assert.assertTrue(student.getStudentId().equals(15));
    }

    @Test
    public void getCourseId() {
        student.setCourseId(16);
        Assert.assertTrue(student.getCourseId().equals(16));
    }

    @Test
    public void getStudentName() {
        student.setStudentName("Name");
        Assert.assertTrue(student.getStudentName().equals("Name"));
    }

}

