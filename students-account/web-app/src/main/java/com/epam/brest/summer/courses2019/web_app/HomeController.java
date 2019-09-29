package com.epam.brest.summer.courses2019.web_app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Root controller.
 */
@Controller
public class HomeController {

    /**
     * Redirect to default page Students.
     *
     * @return redirect path
     */
    @GetMapping(value = "/")
    public String defaultPageRedirect() {
        return "redirect:courses";
    }
}