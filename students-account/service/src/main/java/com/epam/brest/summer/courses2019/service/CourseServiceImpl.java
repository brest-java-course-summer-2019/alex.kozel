package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.dao.CourseDao;
import com.epam.brest.summer.courses2019.model.Course;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

    public class DepartmentServiceImpl implements CourseService {

        private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

        private CourseDao dao;

        public DepartmentServiceImpl(CourseDao dao) {
            this.dao = dao;
        }

        @Override
        public List<Course> findAll() {
            LOGGER.debug("Find all courses");
            return dao.findAll();
        }

        @Override
        public Course findById(Integer id) {
            LOGGER.debug("findById({})", id);
            return dao.findById(id)
                    .orElseThrow(() -> new RuntimeException("Failed to get courses from DB"));
        }

        @Override
        public void update(Course course) {
            LOGGER.debug("update({})", course);
            dao.update(course);
        }

        @Override
        public void delete(int id) {
            LOGGER.debug("delete({})", id);
            dao.delete(id);
        }
    }
}