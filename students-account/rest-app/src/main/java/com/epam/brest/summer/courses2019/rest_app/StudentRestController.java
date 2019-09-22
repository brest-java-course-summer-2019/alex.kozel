package com.epam.brest.summer.courses2019.rest_app;

import com.epam.brest.summer.courses2019.model.Student;
import com.epam.brest.summer.courses2019.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentRestController.class);

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/students")
    public List<Student> findAll() {
        LOGGER.debug("get all students");
        return studentService.findAll();
    }

    @GetMapping(value = "/students/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Student findById(@PathVariable Integer id) {
        LOGGER.debug("find student by id{}", id);
        return studentService.findById(id);
    }

    @PutMapping(value = "/students")
    public final void update(@RequestBody Student student){
        LOGGER.debug("ubdate student {}", student);
        studentService.update(student);
    }

    @DeleteMapping(value = "/students/{id}")
    public void delete(@PathVariable ("id") Integer id) {
        LOGGER.debug("delete client ({})", id);
        studentService.delete(id);
    }

    @PostMapping(value = "/students")
    @ResponseStatus(HttpStatus.CREATED)
    public final Student add(@RequestBody Student student){
        LOGGER.debug("add student({})", student);
        return studentService.add(student);
    }

    @GetMapping(value = "/students/{courseId}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Student> findByCourseId(@PathVariable("courseId") Integer courseId) {
        LOGGER.debug("REST: Find iteminorder by orderId({})", courseId);
        List<Student> result = studentService.findByCourseId(courseId);
        return result;
    }
}
