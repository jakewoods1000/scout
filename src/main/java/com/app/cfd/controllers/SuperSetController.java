package com.app.cfd.controllers;

import com.app.cfd.models.SuperSet;
import com.app.cfd.services.SuperSetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/superSets")
public class SuperSetController {

    private final SuperSetService superSetService;

    public SuperSetController(SuperSetService superSetService) {
        this.superSetService = superSetService;
    }

    @PostMapping()
    ResponseEntity createSuperSet(@RequestBody SuperSet superSet) {
        HttpStatusCode status = HttpStatus.CREATED;
        Object response;
        try {
            response = superSetService.insert(superSet);
        }
        catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    @GetMapping("/{id}")
    ResponseEntity superSetById(@PathVariable UUID id){
        HttpStatusCode status = HttpStatus.OK;
        Object response;
        try {
            response = superSetService.getSuperSet(id);
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
        Object response = "Delete Successful";
        try {
            superSetService.deleteById(id);
        }
        catch(Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    @PutMapping()
    ResponseEntity updateSuperSet(@RequestBody SuperSet superSet) {
        HttpStatusCode status = HttpStatus.OK;
        Object response = "Update Successful";
        try {
            superSetService.updateSuperSet(superSet);
        }
        catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }
}
