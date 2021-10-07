package com.example.webapp.restcontroller;

import com.example.webapp.model.modelDTO.CDto;
import com.example.webapp.service.CRestService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sacuta V.A.
 */

@RestController
@RequestMapping("web/c")
public class CRestWebAppController {

    private final CRestService cRestService;

    public CRestWebAppController(CRestService cRestService) {
        this.cRestService = cRestService;
    }

    @GetMapping(value = "/{id}")
    public CDto getByIdAtService(@PathVariable(name = "id") int id) {
        return cRestService.getById(id);
    }

    @PostMapping(value = "/")
    public CDto create(@RequestBody CDto cDto) {
        return cRestService.save(cDto);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        cRestService.delete(id);
    }

    @PostMapping(value = "/{id}")
    public CDto update(@PathVariable(name = "id") Integer id, @RequestBody CDto cDto) {
        return cRestService.update(cDto, id);
    }
}
