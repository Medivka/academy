package com.example.webapp.restcontroller;

import com.example.webapp.model.modelDTO.BDto;
import com.example.webapp.service.BRestService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sacuta V.A.
 */

@RestController
@RequestMapping("web/b")
public class BREstWebAppController {

    private final BRestService bRestService;

    public BREstWebAppController(BRestService bRestService) {
        this.bRestService = bRestService;
    }

    @GetMapping(value = "/{id}")
    public BDto getById(@PathVariable(name = "id") int id) {
        return bRestService.getByIdB(id);
    }

    @PostMapping(value = "/")
    public BDto create(@RequestBody BDto bDto) {
        return bRestService.save(bDto);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        bRestService.delete(id);
    }

    @PostMapping(value = "/{id}")
    public BDto update(@PathVariable(name = "id") Integer id, @RequestBody BDto bDto) {
        return bRestService.update(bDto, id);
    }
}
