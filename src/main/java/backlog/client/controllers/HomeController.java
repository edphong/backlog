package backlog.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import backlog.client.models.TopRatedMoviesResponse;
import backlog.client.config.ApiKeyProvider;

@Controller
public class HomeController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApiKeyProvider apiKeyProvider;

    @GetMapping("/")
    public String index(Model model) {
        // trending films today
        String url = "https://api.themoviedb.org/3/trending/movie/day?language=en-US";
        String apiKey = apiKeyProvider.getApiKey();  // Retrieve API key using ApiKeyProvider
        
        TopRatedMoviesResponse response = restTemplate.getForObject(url + "&api_key=" + apiKey, TopRatedMoviesResponse.class);

        model.addAttribute("movies", response.getResults());
        return "index";
    }
}
