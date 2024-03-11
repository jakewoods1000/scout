package com.app.cfd.controllers;

import com.app.cfd.daos.WorkoutDao;
import com.app.cfd.models.Workout;
import com.app.cfd.services.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/workouts")
public class WorkoutController {
    private final WorkoutService workoutService;
    private final WorkoutDao workoutDao;

    public WorkoutController(WorkoutService workoutService, WorkoutDao workoutDao) {
        this.workoutService = workoutService;
        this.workoutDao = workoutDao;
    }

    @PostMapping()
    ResponseEntity createWorkout(@RequestBody Workout workout, Principal principal) {
        HttpStatusCode status = HttpStatus.CREATED;
        Object response;
        try {
            workout.setUserId(principal.getName());
            response = workoutService.insert(workout);
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    @GetMapping()
    ResponseEntity getAll() {
        HttpStatusCode status = HttpStatus.OK;
        Object response;
        try {
            response = workoutDao.getAll();
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    @GetMapping("/{id}")
    ResponseEntity workoutById(@PathVariable UUID id) {
        HttpStatusCode status = HttpStatus.OK;
        Object response;
        try {
            response = workoutService.getWorkoutById(id);
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
            workoutService.deleteById(id);
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    @PutMapping()
    ResponseEntity updateWorkout(@RequestBody Workout workout, Principal principal) {
        HttpStatusCode status = HttpStatus.OK;
        Object response = "Update Successful";
        try {
            workout.setUserId(principal.getName());
            workoutService.updateWorkout(workout);
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }
}
