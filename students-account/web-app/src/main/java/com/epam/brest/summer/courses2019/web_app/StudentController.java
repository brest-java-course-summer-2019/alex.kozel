package com.epam.brest.summer.courses2019.web_app;


import com.epam.brest.summer.courses2019.model.Student;
import com.epam.brest.summer.courses2019.service.CourseService;
import com.epam.brest.summer.courses2019.service.StudentService;
import com.epam.brest.summer.courses2019.web_app.validators.StudentValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Student controller
 */
@Controller
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    /**
     * Service of student
     */
    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    StudentValidator studentValidator;

    /**
     * Goto edit student page
     * @param id
     * @param model
     * @return view name
     *
     * @throws Exception
     */
    @GetMapping(value = "/student/{id}")
    public final String gotoEditStudentPage(@PathVariable Integer id, Model model)
        throws Exception {
        LOGGER.debug("gotoEditStudentPage({}, {})", id, model);
        model.addAttribute("student", studentService.findById(id));
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("isNew", false);
        return "student";
    }

    /**
     * Goto student list page
     * @param model
     * @return view name
     *
     * @throws Exception
     */
    @GetMapping(value = "/students")
    public final String courses(Model model) throws Exception{
        LOGGER.debug("findAll({})", model);
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("students", studentService.findAll());
        return "students";
    }

    /**
     * Update students in DB
     *
     * @param student
     * @param result
     * @param model
     * @return view name
     *
     * @throws Exception
     */
    @PostMapping(value = "student/{id}")
    public String updateStudent(@Valid Student student, BindingResult result, Model model)
        throws Exception{
        LOGGER.debug("updateStudent([], {})", student, result);
        studentValidator.validate(student, result);
        if (result.hasErrors()) {
            model.addAttribute("student", student);
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("isNew", false);
            return "student";
        } else {
            this.studentService.update(student);
            return "redirect:/courses";
        }
    }

    /**
     * Goto add student page
     * @param model
     * @return view name
     *
     * @throws Exception
     */
    @GetMapping(value = "/student")
    public final String gotoAddStudentPage(Model model) throws Exception{
        LOGGER.debug("gotoAddStudentPage({})", model);
        Student student = new Student();
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("isNew", true);
        model.addAttribute("student", student);
        return "student";
    }

    /**
     * Add student in DB
     * @param studentAdd
     * @param result
     * @param model
     * @return view name
     *
     * @throws Exception
     */
    @PostMapping(value = "/student")
    public final String addStudent(@Valid Student studentAdd, BindingResult result, Model model)
            throws Exception{
        LOGGER.debug("postAddStudent({}, {})",studentAdd, model);
        studentValidator.validate(studentAdd, result);
        if (result.hasErrors()) {
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("student", studentAdd);
            model.addAttribute("isNew", true);
            return "student";
        } else {
            studentService.add(studentAdd);
            return "redirect:/students";
        }
    }

    @GetMapping(value = "/student{id}/delete")
    public final String deleteStudentById(@PathVariable Integer id, Model model)
        throws Exception{
        LOGGER.debug("deleteStudentById({}, {})", id, model);
        studentService.delete(id);
        return "redirect:/students";
    }
}
