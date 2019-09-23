package com.epam.brest.summer.courses2019.web_app.consumers;

import com.epam.brest.summer.courses2019.model.Course;
import com.epam.brest.summer.courses2019.service.CourseService;
import com.epam.brest.summer.courses2019.web_app.ServerDataAccessExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

public class CourseRestConsumer implements CourseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRestConsumer.class);

    private String url;

    private RestTemplate restTemplate;

    public CourseRestConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }


    @Override
    public List<Course> findAll() throws ServerDataAccessExeption {
        LOGGER.debug("Find all courses");
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<Course>) responseEntity.getBody();
    }

    @Override
    public Course findById(Integer id) throws ServerDataAccessExeption{
        LOGGER.debug("Find courses By id({})", id);
        ResponseEntity<Course> responseEntity = restTemplate.getForEntity(url + "/" + id, Course.class );
        return responseEntity.getBody();
    }

    @Override
    public void update(Course course) throws ServerDataAccessExeption{
        LOGGER.debug("Update course({})", course);
        restTemplate.put(url, course);
    }

    @Override
    public void delete(int id) throws  ServerDataAccessExeption{
        LOGGER.debug("delete course({})", id);
        restTemplate.delete(url + "/" + id);
    }

    @Override
    public List<Course> filterCourseByDate(Date fromDate, Date toDate) throws ServerDataAccessExeption{
        LOGGER.debug("REST filter Courses By Date ({}, {})", fromDate, toDate );
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url + "/" + fromDate + "/" + toDate, List.class);
        LOGGER.debug("REST responseEntity filter Courses By Date ({})", responseEntity );
        return (List<Course>) responseEntity.getBody();
    }

    @Override
    public Course add(Course course) throws ServerDataAccessExeption{
        LOGGER.debug("add course ({})", course);
        ResponseEntity responseEntity = restTemplate.postForEntity(url, course, Course.class);
        Object result = responseEntity.getBody();
        return (Course) result;
    }
}
