package com.example.data_baseapp.controllers.rest;

import com.example.data_baseapp.domain.dto.ADto;
import com.example.data_baseapp.domain.model.A;
import com.example.data_baseapp.modelmapper.MyModelMapper;
import com.example.data_baseapp.service.AService;
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
@RequestMapping("rest/a")
public class ARestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ARestController.class);
    private final AService service;
    private final MyModelMapper myModelMapper;

    public ARestController(AService service, MyModelMapper myModelMapper) {
        this.service = service;
        this.myModelMapper = myModelMapper;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<ADto> getById(@PathVariable(name = "id") int id) {
        final ADto aDto = myModelMapper.map(service.findById(id), ADto.class);
        return aDto != null
                ? new ResponseEntity<>(aDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> create(@RequestBody ADto aDto) {
        LOGGER.info(String.format("rest/save %s", aDto.getName()));
        service.save(myModelMapper.map((aDto), A.class));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Integer id, @RequestBody ADto aDto) {
        LOGGER.info(String.format("rest/a/get/{%s} ", id));
        boolean update = false;
        if (service.exist(myModelMapper.map(aDto, A.class))) {
            service.update(myModelMapper.map(aDto, A.class));
            update = true;
        }
        return update
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        LOGGER.info(String.format("rest/a/delete/{%s} ", id));
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
    public ResponseEntity<List<ADto>> read() {
        LOGGER.info("rest/a/get/all ");
        List<ADto> ADTOList = myModelMapper.mapCollections(service.getAll(), ADto.class);
        return ADTOList != null && !ADTOList.isEmpty()
                ? new ResponseEntity<>(ADTOList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
