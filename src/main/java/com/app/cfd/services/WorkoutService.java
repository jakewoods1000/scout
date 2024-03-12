package com.app.cfd.services;

import com.app.cfd.daos.TagDao;
import com.app.cfd.daos.WorkoutDao;
import com.app.cfd.models.OrderedId;
import com.app.cfd.models.Workout;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
            UUID workoutId = transactional.insert(workout);
            if (workout.getOrderedSuperSetIds() != null) {
                insertSuperSetWorkoutJoins(workoutId, workout.getOrderedSuperSetIds());
            }
            return workoutId;
        });
    }

    public Workout getWorkoutById(UUID id) {
        Workout workout = workoutDao.findById(id);
        List<OrderedId> orderedSuperSetIds = workoutDao.getSuperSetIdsByWorkoutId(id);
        workout.setOrderedSuperSetIds(orderedSuperSetIds);
        return workout;
    }

    public void deleteById(UUID id) {
        workoutDao.useTransaction(transactional -> {
            transactional.deleteAllSuperSetWorkoutLinks(id);
            transactional.deleteWorkout(id);
        });
    }

    public void updateWorkout(Workout workout) {
        if (Objects.equals(workout.getUserId(), workoutDao.findById(workout.getId()).getUserId())) {
            workoutDao.useTransaction(transactional -> {
                List<OrderedId> oldSuperSetIds = transactional.getSuperSetIdsByWorkoutId(workout.getId());
                transactional.update(workout.getId(), workout.getName(), workout.getDescription());
                deleteSuperSetWorkoutJoins(workout.getId(), oldSuperSetIds);
                if (workout.getOrderedSuperSetIds() != null) {
                    insertSuperSetWorkoutJoins(workout.getId(), workout.getOrderedSuperSetIds());
                }
            });
        } else {
            insert(workout);
        }
    }

    private void insertSuperSetWorkoutJoins(UUID workoutId, List<OrderedId> orderedSuperSetIds) {
        for (OrderedId orderedId : orderedSuperSetIds) {
            workoutDao.insertSuperSetWorkoutLink(orderedId.getId(), orderedId.getOrder(), workoutId);
        }
    }

    private void deleteSuperSetWorkoutJoins(UUID workoutId, List<OrderedId> orderedSuperSetIds) {
        List<UUID> superSetIds = new ArrayList<>();
        for (OrderedId orderedId : orderedSuperSetIds) {
            superSetIds.add(orderedId.getId());
        }
        workoutDao.deleteSuperSetWorkoutLinks(superSetIds, workoutId);
        tagDao.deleteJoinsFromWorkoutsByWorkoutId(workoutId);
    }
}
