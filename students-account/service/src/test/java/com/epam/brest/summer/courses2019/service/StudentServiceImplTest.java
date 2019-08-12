package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml"})
public class StudentServiceImplTest {

    @Autowired
    private StudentService studentService;

    @Test
    void findAll() {
        List<Student> students = studentService.findAll();

        assertNotNull(students);
        assertFalse(students.isEmpty());
    }

    @Test
    void findById() {
        int id = 1;
        Student student = studentService.findById(id);

        assertNotNull(student);
        assertEquals("FUSER10", student.getStudentName());
    }

    @Test
    void findByCourseId() {
        Integer id = 1;
        List<Student> students = studentService.findByCourseId(id);

        assertNotNull(students);
        assertTrue(students.size() > 0);
    }

    @Test
    void add() {
        long sizeBefore = studentService.findAll().size();
        Student student = create();
        Student newStudent = studentService.add(student);
        long sizeAfter = studentService.findAll().size();
        assertNotNull(newStudent.getStudentId());
        assertEquals(newStudent.getCourseId(), newStudent.getCourseId());
        assertEquals(newStudent.getStudentName(), newStudent.getStudentName());
        assertEquals(sizeBefore +1, sizeAfter);
    }

    @Test
    void update(){
        Student student = studentService.findById(2);
        student.setStudentName("AnotherName");
        studentService.update(student);
        Student updateStudent = studentService.findById(student.getStudentId());
        assertEquals(student.getStudentName(), updateStudent.getStudentName());
        assertEquals(student.getStudentId(), updateStudent.getStudentId());
        assertEquals(student.getCourseId(), updateStudent.getCourseId());
    }

    @Test
    void delete(){
        Student student = create();
        studentService.add(student);
        List<Student> students = studentService.findAll();
        int sizeBefore = students.size();
        studentService.delete(student.getStudentId());
        assertTrue((sizeBefore -1) == studentService.findAll().size());
    }

    public Student create(){
        Student student = new Student("name",3);
        return student;
    }

}
