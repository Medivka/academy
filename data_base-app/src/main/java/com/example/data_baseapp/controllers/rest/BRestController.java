package com.example.data_baseapp.controllers.rest;

import com.example.data_baseapp.domain.dto.BDto;
import com.example.data_baseapp.domain.model.B;
import com.example.data_baseapp.modelmapper.MyModelMapper;
import com.example.data_baseapp.service.BService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.StyledEditorKit;
import java.util.List;

/**
 * @author Sacuta V.A.
 */


@RestController
@RequestMapping("rest/b")
public class BRestController {


    private static final Logger LOGGER = LoggerFactory.getLogger(BRestController.class);
    private final BService service;
    private final MyModelMapper myModelMapper;

    public BRestController(BService service, MyModelMapper myModelMapper) {
        this.service = service;
        this.myModelMapper = myModelMapper;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<BDto> getByIdAtService(@PathVariable(name = "id") int id) {
        final BDto bDto = myModelMapper.map(service.findById(id), BDto.class);
        return bDto != null
                ? new ResponseEntity<>(bDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> create(@RequestBody BDto bDto) {
        LOGGER.info(String.format("rest/save %s", bDto.getPhone()));
        service.save(myModelMapper.map((bDto), B.class));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Integer id, @RequestBody BDto bDto) {
        LOGGER.info(String.format("rest/update/get/{%s} ", id));
        if (service.exist(myModelMapper.map(bDto, B.class))) {
            BDto bDto1 = myModelMapper.map(service.update(myModelMapper.map(bDto, B.class)), BDto.class);
            return new ResponseEntity<>(bDto1, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        LOGGER.info(String.format("rest/b/{%s} ", id));
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
    public ResponseEntity<List<BDto>> read() {
        LOGGER.info("rest/b/all ");
        List<BDto> bDtoList = myModelMapper.mapCollections(service.getAll(), BDto.class);
        return bDtoList != null && !bDtoList.isEmpty()
                ? new ResponseEntity<>(bDtoList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
