package com.epam.brest.summer.courses2019.rest_app;

import com.epam.brest.summer.courses2019.model.Course;
import com.epam.brest.summer.courses2019.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class CourseRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRestController.class);

    @Autowired
    private CourseService service;

    @GetMapping(value ="/courses")
    public Collection<Course> findAll(){
        LOGGER.debug("get all courses");
        return service.findAll();
    }

    @GetMapping(value ="/courses/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Course findById(@PathVariable Integer id) {
        LOGGER.debug("find course by id({})", id);
        return service.findById(id);
    }

    @PutMapping()
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void update(@RequestBody Course course) {
        LOGGER.debug("update course ({})", course);
        service.update(course);
    }

    @DeleteMapping(value = "/courses/{id}")
    public void delete(@PathVariable("id") Integer id) {
        LOGGER.debug("delete course ({})", id);
        service.delete(id);
    }

    @PostMapping()
    public ResponseEntity<Course> add(@RequestBody Course course){
        LOGGER.debug("add course({})", course);
        Course result = service.add(course);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
