package com.example.webapp.restcontroller;

import com.example.webapp.model.modelDTO.BDto;
import com.example.webapp.model.modelDTO.CDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author Sacuta V.A.
 */

@RestController
@RequestMapping("web/c")
public class CRestWebAppController {

    private final RestTemplate restTemplate;

    public CRestWebAppController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping(value = "/{id}")
    public CDto getByIdAtService(@PathVariable(name = "id") int id) {
        return this.restTemplate.getForObject("http://data-base-app:8003/rest/c/{id}", CDto.class, id);
    }

    @PostMapping(value = "/save")
    public void create(@RequestBody CDto cDto) {
        this.restTemplate.postForObject("http://data-base-app:8003/rest/c/save", cDto, CDto.class);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        this.restTemplate.delete("http://data-base-app:8003/rest/c/{id}", id);
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable(name = "id") Integer id, @RequestBody CDto cDto){
        this.restTemplate.put("http://data-base-app:8003/rest/c/{id}",cDto,id);
    }
}
