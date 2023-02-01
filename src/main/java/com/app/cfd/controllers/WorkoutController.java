package com.app.cfd.controllers;

import com.app.cfd.models.Workout;
import com.app.cfd.services.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/workouts")
public class WorkoutController {
    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) { this.workoutService = workoutService; }

    @PostMapping()
    ResponseEntity createWorkout(@RequestBody Workout workout) {
        HttpStatusCode status = HttpStatus.CREATED;
        Object response;
        try {
            response = workoutService.insert(workout);
        }
        catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    @GetMapping("/{id}")
    ResponseEntity workoutById(@PathVariable UUID id){
        HttpStatusCode status = HttpStatus.OK;
        Object response;
        try {
            response = workoutService.getWorkout(id);
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
            workoutService.deleteById(id);
        }
        catch(Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }

    @PutMapping()
    ResponseEntity updateWorkout(@RequestBody Workout workout) {
        HttpStatusCode status = HttpStatus.OK;
        Object response = "Update Successful";
        try {
            workoutService.updateWorkout(workout);
        }
        catch(Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity(response, status);
    }
}
