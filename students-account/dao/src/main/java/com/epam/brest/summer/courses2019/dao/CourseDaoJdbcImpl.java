package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.CountStudentsOnCourse;
import com.epam.brest.summer.courses2019.model.Course;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;

/**
 *  Course DAO Interface implementation.
 */
@Component
public class CourseDaoJdbcImpl implements CourseDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseDaoJdbcImpl.class);

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${course.findAll}")
    private String findAllSql;

    @Value("${course.findById}")
    private String findByIdSql;

    @Value("${course.insert}")
    private String insertSql;

    @Value("${course.update}")
    private String updateSql;

    @Value("${course.delete}")
    private String deleteSql;

    @Value("${course.filter}")
    private String filterSql;

    /**
     * Columns in DB table Courses.
     */
    private static final String COURSE_NAME = "courseName";
    private static final String COURSE_ID = "courseId";
    private static final String COURSE_DATE = "courseDate";

    /**
     * Date filter for SQL query.
     */
    private static final String FROM_DATE = "fromDate";
    private static final String TO_DATE = "toDate";

    public CourseDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Course add(Course course) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(COURSE_NAME, course.getCourseName());
        parameters.addValue(COURSE_DATE, course.getCourseDate());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertSql, parameters, generatedKeyHolder);
        course.setCourseId(generatedKeyHolder.getKey().intValue());
        return course;
    }

    @Override
    public void update(Course course) {
        Optional.of(namedParameterJdbcTemplate.update(updateSql, new BeanPropertySqlParameterSource(course)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update course in DB"));
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    @Override
    public void delete(Integer courseId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(COURSE_ID, courseId);
        Optional.of(namedParameterJdbcTemplate.update(deleteSql, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete course from DB"));
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = namedParameterJdbcTemplate.query(findAllSql, new CourseRowMapper());
        return courses;
    }

    @Override
    public Optional<Course> findById(Integer courseId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(COURSE_ID, courseId);
        List<Course> results = namedParameterJdbcTemplate.query(findByIdSql, namedParameters,
                BeanPropertyRowMapper.newInstance(Course.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    private class CourseRowMapper implements RowMapper<Course>{
        @Override
        public Course mapRow(ResultSet resultSet, int i) throws SQLException{
            Course course = new Course();
            course.setCourseId(resultSet.getInt("course_Id"));
            course.setCourseName(resultSet.getString("course_name"));
            course.setCourseDate(resultSet.getDate("course_date"));
            return course;
        }
    }

    private class CountStudentsOnCourseRowMapper implements RowMapper<CountStudentsOnCourse>{
        @Override
        public CountStudentsOnCourse mapRow(ResultSet resultSet, int i) throws SQLException{
            CountStudentsOnCourse course = new CountStudentsOnCourse();
            course.setCourseId(resultSet.getInt("course_Id"));
            course.setCourseName(resultSet.getString("course_name"));
            course.setCourseDate(resultSet.getDate("course_date"));
            return course;
        }
    }

    @Override
    public List<CountStudentsOnCourse> filterCourseByDate(Date fromDate, Date toDate) {
        LOGGER.debug("filterDeviceByDate({}, {})", fromDate, toDate);
        SqlParameterSource namedParamets = new MapSqlParameterSource().addValue(FROM_DATE, fromDate).addValue(TO_DATE, toDate);
        List<CountStudentsOnCourse> courses = namedParameterJdbcTemplate.query(filterSql, namedParamets, new CountStudentsOnCourseRowMapper());
        LOGGER.debug("filteredCourses({})");
        return courses;
    }
}
