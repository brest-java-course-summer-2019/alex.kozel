package com.epam.brest.summer.courses2019.web_app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Root controller.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    /**
     * Redirect to default page Students.
     *
     * @return redirect path
     */
    @GetMapping("/")
    public String defaultPageRedirect() {
        return "redirect:Courses";
    }
}