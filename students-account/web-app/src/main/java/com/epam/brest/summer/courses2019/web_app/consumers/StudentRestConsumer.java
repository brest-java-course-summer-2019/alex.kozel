package com.epam.brest.summer.courses2019.web_app.consumers;

import com.epam.brest.summer.courses2019.model.Student;
import com.epam.brest.summer.courses2019.service.StudentService;
import com.epam.brest.summer.courses2019.web_app.ServerDataAccessExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class StudentRestConsumer implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentRestConsumer.class);

    private String url;

    private RestTemplate restTemplate;

    /**
     * Constructor for Student consumer
     *
     * @param url
     * @param restTemplate
     */
    public StudentRestConsumer(String url, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    @Override
    public List<Student> findAll() throws DataAccessException {
        LOGGER.debug("Find all students");
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<Student>) responseEntity.getBody();
    }

    @Override
    public List<Student> findByCourseId(Integer courseId) throws DataAccessException{
        LOGGER.debug("Find students by course id({})", courseId);
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/{courseId}", List.class);
        return (List<Student>) responseEntity.getBody();
    }

    @Override
    public Student findById(Integer studentId)
            throws ServerDataAccessExeption {
        LOGGER.debug("findById({})", studentId);
        ResponseEntity<Student> responseEntity = restTemplate.getForEntity(url + "/" + studentId, Student.class);
        return responseEntity.getBody();
    }

    @Override
    public void update(Student student) throws ServerDataAccessExeption {
        LOGGER.debug("update({})", student);
        restTemplate.put(url, student);
    }

    @Override
    public Student add(Student student) throws DataAccessException {
        LOGGER.debug("add({})", student);
        ResponseEntity responseEntity = restTemplate.postForEntity(url, student, Student.class);
        Object result = responseEntity.getBody();
        return (Student) result;
    }

    @Override
    public void delete(Integer studentId) throws DataAccessException{
        LOGGER.debug("delete student ({})", studentId);
        restTemplate.delete(url +"/" + studentId);
    }
}
