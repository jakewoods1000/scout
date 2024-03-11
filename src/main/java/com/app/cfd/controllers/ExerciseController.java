package com.app.cfd.controllers;

import com.app.cfd.daos.ExerciseDao;
import com.app.cfd.models.Exercise;
import com.app.cfd.services.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;
    private final ExerciseDao exerciseDao;

    public ExerciseController(ExerciseService exerciseService, ExerciseDao exerciseDao) {
        this.exerciseService = exerciseService;
        this.exerciseDao = exerciseDao;
    }

    @PostMapping()
    ResponseEntity createExercise(@RequestBody Exercise exercise, Principal principal) {
        HttpStatusCode status = HttpStatus.CREATED;
        Object response;
        try {
            exercise.setUserId(principal.getName());
            response = exerciseDao.insert(exercise);
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    @PostMapping("/bulk-create")
    ResponseEntity bulkCreateExercise(@RequestBody List<Exercise> exercises, Principal principal) {
        HttpStatusCode status = HttpStatus.CREATED;
        Object response;
        try {
//            TODO: Should this be done in a transaction instead? Just thinking about how this might be weird if we get
//            half-way through a bulk insert then it blows up, half are created, then if you rerun the bulk insert
//            you end up with doubles of the first half that was successful...
            for (Exercise exercise : exercises) {
                exercise.setUserId(principal.getName());
                exerciseDao.insert(exercise);
            }
            response = "Successfully Inserted Records";
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
    ResponseEntity updateExercise(@RequestBody Exercise exercise, Principal principal) {
        HttpStatusCode status = HttpStatus.OK;
        Object response = null;
        try {
            exercise.setUserId(principal.getName());
            exerciseService.updateExercise(exercise);
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
            exerciseService.deleteExercise(id);
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }
}
