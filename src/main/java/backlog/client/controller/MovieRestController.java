package backlog.client.controller;

import com.fasterxml.jackson.databind.JsonNode;
import backlog.client.service.MovieAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api")
public class MovieRestController {

    @Autowired
    private MovieAPI movieapi;

    @GetMapping("/data")
    public String getApiData(Model model) throws Exception {
        JsonNode data = movieapi.fetchDataFromApi();
        model.addAttribute("data", data.toPrettyString());
        return "data";  // Return the name of the Thymeleaf template
    }
}