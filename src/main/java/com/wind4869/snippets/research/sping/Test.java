package com.wind4869.snippets.research.sping;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Test
 *
 * @author wind4869
 * @since 1.0.0
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
        TestController controller = context.getBean("testController", TestController.class);
        controller.welcome("wind4869");
    }
}
