package rest.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
// ------------------------------ FIELDS ------------------------------

    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface CommandLineRunner ---------------------

    @Override
    public void run(String... args) throws Exception {
        ResponseEntity<Joke> response = get("https://api.chucknorris.io/jokes/random", Joke.class);
        out(response.getBody());
    }

    private <T> ResponseEntity<T> get(String url, Class<T> clazz) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url, HttpMethod.GET, entity, clazz);
    }

    private void out(Object o) throws JsonProcessingException {
        log.info(new ObjectMapper().writeValueAsString(o));
    }

// --------------------------- main() method ---------------------------

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
