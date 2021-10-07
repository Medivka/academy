package com.example.webapp.restcontroller;

import com.example.webapp.model.modelDTO.DDto;
import com.example.webapp.service.DRestService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sacuta V.A.
 */

@RestController
@RequestMapping("web/d")
public class DRestWebAppController {

    private final DRestService dRestService;

    public DRestWebAppController(DRestService dRestService) {
        this.dRestService = dRestService;
    }

    @GetMapping(value = "/{id}")
    public DDto getByIdAtService(@PathVariable(name = "id") int id) {
        return dRestService.getById(id);
    }

    @PostMapping(value = "/")
    public DDto create(@RequestBody DDto dDto) {
        return dRestService.save(dDto);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        dRestService.delete(id);
    }

    @PostMapping(value = "/{id}")
    public DDto update(@PathVariable(name = "id") Integer id, @RequestBody DDto dDto) {
        return dRestService.update(dDto, id);
    }
}
