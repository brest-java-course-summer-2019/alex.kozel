package com.epam.brest.summer.courses2019.rest_app;

import com.epam.brest.summer.courses2019.model.Course;
import com.epam.brest.summer.courses2019.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
public class CourseRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRestController.class);

    @Autowired
    private CourseService courseService;


    @GetMapping(value ="/courses")
    public Collection<Course> findAll(){
        LOGGER.debug("get all courses");
        return courseService.findAll();
    }

    @GetMapping(value ="/courses/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Course findById(@PathVariable Integer id) {
        LOGGER.debug("find course by id({})", id);
        return courseService.findById(id);
    }

    @PutMapping(value = "/clients")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void update(@RequestBody Course course) {
        LOGGER.debug("update course ({})", course);
        courseService.update(course);
    }

    @DeleteMapping(value = "/courses/{id}")
    public void delete(@PathVariable("id") Integer id) {
        LOGGER.debug("delete course ({})", id);
        courseService.delete(id);
    }

    @PostMapping(value = "/courses")
    @ResponseStatus(HttpStatus.CREATED)
    public Course add(@RequestBody Course course){
        LOGGER.debug("add course({})", course);
        return courseService.add(course);
    }
    @GetMapping(value = "/devices/{fromDate}/{toDate}")
    public List<Course> filteredCoursesByDate(
            @PathVariable(value = "fromDate") String fromDate,
            @PathVariable(value = "toDate") String toDate)
            throws ParseException {
        LOGGER.debug("REST-server filterByDate({} - {})", fromDate, toDate);
        SimpleDateFormat formatDate
                = new SimpleDateFormat("yyyy-MM-dd",
                Locale.US);
        Date startDate = formatDate.parse(fromDate);
        Date endDate = formatDate.parse(toDate);
        return courseService.filterCourseByDate(startDate, endDate);
    }

}
