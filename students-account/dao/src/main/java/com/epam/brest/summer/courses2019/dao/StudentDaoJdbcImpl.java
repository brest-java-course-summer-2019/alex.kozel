package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Student DAO Interface implementation.
 */
@Component
public class StudentDaoJdbcImpl implements StudentDao  {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Value("${student.findAll}")
    private String findAllSql;

    @Value("${student.findById}")
    private String findByIdSql;

    @Value("${student.insert}")
    private String insertSql;

    @Value("${student.update}")
    private String updateSql;

    @Value("${student.delete}")
    private String deleteSql;

    @Value("${student.findByCourseId}")
    private String findByCourseIdSql;



    private static final String COURSE_ID = "courseId";
    private static final String STUDENT_ID = "studentId";

    public StudentDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students =
                namedParameterJdbcTemplate.query(findAllSql, BeanPropertyRowMapper.newInstance(Student.class));
        return students;
    }

    @Override
    public List<Student> findByCourseId(Integer courseId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(COURSE_ID, courseId);
        List<Student> results = namedParameterJdbcTemplate.query(findByCourseIdSql, namedParameters,
                BeanPropertyRowMapper.newInstance(Student.class));
        return results;
    }

    @Override
    public Optional<Student> findById(Integer studentId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(STUDENT_ID, studentId);
        List<Student> results = namedParameterJdbcTemplate.query(findByIdSql, namedParameters,
                BeanPropertyRowMapper.newInstance(Student.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public Student add(Student student) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("studentName", student.getStudentName());
        parameters.addValue("courseId", student.getCourseId());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertSql, parameters, generatedKeyHolder);
        student.setStudentId(generatedKeyHolder.getKey().intValue());
        return student;
    }

    @Override
    public void update(Student student) {
        Optional.of(namedParameterJdbcTemplate.update(updateSql, new BeanPropertySqlParameterSource(student)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update student in DB"));
    }

    @Override
    public void delete(Integer studentId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(STUDENT_ID, studentId);
        Optional.of(namedParameterJdbcTemplate.update(deleteSql, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete student from DB"));
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

}