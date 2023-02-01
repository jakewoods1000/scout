package com.app.cfd.services;

import com.app.cfd.daos.TagDao;
import com.app.cfd.daos.WorkoutDao;
import com.app.cfd.models.Tag;
import com.app.cfd.models.Workout;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WorkoutService {
    private final WorkoutDao workoutDao;
    private final TagDao tagDao;

    public WorkoutService(WorkoutDao workoutDao, TagDao tagDao) {
        this.workoutDao = workoutDao;
        this.tagDao = tagDao;
    }

    public UUID insert(Workout workout) {
        return workoutDao.inTransaction(transactional -> {
            UUID workoutId = transactional.insert(workout.getName(),
                    workout.getDescription());
            insertSuperSetWorkoutJoins(workoutId, workout.getSuperSetIds());
            return workoutId;
        });
    }

    public Workout getWorkout(UUID id) {
        Workout workout = workoutDao.getWorkout(id);
        List<UUID> superSetIds = workoutDao.getSuperSetIdsByWorkoutId(id);
        workout.setSuperSetIds(superSetIds);
        return workout;
    }

    public void deleteById(UUID id) {
        workoutDao.useTransaction(transactional -> {
            List<UUID> superSetIds = transactional.getSuperSetIdsByWorkoutId(id);
            deleteSuperSetWorkoutJoins(id, superSetIds);
        });
    }

    public void updateWorkout(Workout workout) {
        workoutDao.useTransaction(transactional -> {
            List<UUID> oldSuperSetIds = transactional.getSuperSetIdsByWorkoutId(workout.getId());
            transactional.update(workout.getId(), workout.getName(), workout.getDescription());
            deleteSuperSetWorkoutJoins(workout.getId(), oldSuperSetIds);
            insertSuperSetWorkoutJoins(workout.getId(), workout.getSuperSetIds());
        });
    }

    private void insertSuperSetWorkoutJoins(UUID workoutId, List<UUID> superSetIds) {
        for (UUID superSetId : superSetIds) {
            workoutDao.insertSuperSetWorkoutLink(superSetId, workoutId);
        }
    }

    private void deleteSuperSetWorkoutJoins(UUID workoutId, List<UUID> superSetIds) {
        for (UUID superSetId : superSetIds) {
            workoutDao.deleteSuperSetWorkoutLink(superSetId, workoutId);
        }
        tagDao.deleteJoinsFromWorkoutsByWorkoutId(workoutId);
    }
}
