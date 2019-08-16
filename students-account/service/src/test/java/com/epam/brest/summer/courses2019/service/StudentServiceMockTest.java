package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.dao.StudentDao;
import com.epam.brest.summer.courses2019.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

    @ExtendWith(MockitoExtension.class)
    public class StudentServiceMockTest {

        @Mock
        private StudentDao dao;

        @Captor
        private ArgumentCaptor<Student> studentCaptor;

        @InjectMocks
        private StudentServiceImpl service;

        @AfterEach
        void after() {
            Mockito.verifyNoMoreInteractions(dao);
        }

        @Test
        void findAll() {

            Mockito.when(dao.findAll()).thenReturn(Collections.singletonList(create()));

            List<Student> result = service.findAll();

            assertNotNull(result);
            assertEquals(1, result.size());

            Mockito.verify(dao).findAll();
        }

        @Test
        void findById() {

            int id = 1;

            Mockito.when(dao.findById(id)).thenReturn(Optional.of(create()));

            Student student = service.findById(id);

            assertNotNull(student);
            assertEquals("name", student.getStudentName());

            Mockito.verify(dao).findById(id);
        }

        @Test
        void update() {

            service.update(create());

            Mockito.verify(dao).update(studentCaptor.capture());

            Student student = studentCaptor.getValue();
            assertNotNull(student);
            assertEquals("name", student.getStudentName());
        }

        @Test
        void delete() {

            int id = 3;

            service.delete(id);

            Mockito.verify(dao).delete(id);
        }

        private Student create() {
            Student student = new Student();
            student.setStudentName("name");
            return student;
        }
    }
