package com.app.cfd.services;

import com.app.cfd.daos.ExerciseDao;
import com.app.cfd.daos.TagDao;
import com.app.cfd.models.Exercise;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class ExerciseService {
    private final ExerciseDao exerciseDao;
    private final TagDao tagDao;

    public ExerciseService(ExerciseDao exerciseDao, TagDao tagDao) {
        this.exerciseDao = exerciseDao;
        this.tagDao = tagDao;
    }

    public void updateExercise(Exercise exercise) {
//        TODO: Add to this check if a user has admin permissions they can edit anything as well
        if (Objects.equals(exercise.getUserId(), exerciseDao.findById(exercise.getId()).getUserId())) {
            exerciseDao.updateExercise(exercise);
        } else {
//            TODO: We may consider raising an error instead, or doing this but also raising something to the user to inform them of this action
            exerciseDao.insert(exercise);
        }

    }

    public void deleteExercise(UUID id) {
        exerciseDao.useTransaction(transactional -> {
            tagDao.deleteJoinsFromExercisesByExerciseId(id);
            exerciseDao.deleteById(id);
        });
    }
}
