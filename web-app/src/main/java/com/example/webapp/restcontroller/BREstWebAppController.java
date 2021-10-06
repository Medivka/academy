package com.example.webapp.restcontroller;

import com.example.webapp.model.modelDTO.BDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author Sacuta V.A.
 */

@RestController
@RequestMapping("web/b")
public class BREstWebAppController {

    private static final String URL_BY_ID="http://data-base-app:8003/rest/b/{id}";
    private static final String URL_SAVE="http://data-base-app:8003/rest/b/";

    private final RestTemplate restTemplate;

    public BREstWebAppController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping(value = "/{id}")
    public BDto getById(@PathVariable(name = "id") int id) {
        return this.restTemplate.getForObject(URL_BY_ID, BDto.class, id);
    }

    @PostMapping(value = "/save")
    public void create(@RequestBody BDto bDto) {
        this.restTemplate.postForObject(URL_SAVE, bDto, BDto.class);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        this.restTemplate.delete(URL_BY_ID, id);
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable(name = "id") Integer id, @RequestBody BDto bDto) {
        this.restTemplate.put(URL_BY_ID, bDto, id);
    }
}
