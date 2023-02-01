package com.app.cfd.controllers;

import com.app.cfd.daos.SetDao;
import com.app.cfd.daos.TagDao;
import com.app.cfd.models.Set;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/sets")
public class SetController {
    private final SetDao setDao;
    private final TagDao tagDao;
    private final ObjectMapper objectMapper;

    public SetController(SetDao setDao, TagDao tagDao) {
        this.setDao = setDao;
        this.objectMapper = new ObjectMapper();
        this.tagDao = tagDao;
    }

    @PostMapping()
    ResponseEntity createSet(@RequestBody Set set) {
        HttpStatusCode status = HttpStatus.CREATED;
        Object response;
        try {
            response = setDao.insert(set.getName(), set.getDescription(),
                    objectMapper.writeValueAsString(set.getQuantity()), set.getExercise_id(), set.getStyle());
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    @GetMapping("/{id}")
    ResponseEntity setById(@PathVariable UUID id){
        HttpStatusCode status = HttpStatus.OK;
        Object response;
        try {
            response = setDao.getSet(id);
        }
        catch(Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    @GetMapping()
    ResponseEntity sets() {
        HttpStatusCode status = HttpStatus.OK;
        Object response;
        try {
            response = setDao.getAllSets();
        }
        catch(Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    @PutMapping()
    ResponseEntity updateSet(@RequestBody Set set){
        HttpStatusCode status = HttpStatus.OK;
        Object response = null;
        try {
            setDao.updateSet(set.getId(), set.getName(), set.getDescription(),
                    objectMapper.writeValueAsString(set.getQuantity()), set.getExercise_id(), set.getStyle());
        }
        catch(Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteById(@PathVariable UUID id) {
        HttpStatusCode status = HttpStatus.OK;
        Object response = null;
        try {
            setDao.useTransaction(transactional -> {
                tagDao.deleteJoinsFromSetsBySetId(id);
                transactional.deleteById(id);
            });
        }
        catch(Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }
}
