package com.app.cfd.controllers;

import com.app.cfd.models.SuperSet;
import com.app.cfd.services.SuperSetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/superSets")
public class SuperSetController {

    private final SuperSetService superSetService;

    public SuperSetController(SuperSetService superSetService) {
        this.superSetService = superSetService;
    }

    @PostMapping()
    ResponseEntity createSuperSet(@RequestBody SuperSet superSet, Principal principal) {
        HttpStatusCode status = HttpStatus.CREATED;
        Object response;
        try {
            superSet.setUserId(principal.getName());
            response = superSetService.insert(superSet);
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    // TODO: Review: I added bulk adds for all the API's to make testing easier
    @PostMapping("/bulk-create")
    ResponseEntity createBulkSuperSet(@RequestBody List<SuperSet> superSets, Principal principal) {
        HttpStatusCode status = HttpStatus.CREATED;
        Object response;
        try {
            for (SuperSet superSet : superSets) {
                superSet.setUserId(principal.getName());
                superSetService.insert(superSet);
            }
            response = "Successfully Inserted SuperSets";
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    @GetMapping("/getAll")
    ResponseEntity getAllSuperSets() {
        HttpStatusCode status = HttpStatus.OK;
        Object response;
        try {
            response = superSetService.getAllSuperSets();
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    @GetMapping("/{id}")
    ResponseEntity superSetById(@PathVariable UUID id) {
        HttpStatusCode status = HttpStatus.OK;
        Object response;
        try {
            response = superSetService.findById(id);
        } catch (Exception e) {
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
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    @PutMapping()
    ResponseEntity updateSuperSet(@RequestBody SuperSet superSet, Principal principal) {
        HttpStatusCode status = HttpStatus.OK;
        Object response = "Update Successful";
        try {
            superSet.setUserId(principal.getName());
            superSetService.updateSuperSet(superSet);
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }
}
