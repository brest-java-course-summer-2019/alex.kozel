package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.CountStudentsOnCourse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountStudentsOnCourseDaoJdbcImpl implements CountStudentsOnCourseDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${countStudentsOnCourse.findAllWithCountOfStudents}")
    private String findAllWithCountOfStudents;

    public CountStudentsOnCourseDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<CountStudentsOnCourse> countStudentsOnCourse() {
        List<CountStudentsOnCourse> courses = namedParameterJdbcTemplate.query(findAllWithCountOfStudents,
                BeanPropertyRowMapper.newInstance(CountStudentsOnCourse.class));
        return courses;
    }
}
