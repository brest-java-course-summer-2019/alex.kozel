package com.epam.brest.summer.courses2019.web_app;

import com.epam.brest.summer.courses2019.model.Course;
import com.epam.brest.summer.courses2019.service.CourseService;
import com.epam.brest.summer.courses2019.web_app.validators.CourseValidator;
import org.springframework.validation.BindingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;

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
     * Goto course list ppage
     *
     * @param model spring model class
     *
     * @return view name
     *
     * @throws Exception
     */
    @GetMapping(value = "/courses")
    public final String courses(Model model) throws Exception{
        LOGGER.debug("findAll({})", model);
    model.addAttribute("courses", courseService.findAll());
    return "courses";
    }

    /**
     * Goto edit client page
     *
     * @param id
     * @param model
     * @return view name
     *
     * @throws Exception
     */
    @GetMapping(value ="/course/{id}")
    public final String gotoEditCoursesPage (@PathVariable Integer id, Model model) throws Exception{
        LOGGER.debug("gotoEditClientPage({}, {})", id, model);
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
        if (result.hasErrors()) {
            model.addAttribute("course", course);
            model.addAttribute("isNew", false);
            return "course";
        } else {
            this.courseService.update(course);
            return "redirect:/clients";
        }
    }

    @GetMapping(value = "/course/{id}/delete")
    public final String deleteCourseById(@PathVariable Integer id, Model model)
        throws Exception {

    }


}
