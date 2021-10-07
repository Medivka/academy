package com.example.data_baseapp.controllers.rest;

import com.example.data_baseapp.domain.dto.CDto;
import com.example.data_baseapp.domain.model.C;
import com.example.data_baseapp.modelmapper.MyModelMapper;
import com.example.data_baseapp.service.CService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sacuta V.A.
 */


@RestController
@RequestMapping("rest/c")
public class CRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CRestController.class);
    private final CService service;
    private final MyModelMapper myModelMapper;

    public CRestController(CService service, MyModelMapper myModelMapper) {
        this.service = service;
        this.myModelMapper = myModelMapper;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<CDto> getById(@PathVariable(name = "id") int id) {
        CDto cDto = myModelMapper.map(service.findById(id), CDto.class);
        return cDto != null
                ? new ResponseEntity<>(cDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> create(@RequestBody CDto cDto) {
        LOGGER.info(String.format("rest/save %s", cDto.getName()));
        service.save(myModelMapper.map((cDto), C.class));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Integer id, @RequestBody CDto cDto) {
        LOGGER.info(String.format("rest/update/get/{%s} ", id));
        if (service.exist(myModelMapper.map(cDto, C.class))) {
            return new ResponseEntity<>(service.update(myModelMapper.map(cDto, C.class)), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteC(@PathVariable(name = "id") Integer id) {
        LOGGER.info(String.format("rest/c/delete/{%s} ", id));

        if (service.exist(service.findById(id))) {
            service.delete(service.findById(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<CDto>> read() {
        LOGGER.info("rest/c/get/all ");
        final List<CDto> cDtoList = myModelMapper.mapCollections(service.getAll(), CDto.class);
        return cDtoList != null && !cDtoList.isEmpty()
                ? new ResponseEntity<>(cDtoList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
