package com.melit.springbootdemo;

import com.melit.springbootdemo.domain.JokeResponse;
import com.melit.springbootdemo.services.JokeService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class JokeServiceTest {

    private Logger logger = LoggerFactory.getLogger(JokeService.class);

    @Autowired
    private JokeService service;


    @Test
    public void getJoke() {

        JokeResponse jokeResponse = service.getJoke("Rajni", "Can");
        logger.info(jokeResponse.toString());
        assertNotNull(jokeResponse);

        assertTrue(jokeResponse.getValue().getJoke().contains("Rajni"));
        assertTrue(jokeResponse.getValue().getJoke().contains("Can"));

    }


}
