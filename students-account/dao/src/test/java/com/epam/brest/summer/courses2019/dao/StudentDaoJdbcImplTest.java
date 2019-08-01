package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
@Rollback

public class StudentDaoJdbcImplTest {

    private final Integer STUDENTID = 1;
    private final String NAME = "FUSER10";
    private final Integer COURSEID = 1;

    @Autowired
    StudentDao studentDao;

    @Test
    public void findAll (){
        List<Student> students = studentDao.findAll();
        assertNotNull(students);
        assertTrue(students.size() > 0);
    }

    @Test
    public void findByCourseId(){
        List<Student> students = studentDao.findByCourseId(1);
        assertNotNull(students);
        assertTrue(students.size() > 0);
    }

    @Test
    public void findById(){
        assertNotNull(studentDao);
        Student student = studentDao.findById(1).get();
        assertEquals(student.getStudentId(),  STUDENTID);
        assertEquals(student.getStudentName(), NAME);
        assertEquals(student.getCourseId(), COURSEID);
    }

    @Test
    public void addStudent(){
        List<Student> students = studentDao.findAll();
        int sizeBefore = students.size();
        Student student = new Student(NAME, COURSEID);
        Student newStudent = studentDao.add(student);
        assertNotNull(newStudent.getStudentId());
        assertEquals(newStudent.getCourseId(), student.getCourseId());
        assertEquals(newStudent.getStudentName(), student.getStudentName());
        assertTrue((sizeBefore +1) == studentDao.findAll().size());
    }

    @Test
    public void update(){
    Student student = studentDao.findById(2).get();
    student.setStudentName("AnotherName");
    studentDao.update(student);
    Student updateStudent = studentDao.findById(student.getStudentId()).get();
    assertEquals(student.getStudentName(), updateStudent.getStudentName());
    assertEquals(student.getStudentId(), updateStudent.getStudentId());
    assertEquals(student.getCourseId(), updateStudent.getCourseId());
    }

    @Test
    public void delete(){
        Student student = new Student(NAME, 1);
        studentDao.add(student);
        List<Student> students = studentDao.findAll();
        int sizeBefore = students.size();
        studentDao.delete(student.getStudentId());
        assertTrue((sizeBefore -1) == studentDao.findAll().size());
    }
}

