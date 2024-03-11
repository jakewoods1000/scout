package com.app.cfd.controllers;

import com.app.cfd.daos.SetDao;
import com.app.cfd.models.Set;
import com.app.cfd.services.SetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sets")
public class SetController {
    private final SetService setService;
    private final SetDao setDao;

    public SetController(SetService setService, SetDao setDao) {
        this.setService = setService;
        this.setDao = setDao;
    }

    @PostMapping()
    ResponseEntity createSet(@RequestBody Set set, Principal principal) {
        HttpStatusCode status = HttpStatus.CREATED;
        Object response;
        try {
            set.setUserId(principal.getName());
            response = setDao.insert(set, setService.getQuantityAsString(set.getQuantity()));
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    @PostMapping("/bulk-create")
    ResponseEntity createBulkSet(@RequestBody List<Set> sets, Principal principal) {
        HttpStatusCode status = HttpStatus.CREATED;
        Object response;
        try {
//            TODO: Same transaction question as with Exercises
            for (Set set : sets) {
                set.setUserId(principal.getName());
                setDao.insert(set, setService.getQuantityAsString(set.getQuantity()));
            }
            response = "Successfully created sets";
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    @GetMapping("/{id}")
    ResponseEntity setById(@PathVariable UUID id) {
        HttpStatusCode status = HttpStatus.OK;
        Object response;
        try {
            response = setDao.findById(id);
        } catch (Exception e) {
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
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    @PutMapping()
    ResponseEntity updateSet(@RequestBody Set set, Principal principal) {
        HttpStatusCode status = HttpStatus.OK;
        Object response = null;
        try {
            set.setUserId(principal.getName());
            setService.updateSet(set);
        } catch (Exception e) {
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
            setService.deleteById(id);
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }
}
