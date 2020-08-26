package com.wind4869.snippets.research.sping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * TestController
 *
 * @author wind4869
 * @since 1.0.0
 */
@Controller
public class TestController {
    @Value("${project}")
    private String project;
    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @ResponseBody
    @TestAnnotation
    @RequestMapping(value = "/", method = GET)
    public String home() {
        return project;
    }

    @ResponseBody
    @TestAnnotation
    @RequestMapping(value = "/welcome/{name}", method = GET)
    public String welcome(@PathVariable String name) {
        return testService.welcome(name);
    }
}
