package com.melit.springbootdemo.services;

import com.melit.springbootdemo.domain.JokeResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Service
public class JokeService {

    private WebClient client;

    public JokeService(WebClient.Builder builder) {
        client = builder.baseUrl("http://api.icndb.com").build();
    }

    public JokeResponse getJoke(String fname, String lname) {
        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/jokes/random")
                        .queryParam("limitTo", "[nerdy]")
                        .queryParam("firstName", fname)
                        .queryParam("lastName", lname)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(JokeResponse.class)
                //.map(jokeResponse -> jokeResponse.getValue().getJoke())
                .block(Duration.ofSeconds(2));
    }
}
