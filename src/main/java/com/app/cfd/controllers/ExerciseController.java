package com.app.cfd.controllers;

import com.app.cfd.daos.ExerciseDao;
import com.app.cfd.daos.TagDao;
import com.app.cfd.models.Exercise;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {
    private final ExerciseDao exerciseDao;
    private final TagDao tagDao;

    public ExerciseController(ExerciseDao exerciseDao, TagDao tagDao) {
        this.exerciseDao = exerciseDao;
        this.tagDao = tagDao;
    }

    @PostMapping()
    ResponseEntity createExercise(@RequestBody Exercise exercise) {
        HttpStatusCode status = HttpStatus.CREATED;
        Object response;
        try {
            response = exerciseDao.insert(exercise.getName(), exercise.getDescription());
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    @GetMapping("/{id}")
    ResponseEntity exerciseById(@PathVariable UUID id) {
        HttpStatusCode status = HttpStatus.OK;
        Object response;
        try {
            response = exerciseDao.findById(id);
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    @GetMapping()
    ResponseEntity exercises() {
        HttpStatusCode status = HttpStatus.OK;
        Object response;
        try {
            response = exerciseDao.getAllExercises();
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    @PutMapping()
    ResponseEntity updateExercise(@RequestBody Exercise exercise) {
        HttpStatusCode status = HttpStatus.OK;
        Object response = null;
        try {
            exerciseDao.updateExercise(exercise.getName(), exercise.getDescription(), exercise.getId());
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
            exerciseDao.useTransaction(transactional -> {
                tagDao.deleteJoinsFromExercisesByExerciseId(id);
                exerciseDao.deleteById(id);
            });
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }
}
