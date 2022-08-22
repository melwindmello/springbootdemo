package com.melit.springbootdemo;

import com.melit.springbootdemo.controllers.HelloController;
import org.junit.jupiter.api.Test;
import org.springframework.validation.support.BindingAwareModelMap;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloControllerUnitTest {

    @Test
    public void testSayHello(){
        HelloController helloController = new HelloController();
        BindingAwareModelMap model = new BindingAwareModelMap();

        String response = helloController.sayHello("john doe", model);

        assertAll(
                () -> assertEquals("john doe", model.getAttribute("user")),
                () -> assertEquals("welcome", response)
        );

    }
}
