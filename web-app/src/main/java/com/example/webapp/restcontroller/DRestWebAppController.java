package com.example.webapp.restcontroller;

import com.example.webapp.model.modelDTO.CDto;
import com.example.webapp.model.modelDTO.DDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author Sacuta V.A.
 */

@RestController
@RequestMapping("web/d")
public class DRestWebAppController {

    private final RestTemplate restTemplate;

    public DRestWebAppController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping(value = "/{id}")
    public DDto getByIdAtService(@PathVariable(name = "id") int id) {
        return this.restTemplate.getForObject("http://data-base-app:8003/rest/d/{id}", DDto.class, id);
    }

    @PostMapping(value = "/save")
    public void create(@RequestBody DDto dDto) {
        this.restTemplate.postForObject("http://data-base-app:8003/rest/d/save", dDto, DDto.class);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        this.restTemplate.delete("http://data-base-app:8003/rest/d/{id}", id);
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable(name = "id") Integer id, @RequestBody DDto dDto){
        this.restTemplate.put("http://data-base-app:8003/rest/d/{id}",dDto,id);
    }
}
