package com.epam.brest.summer.courses2019.rest_app;

import com.epam.brest.summer.courses2019.model.Course;
import com.epam.brest.summer.courses2019.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class CourseRestControllerTest {

    private static final String COURSE_NAME = "courseName";
    private static final Integer COURSE_ID1 = 1;
    private static final Integer COURSE_ID2 = 2;
    private static final String DATE = "1970-02-02";
    private SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    private  Date date = formatDate.parse(DATE);

    @InjectMocks
    private CourseRestController courseRestController ;

    @Mock
    private CourseService courseService;

    ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    public CourseRestControllerTest() throws ParseException {
    }


    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(courseRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @Test
    public void findAll() {
        Mockito.when(courseService.findAll()).thenReturn(Arrays.asList(createCourseForTest(COURSE_ID1), createCourseForTest(COURSE_ID2)));
        courseRestController.findAll();
        Mockito.verify(courseService, times(1)).findAll();

}

    @Test
    public void findById() {
        Mockito.when(courseService.findById(COURSE_ID1)).thenReturn(createCourseForTest(COURSE_ID1));
        courseRestController.findById(COURSE_ID1);
        Mockito.verify(courseService).findById(COURSE_ID1);
        Mockito.verifyNoMoreInteractions(courseService);
    }

    @Test
    public void add() {
        Course testCourse = createCourseForTest(COURSE_ID1);
        Mockito.when(courseService.add(testCourse)).thenReturn(testCourse);
        courseRestController.add(testCourse);
        Mockito.verify(courseService).add(testCourse);
    }

    @Test
    public void add2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/courses")
                .content(asJsonString(new Course(COURSE_NAME, date)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn().getResponse()
        ;

    }

    @Test
    public void update() throws Exception {
        Course course = createCourseForTest(COURSE_ID1);
        String json = new ObjectMapper().writeValueAsString(course);
    }

    @Test
    public void delete()  {
        doNothing().when(courseService).delete(createCourseForTest(COURSE_ID1).getCourseId());

        courseRestController.delete(COURSE_ID1);
        Mockito.verify(courseService).delete(COURSE_ID1);
        Mockito.verify(courseService, times(1)).delete(COURSE_ID1);
        Mockito.verifyNoMoreInteractions(courseService);
    }

    private Course createCourseForTest(int courseId) {
        Course course = new Course();
        course.setCourseId(courseId);
        course.setCourseName(COURSE_NAME + courseId);
        course.setCourseDate(date);
        return course;
    }

    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
