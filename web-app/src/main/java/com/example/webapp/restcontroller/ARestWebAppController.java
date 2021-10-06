package com.example.webapp.restcontroller;

import com.example.webapp.model.modelDTO.ADto;
import com.example.webapp.service.impl.ARestServiceImpl;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sacuta V.A.
 */


@RestController
@RequestMapping("web/a")
public class ARestWebAppController {

    private final ARestServiceImpl service;

    public ARestWebAppController(ARestServiceImpl service) {

        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ADto getByIdAtService(@PathVariable(name = "id") int id) {
        return service.getById(id);
    }

    @PostMapping(value = "/")
    public ADto create(@RequestBody ADto aDto) {
        return service.save(aDto);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        service.deleteA(id);
    }

    @PostMapping(value = "/{id}")
    public ADto update(@PathVariable(name = "id") Integer id, @RequestBody ADto aDto) {
        return service.update(aDto, id);
    }
}
