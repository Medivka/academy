package com.example.data_baseapp.controllers.rest;

import com.example.data_baseapp.domain.dto.DDto;
import com.example.data_baseapp.domain.model.D;
import com.example.data_baseapp.modelmapper.MyModelMapper;
import com.example.data_baseapp.service.DService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

/**
 * @author Sacuta V.A.
 */

@RequestScope                      /// на каждый запрос новый бин контроллера
@RestController
@RequestMapping("rest/d")
public class DRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DRestController.class);
    private final DService service;
    private final MyModelMapper myModelMapper;

    public DRestController(DService service, MyModelMapper myModelMapper) {
        this.service = service;
        this.myModelMapper = myModelMapper;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<DDto> getByIdAtService(@PathVariable(name = "id") int id) {
        DDto dDto = myModelMapper.map((service.getById(id)), DDto.class);
        return dDto != null
                ? new ResponseEntity<>(dDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> create(@RequestBody DDto dDto) {
        LOGGER.info(String.format("rest/save %s", dDto.getName()));
        service.save(myModelMapper.map(dDto, D.class));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Integer id, @RequestBody DDto dDto) {
        LOGGER.info(String.format("rest/d/get/{%s} ", id));
        boolean update = false;
        if (service.exist(myModelMapper.map(dDto, D.class))) {
            service.update(myModelMapper.map(dDto, D.class));
            update = true;
        }
        return update
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        LOGGER.info(String.format("rest/d/delete/{%s} ", id));
        boolean delete = false;
        if (service.exist(service.findById(id))) {
            service.delete(service.findById(id));
            delete = true;
        }
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<DDto>> read() {
        LOGGER.info("rest/d/get/all ");
        final List<DDto> dDtoList = myModelMapper.mapCollections(service.getAll(), DDto.class);
        return dDtoList != null && !dDtoList.isEmpty()
                ? new ResponseEntity<>(dDtoList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

