package com.example.webapp.restcontroller;

import com.example.webapp.model.modelDTO.ADto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author Sacuta V.A.
 */


@RestController
@RequestMapping("rest/a")
public class ARestWebAppController {

    private final RestTemplate restTemplate;

    public ARestWebAppController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping(value = "/{id}")
    public ADto getByIdAtService(@PathVariable(name = "id") int id) {
        return this.restTemplate.getForObject("http://data-base-app:8003/rest/a/{id}", ADto.class, id);
    }

    @PostMapping(value = "/save")
    public void create(@RequestBody ADto aDto) {
        this.restTemplate.postForObject("http://data-base-app:8003/rest/a/save", aDto, ADto.class);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        this.restTemplate.delete("http://data-base-app:8003/rest/a/{id}", id);
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable(name = "id") Integer id, @RequestBody ADto aDto){
        this.restTemplate.put("http://data-base-app:8003/rest/a/{id}",aDto,id);
    }
}
