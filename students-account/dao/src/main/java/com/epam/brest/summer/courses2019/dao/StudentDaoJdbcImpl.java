package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
 * Employee DAO Interface implementation.
 */
@Component
public class StudentDaoJdbcImpl implements StudentDao  {

    private final static String SELECT_ALL =
            "select student_id, student_name, date, course_id from student";

    private static final String FIND_BY_ID =
            "select student_id, student_name, date, course_id " +
                    "from student where student_id = :studentId";

    private static final String FIND_BY_COURSE_ID =
            "select student_id, student_name, date, course_id " +
                    "from employee where course_id = :courseId";

    private final static String ADD_STUDENT =
            "insert into student (student_name, date, course_id) " +
                    "values (:studentName, :date, :courseId)";

    private static final String UPDATE =
            "update student set student_name = :studentName, date = :date, " +
                    " course_id = :courseId where student_ud = :studentId";

    private static final String DELETE =
            "delete from employee where employee_id = :employeeId";

    private static final String COURSE_ID = "courseId";
    private static final String STUDENT_ID = "studentId";
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public StudentDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students =
                namedParameterJdbcTemplate.query(SELECT_ALL, BeanPropertyRowMapper.newInstance(Student.class));
        return students;
    }

    @Override
    public List<Student> findByCourseId(Integer courseId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(COURSE_ID, courseId);
        List<Student> results = namedParameterJdbcTemplate.query(FIND_BY_COURSE_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(Student.class));
        return results;
    }

    @Override
    public Optional<Student> findById(Integer studentId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(STUDENT_ID, studentId);
        List<Student> results = namedParameterJdbcTemplate.query(FIND_BY_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(Student.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public Student add(Student student) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("studentName", student.getStudentName());
        parameters.addValue("courseId", student.getCourseId());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_STUDENT, parameters, generatedKeyHolder);
        student.setStudentId(generatedKeyHolder.getKey().intValue());
        return student;
    }

    @Override
    public void update(Student student) {
        Optional.of(namedParameterJdbcTemplate.update(UPDATE, new BeanPropertySqlParameterSource(student)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update student in DB"));
    }

    @Override
    public void delete(Integer studentId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(STUDENT_ID, studentId);
        Optional.of(namedParameterJdbcTemplate.update(DELETE, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete student from DB"));
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

}