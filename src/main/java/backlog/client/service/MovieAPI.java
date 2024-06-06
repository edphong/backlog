package backlog.client.service;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MovieAPI {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonNode fetchDataFromApi() throws Exception {
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            String response = client.prepare("GET", "https://imdb8.p.rapidapi.com/title/get-all-images?tconst=tt0944947")
                    .setHeader("x-rapidapi-key", "9a673e79e8msh43877208ccf2063p19e7c2jsn46935face9ed")
                    .setHeader("x-rapidapi-host", "imdb8.p.rapidapi.com")
                    .execute()
                    .toCompletableFuture()
                    .thenApply(res -> {
                        try {
                            return res.getResponseBody();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .join();
            return objectMapper.readTree(response);
        }
    }
}