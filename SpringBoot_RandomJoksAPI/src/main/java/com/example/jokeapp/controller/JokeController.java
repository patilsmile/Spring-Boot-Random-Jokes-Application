package com.example.jokeapp.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class JokeController {

    private final RestTemplate restTemplate = new RestTemplate();

//    @GetMapping("/api/joke")
//    public String getJoke() {
//        try {
//            String url = "https://api.freeapi.app/api/v1/public/randomjokes/joke/random";
//            String response = restTemplate.getForObject(url, String.class);
    
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode root = mapper.readTree(response);
//            return root.path("data").path("content").asText();
//        } catch (Exception e) {
//            return "Error: " + e.getMessage();
//        }
//    }
    
    
    @GetMapping("/api/joke")
    public String getQuote() {
        try {
            HttpResponse<String> response = Unirest.get("https://api.freeapi.app/api/v1/public/quotes/quote/random")
                    .header("accept", "application/json")
                    .asString();

            // Parse the JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            String quote = root.path("data").path("content").asText();
            String author = root.path("data").path("author").asText();

            return quote + " — " + author;

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

}
