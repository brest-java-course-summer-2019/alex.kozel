package com.epam.brest.summer.courses2019.rest_app;

import com.epam.brest.summer.courses2019.model.Course;
import com.epam.brest.summer.courses2019.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:restTest.xml", "classpath*:dao-context.xml"})
public class CourseRestControllerTest {

    private static final String COURSE_NAME = "courseName";
    private static final Integer COURSE_ID1 = 1;
    private static final Integer COURSE_ID2 = 2;
    private static final String DATE = "1970-02-02";
    private SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    private  Date date = formatDate.parse(DATE);

    @Autowired
    private CourseRestController courseRestController;

    @Autowired
    private CourseService courseService;

    ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    public CourseRestControllerTest() throws ParseException {
    }

    @BeforeEach
    void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(courseRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @AfterEach
    void after() {
        Mockito.reset(courseService);
    }

    @Test
    public void findAll() throws Exception {

        Mockito.when(courseService.findAll()).thenReturn(Arrays.asList(createCourseForTest(COURSE_ID1), createCourseForTest(COURSE_ID2)));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/courses")
                .accept(MediaType.APPLICATION_JSON_UTF8))
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].courseId", Matchers.is(COURSE_ID1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].courseName", Matchers.is(COURSE_NAME + COURSE_ID1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].courseId", Matchers.is(COURSE_ID2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].courseName", Matchers.is(COURSE_NAME + COURSE_ID2)));

        Mockito.verify(courseService).findAll();
        Mockito.verifyNoMoreInteractions(courseService);
}

    @Test
    public void findById() throws Exception {
        Mockito.when(courseService.findById(COURSE_ID1)).thenReturn(createCourseForTest(COURSE_ID1));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/courses/{courseId}", COURSE_ID1)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.courseId").value(COURSE_ID1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.courseName").value(COURSE_NAME + COURSE_ID1));

        Mockito.verify(courseService).findById(COURSE_ID1);
        Mockito.verifyNoMoreInteractions(courseService);
    }

    @Test
    public void add() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/courses")
                .content(asJsonString(createCourseForTest(3)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn().getResponse()
        ;

    }

    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void update() throws Exception {

        Course course = createCourseForTest(COURSE_ID1);

        String json = new ObjectMapper().writeValueAsString(course);
        mockMvc.perform(MockMvcRequestBuilders
                .put("/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        )
                .andExpect(status().isAccepted())
        ;
    }

    @Test
    public void delete() throws Exception {
        Mockito.when(courseService.findById(createCourseForTest(COURSE_ID1).getCourseId())).thenReturn(createCourseForTest(COURSE_ID1));
        doNothing().when(courseService).delete(createCourseForTest(COURSE_ID1).getCourseId());

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/courses/{courseId}", COURSE_ID1))
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;

        Mockito.verify(courseService).delete(COURSE_ID1);
        Mockito.verify(courseService, times(1)).delete(COURSE_ID1);
        Mockito.verifyNoMoreInteractions(courseService);
    }

//    @Test
//    public void countStudentsOnCourse() throws Exception {
//
//        Mockito.when(courseService.countStudentsOnCourse()).thenReturn(Arrays.asList(createCourseForTest(COURSE_ID1), createCourseForTest(COURSE_ID2)));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/courses/with_count")
//                .accept(MediaType.APPLICATION_JSON_UTF8))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].courseId", Matchers.is(COURSE_ID1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].courseName", Matchers.is(COURSE_NAME + COURSE_ID1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].courseId", Matchers.is(COURSE_ID2)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].courseName", Matchers.is(COURSE_NAME + COURSE_ID2)));
//
//        Mockito.verify(courseService).countStudentsOnCourse();
//        Mockito.verifyNoMoreInteractions(courseService);
//    }


    private Course createCourseForTest(int courseId) {
        Course course = new Course();
        course.setCourseId(courseId);
        course.setCourseName(COURSE_NAME + courseId);
        course.setCourseDate(date);
        return course;
    }


}
