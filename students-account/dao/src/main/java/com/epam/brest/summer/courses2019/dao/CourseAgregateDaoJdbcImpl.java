package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.CourseAgregate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseAgregateDaoJdbcImpl implements CourseAgregateDao{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${courseAgregate.findAllWithCountOfStudents}")
    private String findAllWithCountofStudentsSql;

    public CourseAgregateDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<CourseAgregate> findAllWithCountOfStudents() {
        List<CourseAgregate> courses = namedParameterJdbcTemplate.query(findAllWithCountofStudentsSql,
                BeanPropertyRowMapper.newInstance(CourseAgregate.class));
        return courses;
    }
}
