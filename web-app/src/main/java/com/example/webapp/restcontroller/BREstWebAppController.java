package com.example.webapp.restcontroller;

import com.example.webapp.model.modelDTO.ADto;
import com.example.webapp.model.modelDTO.BDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author Sacuta V.A.
 */

@RestController
@RequestMapping("rest/b")
public class BREstWebAppController {

    private final RestTemplate restTemplate;

    public BREstWebAppController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping(value = "/{id}")
    public BDto getByIdAtService(@PathVariable(name = "id") int id) {
        return this.restTemplate.getForObject("http://data-base-app:8003/rest/b/{id}", BDto.class, id);
    }

    @PostMapping(value = "/save")
    public void create(@RequestBody BDto bDto) {
        this.restTemplate.postForObject("http://data-base-app:8003/rest/b/save", bDto, BDto.class);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        this.restTemplate.delete("http://data-base-app:8003/rest/b/{id}", id);
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable(name = "id") Integer id, @RequestBody BDto bDto){
        this.restTemplate.put("http://data-base-app:8003/rest/b/{id}",bDto,id);
    }
}
