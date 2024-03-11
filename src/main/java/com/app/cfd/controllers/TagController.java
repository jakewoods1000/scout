package com.app.cfd.controllers;

import com.app.cfd.controllers.model.TagInput;
import com.app.cfd.models.Tag;
import com.app.cfd.services.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping()
    ResponseEntity createTag(@RequestBody Tag tag, Principal principal) {
        HttpStatusCode status = HttpStatus.CREATED;
        Object response;
        try {
            response = tagService.insert(principal.getName(), tag);
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    @GetMapping("/{id}")
    ResponseEntity tagById(@PathVariable UUID id) {
        HttpStatusCode status = HttpStatus.OK;
        Object response;
        try {
            response = tagService.getTag(id);
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    @GetMapping()
    ResponseEntity tags() {
        HttpStatusCode status = HttpStatus.OK;
        Object response;
        try {
            response = tagService.getAllTags();
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    @PutMapping("/{id}")
    ResponseEntity updateTag(@RequestBody Tag tag, Principal principal) {
        HttpStatusCode status = HttpStatus.OK;
        Object response = null;
        try {
            tagService.updateTag(principal.getName(), tag);
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
            tagService.deleteById(id);
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    @PostMapping("/tagEntity")
    ResponseEntity tagEntity(@RequestBody TagInput input) {
        HttpStatusCode status = HttpStatus.CREATED;
        Object response;
        try {
            response = tagService.tagEntity(input);
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }
}
