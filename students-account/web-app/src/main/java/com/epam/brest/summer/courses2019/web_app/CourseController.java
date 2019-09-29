package com.epam.brest.summer.courses2019.web_app;

import com.epam.brest.summer.courses2019.model.Course;
import com.epam.brest.summer.courses2019.service.CourseService;
import com.epam.brest.summer.courses2019.web_app.validators.CourseValidator;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Course controller
 */
@Controller
public class CourseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseValidator courseValidator;

    /**
     * Goto course list page
     *
     * @param model spring model class
     *
     * @return view name
     *
     * @throws Exception
     */
//    @GetMapping(value = "/courses")
//    public final String courses(Model model) throws Exception{
//        LOGGER.debug("findAll({})", model);
//    model.addAttribute("courses", courseService.findAll());
//    return "courses";
//    }

    @GetMapping(value = "/courses")
    public final String courses(Model model) throws Exception{
        LOGGER.debug("findAll({})", model);
        model.addAttribute("courses", courseService.countStudentsOnCourse());
        return "courses";
    }

    @GetMapping(value = "/course")
    public final String gotoAddDevicePage(Model model) throws Exception {
        LOGGER.debug("gotoAddCoursePage({})", model);
        Course course = new Course();
        model.addAttribute("isNew", true);
        model.addAttribute("course", course);
        return "course";
    }

    @PostMapping(value = "/course")
    public final String addCourse(@Valid Course courseAdd, BindingResult result, Model model)
            throws DataAccessException {
        LOGGER.debug("postAddCourse({}, {})", courseAdd, result);
        courseValidator.validate(courseAdd, result);
        if(result.hasErrors()) {
            model.addAttribute("course", courseAdd);
            model.addAttribute("isNew", true );
            return "course";
        } else {
            courseService.add(courseAdd);
            return "redirect:/courses";
        }

    }

    /**
     * Goto edit course page
     *
     * @param id
     * @param model
     * @return view name
     *
     * @throws Exception
     */
    @GetMapping(value ="/course/{id}")
    public final String gotoEditCoursesPage (@PathVariable Integer id, Model model) throws Exception{
        LOGGER.debug("gotoEditCoursePage({}, {})", id, model);
        model.addAttribute("course", courseService.findById(id));
        model.addAttribute("isNew", false);
        return "course";
    }

    /**
     * Persist new course to DB
     * @param course
     * @param result
     * @param model
     * @return view name
     *
     * @throws Exception
     */
    @PostMapping(value = "/course/{id}")
    public String updateCourse(@Valid Course course, BindingResult result, Model model)
        throws Exception {
        LOGGER.debug("updateCourse({}, {})", course, model);
        courseValidator.validate(course, result);
        if (result.hasErrors()) {
            model.addAttribute("course", course);
            model.addAttribute("isNew", false);
            return "course";
        } else {
            this.courseService.update(course);
            return "redirect:/courses";
        }
    }

    /**
     * Delete course
     *
     * @param id
     * @param model
     * @return view name
     *
     * @throws Exception
     */
    @GetMapping(value = "/course/{id}/delete")
    public final String deleteCourseById(@PathVariable Integer id, Model model)
        throws Exception {
        LOGGER.debug("delete({}, {})", id, model);
        courseService.delete(id);
        return "redirect:/courses";
    }

    /**
     * filter courses by date
     * @param fromDate
     * @param toDate
     * @param model
     * @return - path
     *
     * @throws ParseException
     */
    @GetMapping(value = "/courses/{fromDate}/{toDate}")
    public String filterCoursesByDate(@PathVariable String fromDate, @PathVariable String toDate, Model model)
        throws ParseException{
        LOGGER.debug("filterCoursesByDate({} - {})", fromDate, toDate);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse(fromDate);
        Date endDate = simpleDateFormat.parse(toDate);
        model.addAttribute("courses", courseService.filterCourseByDate(startDate, endDate));
        return  "devices";
    }
}
